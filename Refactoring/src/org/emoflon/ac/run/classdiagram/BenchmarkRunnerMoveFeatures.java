package org.emoflon.ac.run.classdiagram;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.emoflon.ac.run.classdiagram.config.BenchmarkMoveFeatures;

/**
 * 
 * @author Lars Fritsche
 *
 *         This class performs the performance measurements of our evaluation by
 *         spawning processes executing {@link BenchmarkMoveFeatures} with a
 *         given model size.
 */
public class BenchmarkRunnerMoveFeatures {
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	private static final int MAX_TIMEOUTS = 5;
	private static final int MAX_EXCEPTIONS = 5;
	private static final int TIMEOUT_MINUTES = 5;

	private static int[] modelSizes = new int[] { 5, 10, 20, 30, 40, 50 };
	private static int repetitions = 5;

	private static List<String> jvmArgs = new LinkedList<>();

	private static File currentLogFile;

	public static void main(String[] args) throws IOException, InterruptedException {
		int timeoutCounter = 0;
		int exceptionCounter = 0;

		for (int modelSize : modelSizes) {
			for (int r = 0; r < repetitions; r++) {

				if (timeoutCounter >= MAX_TIMEOUTS) {
					System.out.println("Timeout (" + TIMEOUT_MINUTES + "min) for " + Arrays.asList(args));
					break;
				}

				if (exceptionCounter >= MAX_EXCEPTIONS) {
					System.out.println("Too many exceptions for " + Arrays.asList(args));
					break;
				}

				Process process = execute(BenchmarkMoveFeatures.class, jvmArgs, Arrays.asList("" + modelSize));
				InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
				BufferedReader reader = new BufferedReader(inputStreamReader);

				if (!process.waitFor(TIMEOUT_MINUTES, TimeUnit.MINUTES)) {
					// count timeouts and restart repetition
					timeoutCounter++;
					r--;
					terminateProcess(process);
					continue;
				}

				if (process.exitValue() != 0) {
					StringBuilder b = new StringBuilder();
					String read = reader.readLine();
					while (read != null) {
						b.append(read);
						b.append("\n");
						read = reader.readLine();
					}
					System.err.println(b);
					// count exceptions and restart repetition if one is detected
					exceptionCounter++;
					r--;
					terminateProcess(process);
					continue;
				}

				// clean up log file if it is empty
				if (currentLogFile.length() == 0) {
					currentLogFile.delete();
				}

				StringBuilder b = new StringBuilder();
				String read = reader.readLine();
				while (read != null) {
					b.append(read);
//					b.append("\n");
					read = reader.readLine();
				}

				System.out.println(b);
			}
		}
	}

	protected static Process execute(Class<?> clazz, List<String> jvmArgs, List<String> args)
			throws IOException, InterruptedException {
		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		String className = clazz.getName();

		// create log file and redirect the error stream to it
		String logFolderPath = clazz.getProtectionDomain().getCodeSource().getLocation().getPath().toString()
				.replace("bin/", "") + "log/";
		File logFolder = new File(logFolderPath);
		logFolder.mkdirs();
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		File logFile = new File(logFolderPath + "log_" + args + DATE_FORMAT.format(new Date()) + ".txt");
		if (!logFile.exists())
			logFile.createNewFile();
		currentLogFile = logFile;

		List<String> command = new ArrayList<>();
		command.add(javaBin);
		command.addAll(jvmArgs);
		command.add("-cp");
		command.add(classpath);
		command.add(className);
		command.addAll(args);
		ProcessBuilder builder = new ProcessBuilder(command);
//		builder.redirectError(logFile);
		Process process = builder.start();
		return process;
	}

	private static void terminateProcess(Process process) throws InterruptedException {
		process.destroy();
		int counter = 0;
		while (process.isAlive()) {
			Thread.sleep(10);
			counter++;
			if (counter >= 100)
				process.destroyForcibly();
		}
	}
}

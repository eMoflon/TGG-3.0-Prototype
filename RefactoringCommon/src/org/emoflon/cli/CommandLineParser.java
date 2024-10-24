package org.emoflon.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CommandLineParser {
	/**
	 * Parses the given array of string arguments to the JSON input and output path.
	 * 
	 * @param args Array of string arguments.
	 * @return JSON input and output paths as record.
	 */
	public static PathConfiguration parseArgs(final String[] args) {
		final Options options = new Options();
		final Option jsonHipePath = new Option("h", "hipeNetwork", true, "Path to hipe-network.xmi");
		final Option jsonIbexPath = new Option("i", "ibexModel", true, "Path to ibex_gt_model.xmi");
		final Option jsonTestPath = new Option("t", "testModel", true, "Path to the xmi of the test model ");
		jsonHipePath.setRequired(false);
		jsonIbexPath.setRequired(false);
		jsonTestPath.setRequired(false);
		options.addOption(jsonHipePath);
		options.addOption(jsonIbexPath);
		options.addOption(jsonTestPath);

		var parser = new DefaultParser();
		final HelpFormatter formatter = new HelpFormatter();

		CommandLine cmd = null;

		try {
			cmd = parser.parse(options, args);
		} catch (final ParseException e) {
			System.out.println(e.getMessage());
			formatter.printHelp("cli parameters", options);
			System.exit(1);
		}

		// Return paths
		return new PathConfiguration(cmd.getOptionValue("h"), cmd.getOptionValue("i"), cmd.getOptionValue("t"));
	}
}

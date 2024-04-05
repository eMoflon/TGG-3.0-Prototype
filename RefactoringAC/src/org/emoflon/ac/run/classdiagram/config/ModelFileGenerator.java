package org.emoflon.ac.run.classdiagram.config;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emoflon.smartemf.persistence.SmartEMFResourceFactoryImpl;

import classDiagram.ClassDiagramFactory;

public class ModelFileGenerator {

	final static String fileName = "model.xmi";
	final static int modelSize = 2;

	public static void main(final String[] args) {
		// Workaround: Always use absolute path
		final URI absPath = URI
				.createFileURI(System.getProperty("user.dir") + "/" + "resources/classDiagram/" + fileName);

		// Create new model for saving
		final ResourceSet rs = new ResourceSetImpl();
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new SmartEMFResourceFactoryImpl(null));
		// ^null is okay if all paths are absolute
		final Resource r = rs.createResource(absPath);

		r.getContents().add(ClassDiagramFactory.eINSTANCE.createClazzModel());
		ModelGeneratorUtil.generateModel(r, modelSize);

		try {
			r.save(null);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}

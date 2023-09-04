package softwareSystem;

import softwareSystem.System;
import softwareSystem.Component;

import org.eclipse.emf.ecore.EFactory;

public interface SoftwareSystemFactory extends EFactory {

	SoftwareSystemFactory eINSTANCE = softwareSystem.impl.SoftwareSystemFactoryImpl.init();
	
	System createSystem();
	
	Component createComponent();
	
	
	SoftwareSystemPackage getSoftwareSystemPackage();

}

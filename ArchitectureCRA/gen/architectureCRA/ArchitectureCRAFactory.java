package architectureCRA;

import architectureCRA.ClassModel;
import architectureCRA.Class;
import architectureCRA.Attribute;
import architectureCRA.Method;
import architectureCRA.Feature;
import architectureCRA.NamedElement;

import org.eclipse.emf.ecore.EFactory;

public interface ArchitectureCRAFactory extends EFactory {

	ArchitectureCRAFactory eINSTANCE = architectureCRA.impl.ArchitectureCRAFactoryImpl.init();
	
	ClassModel createClassModel();
	
	Class createClass();
	
	Attribute createAttribute();
	
	Method createMethod();
	
	
	ArchitectureCRAPackage getArchitectureCRAPackage();

}

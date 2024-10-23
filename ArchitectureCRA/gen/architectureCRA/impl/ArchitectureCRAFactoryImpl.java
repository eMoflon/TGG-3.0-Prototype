package architectureCRA.impl;

import architectureCRA.ClassModel;
import architectureCRA.Class;
import architectureCRA.Attribute;
import architectureCRA.Method;
import architectureCRA.Feature;
import architectureCRA.NamedElement;


import architectureCRA.ArchitectureCRAFactory;
import architectureCRA.ArchitectureCRAPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

public class ArchitectureCRAFactoryImpl extends EFactoryImpl implements architectureCRA.ArchitectureCRAFactory {

	public static architectureCRA.ArchitectureCRAFactory init() {
		try {
			ArchitectureCRAFactory theArchitectureCRAFactory = (ArchitectureCRAFactory) EPackage.Registry.INSTANCE
					.getEFactory(ArchitectureCRAPackage.eNS_URI);
			if (theArchitectureCRAFactory != null) {
				return theArchitectureCRAFactory;
			}
		} catch (java.lang.Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ArchitectureCRAFactoryImpl();
	}

	public ArchitectureCRAFactoryImpl() {
		super();
	}

	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case ArchitectureCRAPackage.CLASS_MODEL:
			return createClassModel();
		case ArchitectureCRAPackage.CLASS:
			return createClass();
		case ArchitectureCRAPackage.ATTRIBUTE:
			return createAttribute();
		case ArchitectureCRAPackage.METHOD:
			return createMethod();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}
	
	
	@Override
	public architectureCRA.ClassModel createClassModel() {
		ClassModelImpl classModel = new ClassModelImpl();
		return classModel;
	}
	@Override
	public architectureCRA.Class createClass() {
		ClassImpl __class = new ClassImpl();
		return __class;
	}
	@Override
	public architectureCRA.Attribute createAttribute() {
		AttributeImpl attribute = new AttributeImpl();
		return attribute;
	}
	@Override
	public architectureCRA.Method createMethod() {
		MethodImpl method = new MethodImpl();
		return method;
	}
	

	@Override
	public ArchitectureCRAPackage getArchitectureCRAPackage() {
	return (ArchitectureCRAPackage) getEPackage();
	}
} 

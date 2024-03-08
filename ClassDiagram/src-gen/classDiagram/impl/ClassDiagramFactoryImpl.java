package classDiagram.impl;

import classDiagram.ClazzModel;
import classDiagram.Clazz;
import classDiagram.Feature;
import classDiagram.Method;
import classDiagram.Attribute;


import classDiagram.ClassDiagramFactory;
import classDiagram.ClassDiagramPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

public class ClassDiagramFactoryImpl extends EFactoryImpl implements classDiagram.ClassDiagramFactory {

	public static classDiagram.ClassDiagramFactory init() {
		try {
			ClassDiagramFactory theClassDiagramFactory = (ClassDiagramFactory) EPackage.Registry.INSTANCE
					.getEFactory(ClassDiagramPackage.eNS_URI);
			if (theClassDiagramFactory != null) {
				return theClassDiagramFactory;
			}
		} catch (java.lang.Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ClassDiagramFactoryImpl();
	}

	public ClassDiagramFactoryImpl() {
		super();
	}

	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case ClassDiagramPackage.CLAZZ_MODEL:
			return createClazzModel();
		case ClassDiagramPackage.CLAZZ:
			return createClazz();
		case ClassDiagramPackage.METHOD:
			return createMethod();
		case ClassDiagramPackage.ATTRIBUTE:
			return createAttribute();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}
	
	
	@Override
	public classDiagram.ClazzModel createClazzModel() {
		ClazzModelImpl clazzModel = new ClazzModelImpl();
		return clazzModel;
	}
	@Override
	public classDiagram.Clazz createClazz() {
		ClazzImpl clazz = new ClazzImpl();
		return clazz;
	}
	@Override
	public classDiagram.Method createMethod() {
		MethodImpl method = new MethodImpl();
		return method;
	}
	@Override
	public classDiagram.Attribute createAttribute() {
		AttributeImpl attribute = new AttributeImpl();
		return attribute;
	}
	

	@Override
	public ClassDiagramPackage getClassDiagramPackage() {
	return (ClassDiagramPackage) getEPackage();
	}
} 

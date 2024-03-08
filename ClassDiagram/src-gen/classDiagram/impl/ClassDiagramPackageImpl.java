package classDiagram.impl;

import classDiagram.ClazzModel;
import classDiagram.Clazz;
import classDiagram.Feature;
import classDiagram.Method;
import classDiagram.Attribute;


import classDiagram.ClassDiagramFactory;
import classDiagram.ClassDiagramPackage;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.emoflon.smartemf.runtime.SmartPackageImpl;

public class ClassDiagramPackageImpl extends SmartPackageImpl
		implements ClassDiagramPackage {
			
	private EClass clazzModelEClass = null;
	private EReference clazzModel_clazzesEReference = null;
	private EClass clazzEClass = null;
	private EAttribute clazz_nameEAttribute = null;
	private EReference clazz_featuresEReference = null;
	private EClass featureEClass = null;
	private EAttribute feature_nameEAttribute = null;
	private EClass methodEClass = null;
	private EReference method_dependenciesEReference = null;
	private EClass attributeEClass = null;
	
	

	private ClassDiagramPackageImpl() {
		super(eNS_URI, classDiagram.ClassDiagramFactory.eINSTANCE);
	}

	private static boolean isRegistered = false;
	private boolean isCreated = false;
	private boolean isInitialized = false;

	public static ClassDiagramPackage init() {
		if (isRegistered)
			return (ClassDiagramPackage) EPackage.Registry.INSTANCE
					.getEPackage(ClassDiagramPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredClassDiagramPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ClassDiagramPackageImpl theClassDiagramPackage = registeredClassDiagramPackage instanceof ClassDiagramPackageImpl
				? (ClassDiagramPackageImpl) registeredClassDiagramPackage
				: new ClassDiagramPackageImpl();

		isRegistered = true;

		// Create package meta-data objects
		theClassDiagramPackage.createPackageContents();

		// Initialize created meta-data
		theClassDiagramPackage.initializePackageContents();
		
		// Inject internal eOpposites to unidirectional references
		theClassDiagramPackage.injectDynamicOpposites();
		
		// Inject external references into foreign packages
		theClassDiagramPackage.injectExternalReferences();

		// Mark meta-data to indicate it can't be changed
		theClassDiagramPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ClassDiagramPackage.eNS_URI,
				theClassDiagramPackage);
				
		theClassDiagramPackage.fetchDynamicEStructuralFeaturesOfSuperTypes();
		return theClassDiagramPackage;
	}

	@Override
	public EClass getClazzModel() {
		return clazzModelEClass;
	}
	@Override
	public EReference getClazzModel_Clazzes() {
		return clazzModel_clazzesEReference;	
	}
	@Override
	public EClass getClazz() {
		return clazzEClass;
	}
	@Override
	public EAttribute getClazz_Name() {
		return clazz_nameEAttribute;	
	}
	@Override
	public EReference getClazz_Features() {
		return clazz_featuresEReference;	
	}
	@Override
	public EClass getFeature() {
		return featureEClass;
	}
	@Override
	public EAttribute getFeature_Name() {
		return feature_nameEAttribute;	
	}
	@Override
	public EClass getMethod() {
		return methodEClass;
	}
	@Override
	public EReference getMethod_Dependencies() {
		return method_dependenciesEReference;	
	}
	@Override
	public EClass getAttribute() {
		return attributeEClass;
	}
	
	

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public classDiagram.ClassDiagramFactory getClassDiagramFactory() {
		return (classDiagram.ClassDiagramFactory) getEFactoryInstance();
	}

	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		clazzModelEClass = createEClass(CLAZZ_MODEL);
		createEReference(clazzModelEClass, CLAZZ_MODEL__CLAZZES);
		clazzModel_clazzesEReference = (EReference) clazzModelEClass.getEStructuralFeatures().get(0);
		
		clazzEClass = createEClass(CLAZZ);
		createEAttribute(clazzEClass, CLAZZ__NAME);
		clazz_nameEAttribute = (EAttribute) clazzEClass.getEStructuralFeatures().get(0);
		createEReference(clazzEClass, CLAZZ__FEATURES);
		clazz_featuresEReference = (EReference) clazzEClass.getEStructuralFeatures().get(1);
		
		featureEClass = createEClass(FEATURE);
		createEAttribute(featureEClass, FEATURE__NAME);
		feature_nameEAttribute = (EAttribute) featureEClass.getEStructuralFeatures().get(0);
		
		methodEClass = createEClass(METHOD);
		createEReference(methodEClass, METHOD__DEPENDENCIES);
		method_dependenciesEReference = (EReference) methodEClass.getEStructuralFeatures().get(0);
		
		attributeEClass = createEClass(ATTRIBUTE);
		
		// Create enums
		
		// Create data types
	}

	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);
		
		// Obtain other dependent packages

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		methodEClass.getESuperTypes().add(this.getFeature());
		attributeEClass.getESuperTypes().add(this.getFeature());

		// Initialize classes, features, and operations; add parameters
		initEClass(clazzModelEClass, ClazzModel.class, "ClazzModel", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClazzModel_Clazzes(), this.getClazz(),  null, 
			"clazzes", null, 0, -1, ClazzModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(clazzEClass, Clazz.class, "Clazz", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getClazz_Name(), ecorePackage.getEString(),
			"name", null, 0, 1, Clazz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		initEReference(getClazz_Features(), this.getFeature(),  null, 
			"features", null, 0, -1, Clazz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(featureEClass, Feature.class, "Feature", IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFeature_Name(), ecorePackage.getEString(),
			"name", null, 0, 1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		
		initEClass(methodEClass, Method.class, "Method", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMethod_Dependencies(), this.getAttribute(),  null, 
			"dependencies", null, 0, -1, Method.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(attributeEClass, Attribute.class, "Attribute", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		
		
		// Initialize enums and add enum literals
		
		// Initialize data types
		
		// Create resource
		createResource(eNS_URI);
	}

} 


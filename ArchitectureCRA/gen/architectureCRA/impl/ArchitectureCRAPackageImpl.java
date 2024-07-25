package architectureCRA.impl;

import architectureCRA.ClassModel;
import architectureCRA.Class;
import architectureCRA.Attribute;
import architectureCRA.Method;
import architectureCRA.Feature;
import architectureCRA.NamedElement;


import architectureCRA.ArchitectureCRAFactory;
import architectureCRA.ArchitectureCRAPackage;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.emoflon.smartemf.runtime.SmartPackageImpl;

public class ArchitectureCRAPackageImpl extends SmartPackageImpl
		implements ArchitectureCRAPackage {
			
	private EClass classModelEClass = null;
	private EReference classModel_classesEReference = null;
	private EReference classModel_featuresEReference = null;
	private EClass classEClass = null;
	private EReference class_encapsulatesEReference = null;
	private EClass attributeEClass = null;
	private EClass methodEClass = null;
	private EReference method_dataDependencyEReference = null;
	private EReference method_functionalDependencyEReference = null;
	private EClass featureEClass = null;
	private EReference feature_isEncapsulatedByEReference = null;
	private EClass namedElementEClass = null;
	private EAttribute namedElement_nameEAttribute = null;
	
	

	private ArchitectureCRAPackageImpl() {
		super(eNS_URI, architectureCRA.ArchitectureCRAFactory.eINSTANCE);
	}

	private static boolean isRegistered = false;
	private boolean isCreated = false;
	private boolean isInitialized = false;

	public static ArchitectureCRAPackage init() {
		if (isRegistered)
			return (ArchitectureCRAPackage) EPackage.Registry.INSTANCE
					.getEPackage(ArchitectureCRAPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredArchitectureCRAPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ArchitectureCRAPackageImpl theArchitectureCRAPackage = registeredArchitectureCRAPackage instanceof ArchitectureCRAPackageImpl
				? (ArchitectureCRAPackageImpl) registeredArchitectureCRAPackage
				: new ArchitectureCRAPackageImpl();

		isRegistered = true;

		// Create package meta-data objects
		theArchitectureCRAPackage.createPackageContents();

		// Initialize created meta-data
		theArchitectureCRAPackage.initializePackageContents();
		
		// Inject internal eOpposites to unidirectional references
		theArchitectureCRAPackage.injectDynamicOpposites();
		
		// Inject external references into foreign packages
		theArchitectureCRAPackage.injectExternalReferences();

		// Mark meta-data to indicate it can't be changed
		theArchitectureCRAPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ArchitectureCRAPackage.eNS_URI,
				theArchitectureCRAPackage);
				
		theArchitectureCRAPackage.fetchDynamicEStructuralFeaturesOfSuperTypes();
		return theArchitectureCRAPackage;
	}

	@Override
	public EClass getClassModel() {
		return classModelEClass;
	}
	@Override
	public EReference getClassModel_Classes() {
		return classModel_classesEReference;	
	}
	@Override
	public EReference getClassModel_Features() {
		return classModel_featuresEReference;	
	}
	@Override
	public EClass getClass_() {
		return classEClass;
	}
	@Override
	public EReference getClass_Encapsulates() {
		return class_encapsulatesEReference;	
	}
	@Override
	public EClass getAttribute() {
		return attributeEClass;
	}
	@Override
	public EClass getMethod() {
		return methodEClass;
	}
	@Override
	public EReference getMethod_DataDependency() {
		return method_dataDependencyEReference;	
	}
	@Override
	public EReference getMethod_FunctionalDependency() {
		return method_functionalDependencyEReference;	
	}
	@Override
	public EClass getFeature() {
		return featureEClass;
	}
	@Override
	public EReference getFeature_IsEncapsulatedBy() {
		return feature_isEncapsulatedByEReference;	
	}
	@Override
	public EClass getNamedElement() {
		return namedElementEClass;
	}
	@Override
	public EAttribute getNamedElement_Name() {
		return namedElement_nameEAttribute;	
	}
	
	

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public architectureCRA.ArchitectureCRAFactory getArchitectureCRAFactory() {
		return (architectureCRA.ArchitectureCRAFactory) getEFactoryInstance();
	}

	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		classModelEClass = createEClass(CLASS_MODEL);
		createEReference(classModelEClass, CLASS_MODEL__CLASSES);
		classModel_classesEReference = (EReference) classModelEClass.getEStructuralFeatures().get(0);
		createEReference(classModelEClass, CLASS_MODEL__FEATURES);
		classModel_featuresEReference = (EReference) classModelEClass.getEStructuralFeatures().get(1);
		
		classEClass = createEClass(CLASS);
		createEReference(classEClass, CLASS__ENCAPSULATES);
		class_encapsulatesEReference = (EReference) classEClass.getEStructuralFeatures().get(0);
		
		attributeEClass = createEClass(ATTRIBUTE);
		
		methodEClass = createEClass(METHOD);
		createEReference(methodEClass, METHOD__DATA_DEPENDENCY);
		method_dataDependencyEReference = (EReference) methodEClass.getEStructuralFeatures().get(0);
		createEReference(methodEClass, METHOD__FUNCTIONAL_DEPENDENCY);
		method_functionalDependencyEReference = (EReference) methodEClass.getEStructuralFeatures().get(1);
		
		featureEClass = createEClass(FEATURE);
		createEReference(featureEClass, FEATURE__IS_ENCAPSULATED_BY);
		feature_isEncapsulatedByEReference = (EReference) featureEClass.getEStructuralFeatures().get(0);
		
		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);
		namedElement_nameEAttribute = (EAttribute) namedElementEClass.getEStructuralFeatures().get(0);
		
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
		classModelEClass.getESuperTypes().add(this.getNamedElement());
		classEClass.getESuperTypes().add(this.getNamedElement());
		attributeEClass.getESuperTypes().add(this.getNamedElement());
		attributeEClass.getESuperTypes().add(this.getFeature());
		methodEClass.getESuperTypes().add(this.getNamedElement());
		methodEClass.getESuperTypes().add(this.getFeature());
		featureEClass.getESuperTypes().add(this.getNamedElement());

		// Initialize classes, features, and operations; add parameters
		initEClass(classModelEClass, ClassModel.class, "ClassModel", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassModel_Classes(), this.getClass_(),  null, 
			"classes", null, 0, -1, ClassModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getClassModel_Features(), this.getFeature(),  null, 
			"features", null, 0, -1, ClassModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
				
		initEClass(classEClass, Class.class, "Class", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClass_Encapsulates(), this.getFeature(), this.getFeature_IsEncapsulatedBy(), 
			"encapsulates", null, 1, -1, Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(attributeEClass, Attribute.class, "Attribute", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		
		initEClass(methodEClass, Method.class, "Method", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMethod_DataDependency(), this.getAttribute(),  null, 
			"dataDependency", null, 0, -1, Method.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMethod_FunctionalDependency(), this.getMethod(),  null, 
			"functionalDependency", null, 0, -1, Method.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(featureEClass, Feature.class, "Feature", IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFeature_IsEncapsulatedBy(), this.getClass_(), this.getClass_Encapsulates(), 
			"isEncapsulatedBy", null, 0, 1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
				
		initEClass(namedElementEClass, NamedElement.class, "NamedElement", IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), ecorePackage.getEString(),
			"name", null, 1, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		
		
		// Initialize enums and add enum literals
		
		// Initialize data types
		
		// Create resource
		createResource(eNS_URI);
	}

} 


package softwareSystem.impl;

import softwareSystem.System;
import softwareSystem.Component;

import softwareSystem.SIL;

import softwareSystem.SoftwareSystemFactory;
import softwareSystem.SoftwareSystemPackage;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.emoflon.smartemf.runtime.SmartPackageImpl;

public class SoftwareSystemPackageImpl extends SmartPackageImpl
		implements SoftwareSystemPackage {
			
	private EClass systemEClass = null;
	private EReference system_subSystemsEReference = null;
	private EReference system_componentsEReference = null;
	private EAttribute system_nameEAttribute = null;
	private EAttribute system_assignedComponentsEAttribute = null;
	private EAttribute system_inverseNumberOfSystemsEAttribute = null;
	private EClass componentEClass = null;
	private EReference component_importsEReference = null;
	private EAttribute component_nameEAttribute = null;
	private EAttribute component_functionPointsEAttribute = null;
	private EAttribute component_silEAttribute = null;
	
	private EEnum sILEEnum = null;
	

	private SoftwareSystemPackageImpl() {
		super(eNS_URI, softwareSystem.SoftwareSystemFactory.eINSTANCE);
	}

	private static boolean isRegistered = false;
	private boolean isCreated = false;
	private boolean isInitialized = false;

	public static SoftwareSystemPackage init() {
		if (isRegistered)
			return (SoftwareSystemPackage) EPackage.Registry.INSTANCE
					.getEPackage(SoftwareSystemPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredSoftwareSystemPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		SoftwareSystemPackageImpl theSoftwareSystemPackage = registeredSoftwareSystemPackage instanceof SoftwareSystemPackageImpl
				? (SoftwareSystemPackageImpl) registeredSoftwareSystemPackage
				: new SoftwareSystemPackageImpl();

		isRegistered = true;

		// Create package meta-data objects
		theSoftwareSystemPackage.createPackageContents();

		// Initialize created meta-data
		theSoftwareSystemPackage.initializePackageContents();
		
		// Inject internal eOpposites to unidirectional references
		theSoftwareSystemPackage.injectDynamicOpposites();
		
		// Inject external references into foreign packages
		theSoftwareSystemPackage.injectExternalReferences();

		// Mark meta-data to indicate it can't be changed
		theSoftwareSystemPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SoftwareSystemPackage.eNS_URI,
				theSoftwareSystemPackage);
				
		theSoftwareSystemPackage.fetchDynamicEStructuralFeaturesOfSuperTypes();
		return theSoftwareSystemPackage;
	}

	@Override
	public EClass getSystem() {
		return systemEClass;
	}
	@Override
	public EReference getSystem_SubSystems() {
		return system_subSystemsEReference;	
	}
	@Override
	public EReference getSystem_Components() {
		return system_componentsEReference;	
	}
	@Override
	public EAttribute getSystem_Name() {
		return system_nameEAttribute;	
	}
	@Override
	public EAttribute getSystem_AssignedComponents() {
		return system_assignedComponentsEAttribute;	
	}
	@Override
	public EAttribute getSystem_InverseNumberOfSystems() {
		return system_inverseNumberOfSystemsEAttribute;	
	}
	@Override
	public EClass getComponent() {
		return componentEClass;
	}
	@Override
	public EReference getComponent_Imports() {
		return component_importsEReference;	
	}
	@Override
	public EAttribute getComponent_Name() {
		return component_nameEAttribute;	
	}
	@Override
	public EAttribute getComponent_FunctionPoints() {
		return component_functionPointsEAttribute;	
	}
	@Override
	public EAttribute getComponent_Sil() {
		return component_silEAttribute;	
	}
	
	@Override
	public EEnum getSIL() {
		return sILEEnum;
	}
	

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public softwareSystem.SoftwareSystemFactory getSoftwareSystemFactory() {
		return (softwareSystem.SoftwareSystemFactory) getEFactoryInstance();
	}

	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		systemEClass = createEClass(SYSTEM);
		createEReference(systemEClass, SYSTEM__SUB_SYSTEMS);
		system_subSystemsEReference = (EReference) systemEClass.getEStructuralFeatures().get(0);
		createEReference(systemEClass, SYSTEM__COMPONENTS);
		system_componentsEReference = (EReference) systemEClass.getEStructuralFeatures().get(1);
		createEAttribute(systemEClass, SYSTEM__NAME);
		system_nameEAttribute = (EAttribute) systemEClass.getEStructuralFeatures().get(2);
		createEAttribute(systemEClass, SYSTEM__ASSIGNED_COMPONENTS);
		system_assignedComponentsEAttribute = (EAttribute) systemEClass.getEStructuralFeatures().get(3);
		createEAttribute(systemEClass, SYSTEM__INVERSE_NUMBER_OF_SYSTEMS);
		system_inverseNumberOfSystemsEAttribute = (EAttribute) systemEClass.getEStructuralFeatures().get(4);
		
		componentEClass = createEClass(COMPONENT);
		createEReference(componentEClass, COMPONENT__IMPORTS);
		component_importsEReference = (EReference) componentEClass.getEStructuralFeatures().get(0);
		createEAttribute(componentEClass, COMPONENT__NAME);
		component_nameEAttribute = (EAttribute) componentEClass.getEStructuralFeatures().get(1);
		createEAttribute(componentEClass, COMPONENT__FUNCTION_POINTS);
		component_functionPointsEAttribute = (EAttribute) componentEClass.getEStructuralFeatures().get(2);
		createEAttribute(componentEClass, COMPONENT__SIL);
		component_silEAttribute = (EAttribute) componentEClass.getEStructuralFeatures().get(3);
		
		// Create enums
		sILEEnum = createEEnum(SIL);
		
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

		// Initialize classes, features, and operations; add parameters
		initEClass(systemEClass, System.class, "System", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSystem_SubSystems(), this.getSystem(),  null, 
			"subSystems", null, 0, -1, System.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSystem_Components(), this.getComponent(),  null, 
			"components", null, 0, -1, System.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSystem_Name(), ecorePackage.getEString(),
			"name", null, 0, 1, System.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		initEAttribute(getSystem_AssignedComponents(), ecorePackage.getEInt(),
			"assignedComponents", "0", 0, 1, System.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		initEAttribute(getSystem_InverseNumberOfSystems(), ecorePackage.getEDouble(),
			"inverseNumberOfSystems", "0.0", 0, 1, System.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		
		initEClass(componentEClass, Component.class, "Component", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponent_Imports(), this.getComponent(),  null, 
			"imports", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_Name(), ecorePackage.getEString(),
			"name", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_FunctionPoints(), ecorePackage.getEInt(),
			"functionPoints", "0", 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_Sil(), this.getSIL(),
			"sil", "LOW", 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		
		
		// Initialize enums and add enum literals
		initEEnum(sILEEnum, SIL.class, "SIL");
		addEEnumLiteral(sILEEnum, softwareSystem.SIL.LOW);
		addEEnumLiteral(sILEEnum, softwareSystem.SIL.MED);
		addEEnumLiteral(sILEEnum, softwareSystem.SIL.HIGH);
		
		// Initialize data types
		
		// Create resource
		createResource(eNS_URI);
	}

} 


package softwareSystem;

import java.lang.String;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EEnum;


import org.emoflon.smartemf.runtime.SmartPackage;

public interface SoftwareSystemPackage extends SmartPackage {

	String eNAME = "softwareSystem";
	String eNS_URI = "platform:/resource/SoftwareSystem/model/softwareSystem.ecore";
	String eNS_PREFIX = "softwareSystem";

	SoftwareSystemPackage eINSTANCE = softwareSystem.impl.SoftwareSystemPackageImpl.init();

	int SYSTEM = 0;
	int SYSTEM__SUB_SYSTEMS = 0;
	int SYSTEM__COMPONENTS = 1;
	int SYSTEM__NAME = 2;
	int SYSTEM_FEATURE_COUNT = 3;
	int SYSTEM_OPERATION_COUNT = 0;
	
	int COMPONENT = 1;
	int COMPONENT__IMPORTS = 3;
	int COMPONENT__NAME = 4;
	int COMPONENT__FUNCTION_POINTS = 5;
	int COMPONENT__SIL = 6;
	int COMPONENT_FEATURE_COUNT = 4;
	int COMPONENT_OPERATION_COUNT = 0;
	
	int SIL = 2;
	

	EClass getSystem();
	EReference getSystem_SubSystems();
	EReference getSystem_Components();
	EAttribute getSystem_Name();
	
	EClass getComponent();
	EReference getComponent_Imports();
	EAttribute getComponent_Name();
	EAttribute getComponent_FunctionPoints();
	EAttribute getComponent_Sil();
	
	EEnum getSIL();
	
	softwareSystem.SoftwareSystemFactory getSoftwareSystemFactory();

	interface Literals {
		
		EClass SYSTEM = eINSTANCE.getSystem();
		
		EReference SYSTEM__SUB_SYSTEMS = eINSTANCE.getSystem_SubSystems();
		
		EReference SYSTEM__COMPONENTS = eINSTANCE.getSystem_Components();
		
		EAttribute SYSTEM__NAME = eINSTANCE.getSystem_Name();
		
		EClass COMPONENT = eINSTANCE.getComponent();
		
		EReference COMPONENT__IMPORTS = eINSTANCE.getComponent_Imports();
		
		EAttribute COMPONENT__NAME = eINSTANCE.getComponent_Name();
		
		EAttribute COMPONENT__FUNCTION_POINTS = eINSTANCE.getComponent_FunctionPoints();
		
		EAttribute COMPONENT__SIL = eINSTANCE.getComponent_Sil();
		
		
		EEnum SIL = eINSTANCE.getSIL();
		
		
	}

} 

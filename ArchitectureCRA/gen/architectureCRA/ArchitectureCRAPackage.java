package architectureCRA;

import java.lang.String;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EEnum;


import org.emoflon.smartemf.runtime.SmartPackage;

public interface ArchitectureCRAPackage extends SmartPackage {

	String eNAME = "architectureCRA";
	String eNS_URI = "platform:/resource/ArchitectureCRA/model/architectureCRA.ecore";
	String eNS_PREFIX = "architectureCRA";

	ArchitectureCRAPackage eINSTANCE = architectureCRA.impl.ArchitectureCRAPackageImpl.init();

	int CLASS_MODEL = 0;
	int CLASS_MODEL__CLASSES = 0;
	int CLASS_MODEL__FEATURES = 1;
	int CLASS_MODEL_FEATURE_COUNT = 3;
	int CLASS_MODEL_OPERATION_COUNT = 0;
	
	int CLASS = 1;
	int CLASS__ENCAPSULATES = 2;
	int CLASS_FEATURE_COUNT = 2;
	int CLASS_OPERATION_COUNT = 0;
	
	int ATTRIBUTE = 2;
	int ATTRIBUTE_FEATURE_COUNT = 2;
	int ATTRIBUTE_OPERATION_COUNT = 0;
	
	int METHOD = 3;
	int METHOD__DATA_DEPENDENCY = 3;
	int METHOD__FUNCTIONAL_DEPENDENCY = 4;
	int METHOD_FEATURE_COUNT = 4;
	int METHOD_OPERATION_COUNT = 0;
	
	int FEATURE = 4;
	int FEATURE__IS_ENCAPSULATED_BY = 5;
	int FEATURE_FEATURE_COUNT = 2;
	int FEATURE_OPERATION_COUNT = 0;
	
	int NAMED_ELEMENT = 5;
	int NAMED_ELEMENT__NAME = 6;
	int NAMED_ELEMENT_FEATURE_COUNT = 1;
	int NAMED_ELEMENT_OPERATION_COUNT = 0;
	
	

	EClass getClassModel();
	EReference getClassModel_Classes();
	EReference getClassModel_Features();
	
	EClass getClass_();
	EReference getClass_Encapsulates();
	
	EClass getAttribute();
	
	EClass getMethod();
	EReference getMethod_DataDependency();
	EReference getMethod_FunctionalDependency();
	
	EClass getFeature();
	EReference getFeature_IsEncapsulatedBy();
	
	EClass getNamedElement();
	EAttribute getNamedElement_Name();
	
	
	architectureCRA.ArchitectureCRAFactory getArchitectureCRAFactory();

	interface Literals {
		
		EClass CLASS_MODEL = eINSTANCE.getClassModel();
		
		EReference CLASS_MODEL__CLASSES = eINSTANCE.getClassModel_Classes();
		
		EReference CLASS_MODEL__FEATURES = eINSTANCE.getClassModel_Features();
		
		EClass CLASS = eINSTANCE.getClass_();
		
		EReference CLASS__ENCAPSULATES = eINSTANCE.getClass_Encapsulates();
		
		EClass ATTRIBUTE = eINSTANCE.getAttribute();
		
		EClass METHOD = eINSTANCE.getMethod();
		
		EReference METHOD__DATA_DEPENDENCY = eINSTANCE.getMethod_DataDependency();
		
		EReference METHOD__FUNCTIONAL_DEPENDENCY = eINSTANCE.getMethod_FunctionalDependency();
		
		EClass FEATURE = eINSTANCE.getFeature();
		
		EReference FEATURE__IS_ENCAPSULATED_BY = eINSTANCE.getFeature_IsEncapsulatedBy();
		
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();
		
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();
		
		
		
		
	}

} 

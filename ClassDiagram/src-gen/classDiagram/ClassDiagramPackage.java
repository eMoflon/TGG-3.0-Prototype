package classDiagram;

import java.lang.String;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EEnum;


import org.emoflon.smartemf.runtime.SmartPackage;

public interface ClassDiagramPackage extends SmartPackage {

	String eNAME = "classDiagram";
	String eNS_URI = "platform:/resource/ClassDiagram/model/classDiagram.ecore";
	String eNS_PREFIX = "classDiagram";

	ClassDiagramPackage eINSTANCE = classDiagram.impl.ClassDiagramPackageImpl.init();

	int CLAZZ_MODEL = 0;
	int CLAZZ_MODEL__CLAZZES = 0;
	int CLAZZ_MODEL_FEATURE_COUNT = 1;
	int CLAZZ_MODEL_OPERATION_COUNT = 0;
	
	int CLAZZ = 1;
	int CLAZZ__NAME = 1;
	int CLAZZ__FEATURES = 2;
	int CLAZZ_FEATURE_COUNT = 2;
	int CLAZZ_OPERATION_COUNT = 0;
	
	int FEATURE = 2;
	int FEATURE__NAME = 3;
	int FEATURE_FEATURE_COUNT = 1;
	int FEATURE_OPERATION_COUNT = 0;
	
	int METHOD = 3;
	int METHOD__DEPENDENCIES = 4;
	int METHOD_FEATURE_COUNT = 2;
	int METHOD_OPERATION_COUNT = 0;
	
	int ATTRIBUTE = 4;
	int ATTRIBUTE_FEATURE_COUNT = 1;
	int ATTRIBUTE_OPERATION_COUNT = 0;
	
	

	EClass getClazzModel();
	EReference getClazzModel_Clazzes();
	
	EClass getClazz();
	EAttribute getClazz_Name();
	EReference getClazz_Features();
	
	EClass getFeature();
	EAttribute getFeature_Name();
	
	EClass getMethod();
	EReference getMethod_Dependencies();
	
	EClass getAttribute();
	
	
	classDiagram.ClassDiagramFactory getClassDiagramFactory();

	interface Literals {
		
		EClass CLAZZ_MODEL = eINSTANCE.getClazzModel();
		
		EReference CLAZZ_MODEL__CLAZZES = eINSTANCE.getClazzModel_Clazzes();
		
		EClass CLAZZ = eINSTANCE.getClazz();
		
		EAttribute CLAZZ__NAME = eINSTANCE.getClazz_Name();
		
		EReference CLAZZ__FEATURES = eINSTANCE.getClazz_Features();
		
		EClass FEATURE = eINSTANCE.getFeature();
		
		EAttribute FEATURE__NAME = eINSTANCE.getFeature_Name();
		
		EClass METHOD = eINSTANCE.getMethod();
		
		EReference METHOD__DEPENDENCIES = eINSTANCE.getMethod_Dependencies();
		
		EClass ATTRIBUTE = eINSTANCE.getAttribute();
		
		
		
		
	}

} 

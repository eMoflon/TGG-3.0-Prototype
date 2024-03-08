package classDiagram;

import classDiagram.ClazzModel;
import classDiagram.Clazz;
import classDiagram.Feature;
import classDiagram.Method;
import classDiagram.Attribute;

import org.eclipse.emf.ecore.EFactory;

public interface ClassDiagramFactory extends EFactory {

	ClassDiagramFactory eINSTANCE = classDiagram.impl.ClassDiagramFactoryImpl.init();
	
	ClazzModel createClazzModel();
	
	Clazz createClazz();
	
	Method createMethod();
	
	Attribute createAttribute();
	
	
	ClassDiagramPackage getClassDiagramPackage();

}

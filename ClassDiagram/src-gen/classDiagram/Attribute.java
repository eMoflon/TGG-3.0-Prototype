package classDiagram;

import classDiagram.ClassDiagramPackage;

import org.emoflon.smartemf.runtime.notification.SmartEMFNotification;
import org.emoflon.smartemf.runtime.SmartObject;
import org.emoflon.smartemf.runtime.collections.*;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface Attribute extends EObject, classDiagram.Feature {
	
    public java.lang.String getName();
    
    public void setName(java.lang.String value);
    

}

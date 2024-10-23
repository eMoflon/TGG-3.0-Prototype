package architectureCRA;

import architectureCRA.ArchitectureCRAPackage;

import org.emoflon.smartemf.runtime.notification.SmartEMFNotification;
import org.emoflon.smartemf.runtime.SmartObject;
import org.emoflon.smartemf.runtime.collections.*;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface ClassModel extends EObject, architectureCRA.NamedElement {
	
    public java.lang.String getName();
    
    public void setName(java.lang.String value);
    
    public SmartESet<architectureCRA.Class> getClasses();
    
    public void setClasses(SmartESet<architectureCRA.Class> value);
    
    public SmartESet<architectureCRA.Feature> getFeatures();
    
    public void setFeatures(SmartESet<architectureCRA.Feature> value);
    

}

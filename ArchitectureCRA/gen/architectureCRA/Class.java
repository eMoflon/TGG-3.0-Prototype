package architectureCRA;

import architectureCRA.ArchitectureCRAPackage;

import org.emoflon.smartemf.runtime.notification.SmartEMFNotification;
import org.emoflon.smartemf.runtime.SmartObject;
import org.emoflon.smartemf.runtime.collections.*;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface Class extends EObject, architectureCRA.NamedElement {
	
    public java.lang.String getName();
    
    public void setName(java.lang.String value);
    
    public LinkedSmartESet<architectureCRA.Feature> getEncapsulates();
    
    public void setEncapsulates(LinkedSmartESet<architectureCRA.Feature> value);
    

}

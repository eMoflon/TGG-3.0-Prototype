package architectureCRA;

import architectureCRA.ArchitectureCRAPackage;

import org.emoflon.smartemf.runtime.notification.SmartEMFNotification;
import org.emoflon.smartemf.runtime.SmartObject;
import org.emoflon.smartemf.runtime.collections.*;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface Method extends EObject, architectureCRA.Feature {
	
    public java.lang.String getName();
    
    public void setName(java.lang.String value);
    
    public architectureCRA.Class getIsEncapsulatedBy();
    
    public void setIsEncapsulatedBy(architectureCRA.Class value);
    
    public LinkedSmartESet<architectureCRA.Attribute> getDataDependency();
    
    public void setDataDependency(LinkedSmartESet<architectureCRA.Attribute> value);
    
    public LinkedSmartESet<architectureCRA.Method> getFunctionalDependency();
    
    public void setFunctionalDependency(LinkedSmartESet<architectureCRA.Method> value);
    

}

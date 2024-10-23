package softwareSystem;

import softwareSystem.SoftwareSystemPackage;

import org.emoflon.smartemf.runtime.notification.SmartEMFNotification;
import org.emoflon.smartemf.runtime.SmartObject;
import org.emoflon.smartemf.runtime.collections.*;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface System extends EObject {
	
    public LinkedSmartESet<softwareSystem.System> getSubSystems();
    
    public void setSubSystems(LinkedSmartESet<softwareSystem.System> value);
    
    public LinkedSmartESet<softwareSystem.Component> getComponents();
    
    public void setComponents(LinkedSmartESet<softwareSystem.Component> value);
    
    public java.lang.String getName();
    
    public void setName(java.lang.String value);
    
    public java.lang.String getTest();
    
    public void setTest(java.lang.String value);
    

}

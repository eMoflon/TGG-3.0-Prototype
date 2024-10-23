package softwareSystem;

import softwareSystem.SoftwareSystemPackage;

import org.emoflon.smartemf.runtime.notification.SmartEMFNotification;
import org.emoflon.smartemf.runtime.SmartObject;
import org.emoflon.smartemf.runtime.collections.*;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface Component extends EObject {
	
    public LinkedSmartESet<softwareSystem.Component> getImports();
    
    public void setImports(LinkedSmartESet<softwareSystem.Component> value);
    
    public java.lang.String getName();
    
    public void setName(java.lang.String value);
    
    public int getFunctionPoints();
    
    public void setFunctionPoints(int value);
    
    public softwareSystem.SIL getSil();
    
    public void setSil(softwareSystem.SIL value);
    

}

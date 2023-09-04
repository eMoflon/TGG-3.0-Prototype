package softwareSystem.impl;

import softwareSystem.SoftwareSystemPackage;
import softwareSystem.SoftwareSystemPackage;

import org.emoflon.smartemf.runtime.*;
import org.emoflon.smartemf.runtime.collections.*;
import org.emoflon.smartemf.persistence.SmartEMFResource;
import org.emoflon.smartemf.runtime.notification.SmartEMFNotification;
import org.emoflon.smartemf.runtime.notification.NotifyStatus;

import java.util.function.Consumer;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

public class SystemImpl extends SmartObject implements softwareSystem.System {

    protected LinkedSmartESet<softwareSystem.System> subSystems = new LinkedSmartESet<softwareSystem.System>(this, SoftwareSystemPackage.Literals.SYSTEM__SUB_SYSTEMS);
    protected LinkedSmartESet<softwareSystem.Component> components = new LinkedSmartESet<softwareSystem.Component>(this, SoftwareSystemPackage.Literals.SYSTEM__COMPONENTS);
    protected java.lang.String name = null;
	
	protected SystemImpl() {
		super(SoftwareSystemPackage.Literals.SYSTEM);
	}
	
    
    @Override
    public LinkedSmartESet<softwareSystem.System> getSubSystems() {
    	return this.subSystems;
    }
    
    @Override
    public void setSubSystems(LinkedSmartESet<softwareSystem.System> value) {
    	throw new UnsupportedOperationException("Set methods for SmartEMF collections are not supported.");
    }
    
    
    @Override
    public LinkedSmartESet<softwareSystem.Component> getComponents() {
    	return this.components;
    }
    
    @Override
    public void setComponents(LinkedSmartESet<softwareSystem.Component> value) {
    	throw new UnsupportedOperationException("Set methods for SmartEMF collections are not supported.");
    }
    
    
    @Override
    public java.lang.String getName() {
    	return this.name;
    }
    
    @Override
    public void setName(java.lang.String value) {
    	Object oldValue = this.name;
    	this.name = value;
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, SoftwareSystemPackage.Literals.SYSTEM__NAME, oldValue, value, -1));
    }
    

    @Override
    public void eSet(EStructuralFeature eFeature, Object newValue){
    	if (SoftwareSystemPackage.Literals.SYSTEM__SUB_SYSTEMS.equals(eFeature)) {
    		setSubSystems((LinkedSmartESet<softwareSystem.System>) newValue); 
    		return;
    	}
    	if (SoftwareSystemPackage.Literals.SYSTEM__COMPONENTS.equals(eFeature)) {
    		setComponents((LinkedSmartESet<softwareSystem.Component>) newValue); 
    		return;
    	}
    	if (SoftwareSystemPackage.Literals.SYSTEM__NAME.equals(eFeature)) {
    		setName((java.lang.String) newValue); 
    		return;
    	}
    	eDynamicSet(eFeature, newValue);
    }
    
    @Override
    public void eUnset(EStructuralFeature eFeature){
    	if (SoftwareSystemPackage.Literals.SYSTEM__SUB_SYSTEMS.equals(eFeature)) {
    		getSubSystems().clear(); 
    		return;
    	}
    	if (SoftwareSystemPackage.Literals.SYSTEM__COMPONENTS.equals(eFeature)) {
    		getComponents().clear(); 
    		return;
    	}
    	if (SoftwareSystemPackage.Literals.SYSTEM__NAME.equals(eFeature)) {
    		setName((java.lang.String)null); 
    		return;
    	}
    	eDynamicUnset(eFeature);
    }

    @Override
    public String toString(){
		StringBuilder b = new StringBuilder();
		b.append(super.toString());
		b.append(" (");
		if (SmartEMFConfig.simpleStringRepresentations()) {
			b.append(getName());
		} else {
			b.append("name: ");
			b.append(getName());
		}
		b.append(")");
		return b.toString();
    }

 	@Override
    public Object eGet(EStructuralFeature eFeature){
    	if (SoftwareSystemPackage.Literals.SYSTEM__SUB_SYSTEMS.equals(eFeature))
    		return getSubSystems();
    	if (SoftwareSystemPackage.Literals.SYSTEM__COMPONENTS.equals(eFeature))
    		return getComponents();
    	if (SoftwareSystemPackage.Literals.SYSTEM__NAME.equals(eFeature))
    		return getName();
    	return eDynamicGet(eFeature);
    }

    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType){
    	throw new UnsupportedOperationException("This method has been deactivated since it is not always safe to use.");
    }
    
    @Override
    public void eInverseAdd(Object otherEnd, EStructuralFeature feature) {
	    if(feature == null)
	    	return;
	    	
    	eDynamicInverseAdd(otherEnd, feature);
	    	}
    	
    @Override
	    	public void eInverseRemove(Object otherEnd, EStructuralFeature feature) {
	    if(feature == null)
	    	return;
	    		    		
    	eDynamicInverseRemove(otherEnd, feature);
	    	}
    
    @Override
    /**
    * This method sets the resource and generates REMOVING_ADAPTER and ADD notifications
    */
    protected void setResourceOfContainments(Consumer<SmartObject> setResourceCall) {
    	for(Object obj : getSubSystems()) {
    		setResourceCall.accept(((SmartObject) obj));
	    		}
    	for(Object obj : getComponents()) {
    		setResourceCall.accept(((SmartObject) obj));
	    		}
	    	}
	    	
	    	@Override
	    	/**
	    	* This method sets the resource and only generates REMOVING_ADAPTER notifications (no ADD messages)
	    	*/
    protected void setResourceOfContainmentsSilently(Resource r) { 		
    	for(Object obj : getSubSystems()) {
    		((SmartObject) obj).setResourceSilently(r);
	    		}
    	for(Object obj : getComponents()) {
    		((SmartObject) obj).setResourceSilently(r);
	    		}
	    	}
}

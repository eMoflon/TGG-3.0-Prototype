package architectureCRA.impl;

import architectureCRA.ArchitectureCRAPackage;
import architectureCRA.ArchitectureCRAPackage;

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

public class ClassImpl extends SmartObject implements architectureCRA.Class {

    protected java.lang.String name = null;
    protected LinkedSmartESet<architectureCRA.Feature> encapsulates = new LinkedSmartESet<architectureCRA.Feature>(this, ArchitectureCRAPackage.Literals.CLASS__ENCAPSULATES);
	
	protected ClassImpl() {
		super(ArchitectureCRAPackage.Literals.CLASS);
	}
	
    
    @Override
    public java.lang.String getName() {
    	return this.name;
    }
    
    @Override
    public void setName(java.lang.String value) {
    	Object oldValue = this.name;
    	this.name = value;
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, ArchitectureCRAPackage.Literals.NAMED_ELEMENT__NAME, oldValue, value, -1));
    }
    
    
    @Override
    public LinkedSmartESet<architectureCRA.Feature> getEncapsulates() {
    	return this.encapsulates;
    }
    
    @Override
    public void setEncapsulates(LinkedSmartESet<architectureCRA.Feature> value) {
    	throw new UnsupportedOperationException("Set methods for SmartEMF collections are not supported.");
    }
    
    private void addEncapsulatesAsInverse(architectureCRA.Feature value) {
    	if(this.encapsulates.addInternal(value, false) == NotifyStatus.SUCCESS_NO_NOTIFICATION) {
    sendNotification(SmartEMFNotification.createAddNotification(this, ArchitectureCRAPackage.Literals.CLASS__ENCAPSULATES, value, -1));
    	} 
    }
    
    private void removeEncapsulatesAsInverse(architectureCRA.Feature value) {
    	encapsulates.removeInternal(value, false, true);
    }

    @Override
    public void eSet(EStructuralFeature eFeature, Object newValue){
    	if (ArchitectureCRAPackage.Literals.NAMED_ELEMENT__NAME.equals(eFeature)) {
    		setName((java.lang.String) newValue); 
    		return;
    	}
    	if (ArchitectureCRAPackage.Literals.CLASS__ENCAPSULATES.equals(eFeature)) {
    		setEncapsulates((LinkedSmartESet<architectureCRA.Feature>) newValue); 
    		return;
    	}
    	eDynamicSet(eFeature, newValue);
    }
    
    @Override
    public void eUnset(EStructuralFeature eFeature){
    	if (ArchitectureCRAPackage.Literals.NAMED_ELEMENT__NAME.equals(eFeature)) {
    		setName((java.lang.String)null); 
    		return;
    	}
    	if (ArchitectureCRAPackage.Literals.CLASS__ENCAPSULATES.equals(eFeature)) {
    		getEncapsulates().clear(); 
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
    	if (ArchitectureCRAPackage.Literals.NAMED_ELEMENT__NAME.equals(eFeature))
    		return getName();
    	if (ArchitectureCRAPackage.Literals.CLASS__ENCAPSULATES.equals(eFeature))
    		return getEncapsulates();
    	return eDynamicGet(eFeature);
    }

    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType){
    	throw new UnsupportedOperationException("This method has been deactivated since it is not always safe to use.");
    }
    
    @Override
    public void eInverseAdd(Object otherEnd, EStructuralFeature feature) {
if (ArchitectureCRAPackage.Literals.CLASS__ENCAPSULATES.equals(feature)) {
	addEncapsulatesAsInverse((architectureCRA.Feature) otherEnd);
 	return;
			        }	
	    if(feature == null)
	    	return;
	    	
    	eDynamicInverseAdd(otherEnd, feature);
	    	}
    	
    @Override
	    	public void eInverseRemove(Object otherEnd, EStructuralFeature feature) {
if (ArchitectureCRAPackage.Literals.CLASS__ENCAPSULATES.equals(feature)) {
	removeEncapsulatesAsInverse((architectureCRA.Feature) otherEnd);
 	return;
			        }
	    if(feature == null)
	    	return;
	    		    		
    	eDynamicInverseRemove(otherEnd, feature);
	    	}
    
    @Override
    /**
    * This method sets the resource and generates REMOVING_ADAPTER and ADD notifications
    */
    protected void setResourceOfContainments(Consumer<SmartObject> setResourceCall) {
	    	}
	    	
	    	@Override
	    	/**
	    	* This method sets the resource and only generates REMOVING_ADAPTER notifications (no ADD messages)
	    	*/
    protected void setResourceOfContainmentsSilently(Resource r) { 		
	    	}
}

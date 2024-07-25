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

public class ClassModelImpl extends SmartObject implements architectureCRA.ClassModel {

    protected java.lang.String name = null;
    protected SmartESet<architectureCRA.Class> classes = new SmartESet<architectureCRA.Class>(this, ArchitectureCRAPackage.Literals.CLASS_MODEL__CLASSES);
    protected SmartESet<architectureCRA.Feature> features = new SmartESet<architectureCRA.Feature>(this, ArchitectureCRAPackage.Literals.CLASS_MODEL__FEATURES);
	
	protected ClassModelImpl() {
		super(ArchitectureCRAPackage.Literals.CLASS_MODEL);
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
    public SmartESet<architectureCRA.Class> getClasses() {
    	return this.classes;
    }
    
    @Override
    public void setClasses(SmartESet<architectureCRA.Class> value) {
    	throw new UnsupportedOperationException("Set methods for SmartEMF collections are not supported.");
    }
    
    
    @Override
    public SmartESet<architectureCRA.Feature> getFeatures() {
    	return this.features;
    }
    
    @Override
    public void setFeatures(SmartESet<architectureCRA.Feature> value) {
    	throw new UnsupportedOperationException("Set methods for SmartEMF collections are not supported.");
    }
    

    @Override
    public void eSet(EStructuralFeature eFeature, Object newValue){
    	if (ArchitectureCRAPackage.Literals.NAMED_ELEMENT__NAME.equals(eFeature)) {
    		setName((java.lang.String) newValue); 
    		return;
    	}
    	if (ArchitectureCRAPackage.Literals.CLASS_MODEL__CLASSES.equals(eFeature)) {
    		setClasses((SmartESet<architectureCRA.Class>) newValue); 
    		return;
    	}
    	if (ArchitectureCRAPackage.Literals.CLASS_MODEL__FEATURES.equals(eFeature)) {
    		setFeatures((SmartESet<architectureCRA.Feature>) newValue); 
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
    	if (ArchitectureCRAPackage.Literals.CLASS_MODEL__CLASSES.equals(eFeature)) {
    		getClasses().clear(); 
    		return;
    	}
    	if (ArchitectureCRAPackage.Literals.CLASS_MODEL__FEATURES.equals(eFeature)) {
    		getFeatures().clear(); 
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
    	if (ArchitectureCRAPackage.Literals.CLASS_MODEL__CLASSES.equals(eFeature))
    		return getClasses();
    	if (ArchitectureCRAPackage.Literals.CLASS_MODEL__FEATURES.equals(eFeature))
    		return getFeatures();
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
    	for(Object obj : getClasses()) {
    		setResourceCall.accept(((SmartObject) obj));
	    		}
    	for(Object obj : getFeatures()) {
    		setResourceCall.accept(((SmartObject) obj));
	    		}
	    	}
	    	
	    	@Override
	    	/**
	    	* This method sets the resource and only generates REMOVING_ADAPTER notifications (no ADD messages)
	    	*/
    protected void setResourceOfContainmentsSilently(Resource r) { 		
    	for(Object obj : getClasses()) {
    		((SmartObject) obj).setResourceSilently(r);
	    		}
    	for(Object obj : getFeatures()) {
    		((SmartObject) obj).setResourceSilently(r);
	    		}
	    	}
}

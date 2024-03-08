package classDiagram.impl;

import classDiagram.ClassDiagramPackage;
import classDiagram.ClassDiagramPackage;

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

public class ClazzImpl extends SmartObject implements classDiagram.Clazz {

    protected java.lang.String name = null;
    protected LinkedSmartESet<classDiagram.Feature> features = new LinkedSmartESet<classDiagram.Feature>(this, ClassDiagramPackage.Literals.CLAZZ__FEATURES);
	
	protected ClazzImpl() {
		super(ClassDiagramPackage.Literals.CLAZZ);
	}
	
    
    @Override
    public java.lang.String getName() {
    	return this.name;
    }
    
    @Override
    public void setName(java.lang.String value) {
    	Object oldValue = this.name;
    	this.name = value;
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, ClassDiagramPackage.Literals.CLAZZ__NAME, oldValue, value, -1));
    }
    
    
    @Override
    public LinkedSmartESet<classDiagram.Feature> getFeatures() {
    	return this.features;
    }
    
    @Override
    public void setFeatures(LinkedSmartESet<classDiagram.Feature> value) {
    	throw new UnsupportedOperationException("Set methods for SmartEMF collections are not supported.");
    }
    

    @Override
    public void eSet(EStructuralFeature eFeature, Object newValue){
    	if (ClassDiagramPackage.Literals.CLAZZ__NAME.equals(eFeature)) {
    		setName((java.lang.String) newValue); 
    		return;
    	}
    	if (ClassDiagramPackage.Literals.CLAZZ__FEATURES.equals(eFeature)) {
    		setFeatures((LinkedSmartESet<classDiagram.Feature>) newValue); 
    		return;
    	}
    	eDynamicSet(eFeature, newValue);
    }
    
    @Override
    public void eUnset(EStructuralFeature eFeature){
    	if (ClassDiagramPackage.Literals.CLAZZ__NAME.equals(eFeature)) {
    		setName((java.lang.String)null); 
    		return;
    	}
    	if (ClassDiagramPackage.Literals.CLAZZ__FEATURES.equals(eFeature)) {
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
    	if (ClassDiagramPackage.Literals.CLAZZ__NAME.equals(eFeature))
    		return getName();
    	if (ClassDiagramPackage.Literals.CLAZZ__FEATURES.equals(eFeature))
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
    	for(Object obj : getFeatures()) {
    		setResourceCall.accept(((SmartObject) obj));
	    		}
	    	}
	    	
	    	@Override
	    	/**
	    	* This method sets the resource and only generates REMOVING_ADAPTER notifications (no ADD messages)
	    	*/
    protected void setResourceOfContainmentsSilently(Resource r) { 		
    	for(Object obj : getFeatures()) {
    		((SmartObject) obj).setResourceSilently(r);
	    		}
	    	}
}

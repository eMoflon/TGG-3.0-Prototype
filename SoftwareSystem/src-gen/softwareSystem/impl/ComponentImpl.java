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

public class ComponentImpl extends SmartObject implements softwareSystem.Component {

    protected LinkedSmartESet<softwareSystem.Component> imports = new LinkedSmartESet<softwareSystem.Component>(this, SoftwareSystemPackage.Literals.COMPONENT__IMPORTS);
    protected java.lang.String name = null;
    protected int functionPoints = 0;
    protected softwareSystem.SIL sil = softwareSystem.SIL.LOW;
	
	protected ComponentImpl() {
		super(SoftwareSystemPackage.Literals.COMPONENT);
	}
	
    
    @Override
    public LinkedSmartESet<softwareSystem.Component> getImports() {
    	return this.imports;
    }
    
    @Override
    public void setImports(LinkedSmartESet<softwareSystem.Component> value) {
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
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, SoftwareSystemPackage.Literals.COMPONENT__NAME, oldValue, value, -1));
    }
    
    
    @Override
    public int getFunctionPoints() {
    	return this.functionPoints;
    }
    
    @Override
    public void setFunctionPoints(int value) {
    	Object oldValue = this.functionPoints;
    	this.functionPoints = value;
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, SoftwareSystemPackage.Literals.COMPONENT__FUNCTION_POINTS, oldValue, value, -1));
    }
    
    
    @Override
    public softwareSystem.SIL getSil() {
    	return this.sil;
    }
    
    @Override
    public void setSil(softwareSystem.SIL value) {
    	Object oldValue = this.sil;
    	this.sil = value;
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, SoftwareSystemPackage.Literals.COMPONENT__SIL, oldValue, value, -1));
    }
    

    @Override
    public void eSet(EStructuralFeature eFeature, Object newValue){
    	if (SoftwareSystemPackage.Literals.COMPONENT__IMPORTS.equals(eFeature)) {
    		setImports((LinkedSmartESet<softwareSystem.Component>) newValue); 
    		return;
    	}
    	if (SoftwareSystemPackage.Literals.COMPONENT__NAME.equals(eFeature)) {
    		setName((java.lang.String) newValue); 
    		return;
    	}
    	if (SoftwareSystemPackage.Literals.COMPONENT__FUNCTION_POINTS.equals(eFeature)) {
    		setFunctionPoints((int) newValue); 
    		return;
    	}
    	if (SoftwareSystemPackage.Literals.COMPONENT__SIL.equals(eFeature)) {
    		setSil((softwareSystem.SIL) newValue); 
    		return;
    	}
    	eDynamicSet(eFeature, newValue);
    }
    
    @Override
    public void eUnset(EStructuralFeature eFeature){
    	if (SoftwareSystemPackage.Literals.COMPONENT__IMPORTS.equals(eFeature)) {
    		getImports().clear(); 
    		return;
    	}
    	if (SoftwareSystemPackage.Literals.COMPONENT__NAME.equals(eFeature)) {
    		setName((java.lang.String)null); 
    		return;
    	}
    	if (SoftwareSystemPackage.Literals.COMPONENT__FUNCTION_POINTS.equals(eFeature)) {
    		setFunctionPoints((int)0); 
    		return;
    	}
    	if (SoftwareSystemPackage.Literals.COMPONENT__SIL.equals(eFeature)) {
    		setSil((softwareSystem.SIL)softwareSystem.SIL.LOW); 
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
			b.append(", ");
			b.append("functionPoints: ");
			b.append(getFunctionPoints());b.append(", ");
			b.append("sil: ");
			b.append(getSil());
		}
		b.append(")");
		return b.toString();
    }

 	@Override
    public Object eGet(EStructuralFeature eFeature){
    	if (SoftwareSystemPackage.Literals.COMPONENT__IMPORTS.equals(eFeature))
    		return getImports();
    	if (SoftwareSystemPackage.Literals.COMPONENT__NAME.equals(eFeature))
    		return getName();
    	if (SoftwareSystemPackage.Literals.COMPONENT__FUNCTION_POINTS.equals(eFeature))
    		return getFunctionPoints();
    	if (SoftwareSystemPackage.Literals.COMPONENT__SIL.equals(eFeature))
    		return getSil();
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
	    	}
	    	
	    	@Override
	    	/**
	    	* This method sets the resource and only generates REMOVING_ADAPTER notifications (no ADD messages)
	    	*/
    protected void setResourceOfContainmentsSilently(Resource r) { 		
	    	}
}

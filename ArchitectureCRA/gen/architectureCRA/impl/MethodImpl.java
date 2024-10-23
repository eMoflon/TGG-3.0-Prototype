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

public class MethodImpl extends SmartObject implements architectureCRA.Method {

    protected java.lang.String name = null;
    protected architectureCRA.Class isEncapsulatedBy = null;
    protected LinkedSmartESet<architectureCRA.Attribute> dataDependency = new LinkedSmartESet<architectureCRA.Attribute>(this, ArchitectureCRAPackage.Literals.METHOD__DATA_DEPENDENCY);
    protected LinkedSmartESet<architectureCRA.Method> functionalDependency = new LinkedSmartESet<architectureCRA.Method>(this, ArchitectureCRAPackage.Literals.METHOD__FUNCTIONAL_DEPENDENCY);
	
	protected MethodImpl() {
		super(ArchitectureCRAPackage.Literals.METHOD);
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
    public architectureCRA.Class getIsEncapsulatedBy() {
    	return this.isEncapsulatedBy;
    }
    
    @Override
    public void setIsEncapsulatedBy(architectureCRA.Class value) {
    	
    	Object oldValue = this.isEncapsulatedBy;
    	
    	if(value == null && oldValue == null)
    		return;
    		
    	if(value != null && value.equals(oldValue))
    		return;
    		
    	
    	
    		        this.isEncapsulatedBy = value;
    		        
    	
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, ArchitectureCRAPackage.Literals.FEATURE__IS_ENCAPSULATED_BY, oldValue, value, -1));
    	        	
    	
    	        	if(oldValue != null) {
    	        		((SmartObject) oldValue).eInverseRemove(this, ArchitectureCRAPackage.Literals.CLASS__ENCAPSULATES);
    	        	}
    	        	if(value != null) {
    	        		((SmartObject) value).eInverseAdd(this, ArchitectureCRAPackage.Literals.CLASS__ENCAPSULATES);
    	        	}
    }
    
    private void setIsEncapsulatedByAsInverse(architectureCRA.Class value) {
			    
			    Object oldValue = this.isEncapsulatedBy;
			    
			    if(value == null && oldValue == null)
			    	return;
			    	
			    if(value != null && value.equals(oldValue))
			    	return;
			    	
			    
			    
			    	        this.isEncapsulatedBy = value;
			    	        
			    
			    
			            	sendNotification(SmartEMFNotification.createSetNotification(this, ArchitectureCRAPackage.Literals.FEATURE__IS_ENCAPSULATED_BY, oldValue, value, -1));
			            	
    }
    
    @Override
    public LinkedSmartESet<architectureCRA.Attribute> getDataDependency() {
    	return this.dataDependency;
    }
    
    @Override
    public void setDataDependency(LinkedSmartESet<architectureCRA.Attribute> value) {
    	throw new UnsupportedOperationException("Set methods for SmartEMF collections are not supported.");
    }
    
    
    @Override
    public LinkedSmartESet<architectureCRA.Method> getFunctionalDependency() {
    	return this.functionalDependency;
    }
    
    @Override
    public void setFunctionalDependency(LinkedSmartESet<architectureCRA.Method> value) {
    	throw new UnsupportedOperationException("Set methods for SmartEMF collections are not supported.");
    }
    

    @Override
    public void eSet(EStructuralFeature eFeature, Object newValue){
    	if (ArchitectureCRAPackage.Literals.NAMED_ELEMENT__NAME.equals(eFeature)) {
    		setName((java.lang.String) newValue); 
    		return;
    	}
    	if (ArchitectureCRAPackage.Literals.FEATURE__IS_ENCAPSULATED_BY.equals(eFeature)) {
    		setIsEncapsulatedBy((architectureCRA.Class) newValue); 
    		return;
    	}
    	if (ArchitectureCRAPackage.Literals.METHOD__DATA_DEPENDENCY.equals(eFeature)) {
    		setDataDependency((LinkedSmartESet<architectureCRA.Attribute>) newValue); 
    		return;
    	}
    	if (ArchitectureCRAPackage.Literals.METHOD__FUNCTIONAL_DEPENDENCY.equals(eFeature)) {
    		setFunctionalDependency((LinkedSmartESet<architectureCRA.Method>) newValue); 
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
    	if (ArchitectureCRAPackage.Literals.FEATURE__IS_ENCAPSULATED_BY.equals(eFeature)) {
    		setIsEncapsulatedBy((architectureCRA.Class)null); 
    		return;
    	}
    	if (ArchitectureCRAPackage.Literals.METHOD__DATA_DEPENDENCY.equals(eFeature)) {
    		getDataDependency().clear(); 
    		return;
    	}
    	if (ArchitectureCRAPackage.Literals.METHOD__FUNCTIONAL_DEPENDENCY.equals(eFeature)) {
    		getFunctionalDependency().clear(); 
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
    	if (ArchitectureCRAPackage.Literals.FEATURE__IS_ENCAPSULATED_BY.equals(eFeature))
    		return getIsEncapsulatedBy();
    	if (ArchitectureCRAPackage.Literals.METHOD__DATA_DEPENDENCY.equals(eFeature))
    		return getDataDependency();
    	if (ArchitectureCRAPackage.Literals.METHOD__FUNCTIONAL_DEPENDENCY.equals(eFeature))
    		return getFunctionalDependency();
    	return eDynamicGet(eFeature);
    }

    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType){
    	throw new UnsupportedOperationException("This method has been deactivated since it is not always safe to use.");
    }
    
    @Override
    public void eInverseAdd(Object otherEnd, EStructuralFeature feature) {
if (ArchitectureCRAPackage.Literals.FEATURE__IS_ENCAPSULATED_BY.equals(feature)) {
setIsEncapsulatedByAsInverse((architectureCRA.Class) otherEnd); 
 	return;
			        }	
	    if(feature == null)
	    	return;
	    	
    	eDynamicInverseAdd(otherEnd, feature);
	    	}
    	
    @Override
	    	public void eInverseRemove(Object otherEnd, EStructuralFeature feature) {
if (ArchitectureCRAPackage.Literals.FEATURE__IS_ENCAPSULATED_BY.equals(feature)) {
setIsEncapsulatedByAsInverse(null); 
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

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

public class MethodImpl extends SmartObject implements classDiagram.Method {

    protected java.lang.String name = null;
    protected LinkedSmartESet<classDiagram.Attribute> dependencies = new LinkedSmartESet<classDiagram.Attribute>(this, ClassDiagramPackage.Literals.METHOD__DEPENDENCIES);
    protected LinkedSmartESet<classDiagram.Method> methodDependency = new LinkedSmartESet<classDiagram.Method>(this, ClassDiagramPackage.Literals.METHOD__METHOD_DEPENDENCY);
	
	protected MethodImpl() {
		super(ClassDiagramPackage.Literals.METHOD);
	}
	
    
    @Override
    public java.lang.String getName() {
    	return this.name;
    }
    
    @Override
    public void setName(java.lang.String value) {
    	Object oldValue = this.name;
    	this.name = value;
    	
    	        	sendNotification(SmartEMFNotification.createSetNotification(this, ClassDiagramPackage.Literals.FEATURE__NAME, oldValue, value, -1));
    }
    
    
    @Override
    public LinkedSmartESet<classDiagram.Attribute> getDependencies() {
    	return this.dependencies;
    }
    
    @Override
    public void setDependencies(LinkedSmartESet<classDiagram.Attribute> value) {
    	throw new UnsupportedOperationException("Set methods for SmartEMF collections are not supported.");
    }
    
    
    @Override
    public LinkedSmartESet<classDiagram.Method> getMethodDependency() {
    	return this.methodDependency;
    }
    
    @Override
    public void setMethodDependency(LinkedSmartESet<classDiagram.Method> value) {
    	throw new UnsupportedOperationException("Set methods for SmartEMF collections are not supported.");
    }
    

    @Override
    public void eSet(EStructuralFeature eFeature, Object newValue){
    	if (ClassDiagramPackage.Literals.FEATURE__NAME.equals(eFeature)) {
    		setName((java.lang.String) newValue); 
    		return;
    	}
    	if (ClassDiagramPackage.Literals.METHOD__DEPENDENCIES.equals(eFeature)) {
    		setDependencies((LinkedSmartESet<classDiagram.Attribute>) newValue); 
    		return;
    	}
    	if (ClassDiagramPackage.Literals.METHOD__METHOD_DEPENDENCY.equals(eFeature)) {
    		setMethodDependency((LinkedSmartESet<classDiagram.Method>) newValue); 
    		return;
    	}
    	eDynamicSet(eFeature, newValue);
    }
    
    @Override
    public void eUnset(EStructuralFeature eFeature){
    	if (ClassDiagramPackage.Literals.FEATURE__NAME.equals(eFeature)) {
    		setName((java.lang.String)null); 
    		return;
    	}
    	if (ClassDiagramPackage.Literals.METHOD__DEPENDENCIES.equals(eFeature)) {
    		getDependencies().clear(); 
    		return;
    	}
    	if (ClassDiagramPackage.Literals.METHOD__METHOD_DEPENDENCY.equals(eFeature)) {
    		getMethodDependency().clear(); 
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
    	if (ClassDiagramPackage.Literals.FEATURE__NAME.equals(eFeature))
    		return getName();
    	if (ClassDiagramPackage.Literals.METHOD__DEPENDENCIES.equals(eFeature))
    		return getDependencies();
    	if (ClassDiagramPackage.Literals.METHOD__METHOD_DEPENDENCY.equals(eFeature))
    		return getMethodDependency();
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

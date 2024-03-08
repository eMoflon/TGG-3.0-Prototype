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

public class ClazzModelImpl extends SmartObject implements classDiagram.ClazzModel {

    protected LinkedSmartESet<classDiagram.Clazz> clazzes = new LinkedSmartESet<classDiagram.Clazz>(this, ClassDiagramPackage.Literals.CLAZZ_MODEL__CLAZZES);
	
	protected ClazzModelImpl() {
		super(ClassDiagramPackage.Literals.CLAZZ_MODEL);
	}
	
    
    @Override
    public LinkedSmartESet<classDiagram.Clazz> getClazzes() {
    	return this.clazzes;
    }
    
    @Override
    public void setClazzes(LinkedSmartESet<classDiagram.Clazz> value) {
    	throw new UnsupportedOperationException("Set methods for SmartEMF collections are not supported.");
    }
    

    @Override
    public void eSet(EStructuralFeature eFeature, Object newValue){
    	if (ClassDiagramPackage.Literals.CLAZZ_MODEL__CLAZZES.equals(eFeature)) {
    		setClazzes((LinkedSmartESet<classDiagram.Clazz>) newValue); 
    		return;
    	}
    	eDynamicSet(eFeature, newValue);
    }
    
    @Override
    public void eUnset(EStructuralFeature eFeature){
    	if (ClassDiagramPackage.Literals.CLAZZ_MODEL__CLAZZES.equals(eFeature)) {
    		getClazzes().clear(); 
    		return;
    	}
    	eDynamicUnset(eFeature);
    }

    @Override
    public String toString(){
		return super.toString();
    }

 	@Override
    public Object eGet(EStructuralFeature eFeature){
    	if (ClassDiagramPackage.Literals.CLAZZ_MODEL__CLAZZES.equals(eFeature))
    		return getClazzes();
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
    	for(Object obj : getClazzes()) {
    		setResourceCall.accept(((SmartObject) obj));
	    		}
	    	}
	    	
	    	@Override
	    	/**
	    	* This method sets the resource and only generates REMOVING_ADAPTER notifications (no ADD messages)
	    	*/
    protected void setResourceOfContainmentsSilently(Resource r) { 		
    	for(Object obj : getClazzes()) {
    		((SmartObject) obj).setResourceSilently(r);
	    		}
	    	}
}

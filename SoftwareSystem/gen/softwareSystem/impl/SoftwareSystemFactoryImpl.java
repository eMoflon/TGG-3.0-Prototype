package softwareSystem.impl;

import softwareSystem.System;
import softwareSystem.Component;

import softwareSystem.SIL;

import softwareSystem.SoftwareSystemFactory;
import softwareSystem.SoftwareSystemPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

public class SoftwareSystemFactoryImpl extends EFactoryImpl implements softwareSystem.SoftwareSystemFactory {

	public static softwareSystem.SoftwareSystemFactory init() {
		try {
			SoftwareSystemFactory theSoftwareSystemFactory = (SoftwareSystemFactory) EPackage.Registry.INSTANCE
					.getEFactory(SoftwareSystemPackage.eNS_URI);
			if (theSoftwareSystemFactory != null) {
				return theSoftwareSystemFactory;
			}
		} catch (java.lang.Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SoftwareSystemFactoryImpl();
	}

	public SoftwareSystemFactoryImpl() {
		super();
	}

	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case SoftwareSystemPackage.SYSTEM:
			return createSystem();
		case SoftwareSystemPackage.COMPONENT:
			return createComponent();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}
	
		@Override
		public Object createFromString(EDataType eDataType, String initialValue) {
			switch (eDataType.getClassifierID()) {
			case SoftwareSystemPackage.SIL:
				return createSILFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
			}
		}

	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
		case SoftwareSystemPackage.SIL:
			return convertSILToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}
	
	@Override
	public softwareSystem.System createSystem() {
		SystemImpl system = new SystemImpl();
		return system;
	}
	@Override
	public softwareSystem.Component createComponent() {
		ComponentImpl component = new ComponentImpl();
		return component;
	}
	
	public SIL createSILFromString(EDataType eDataType, String initialValue) {
		SIL result = SIL.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				
		return result;
	}
	
	public String convertSILToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	@Override
	public SoftwareSystemPackage getSoftwareSystemPackage() {
	return (SoftwareSystemPackage) getEPackage();
	}
} 

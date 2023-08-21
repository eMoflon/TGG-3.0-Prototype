/**
 */
package softwareSystem.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import softwareSystem.Component;
import softwareSystem.SIL;
import softwareSystem.SoftwareSystemPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link softwareSystem.impl.ComponentImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link softwareSystem.impl.ComponentImpl#getName <em>Name</em>}</li>
 *   <li>{@link softwareSystem.impl.ComponentImpl#getFunctionPoints <em>Function Points</em>}</li>
 *   <li>{@link softwareSystem.impl.ComponentImpl#getSil <em>Sil</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ComponentImpl extends MinimalEObjectImpl.Container implements Component {
	/**
	 * The cached value of the '{@link #getImports() <em>Imports</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImports()
	 * @generated
	 * @ordered
	 */
	protected EList<Component> imports;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getFunctionPoints() <em>Function Points</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionPoints()
	 * @generated
	 * @ordered
	 */
	protected static final int FUNCTION_POINTS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getFunctionPoints() <em>Function Points</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionPoints()
	 * @generated
	 * @ordered
	 */
	protected int functionPoints = FUNCTION_POINTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getSil() <em>Sil</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSil()
	 * @generated
	 * @ordered
	 */
	protected static final SIL SIL_EDEFAULT = SIL.LOW;

	/**
	 * The cached value of the '{@link #getSil() <em>Sil</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSil()
	 * @generated
	 * @ordered
	 */
	protected SIL sil = SIL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SoftwareSystemPackage.Literals.COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Component> getImports() {
		if (imports == null) {
			imports = new EObjectResolvingEList<Component>(Component.class, this,
					SoftwareSystemPackage.COMPONENT__IMPORTS);
		}
		return imports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoftwareSystemPackage.COMPONENT__NAME, oldName,
					name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getFunctionPoints() {
		return functionPoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFunctionPoints(int newFunctionPoints) {
		int oldFunctionPoints = functionPoints;
		functionPoints = newFunctionPoints;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoftwareSystemPackage.COMPONENT__FUNCTION_POINTS,
					oldFunctionPoints, functionPoints));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SIL getSil() {
		return sil;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSil(SIL newSil) {
		SIL oldSil = sil;
		sil = newSil == null ? SIL_EDEFAULT : newSil;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoftwareSystemPackage.COMPONENT__SIL, oldSil, sil));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case SoftwareSystemPackage.COMPONENT__IMPORTS:
			return getImports();
		case SoftwareSystemPackage.COMPONENT__NAME:
			return getName();
		case SoftwareSystemPackage.COMPONENT__FUNCTION_POINTS:
			return getFunctionPoints();
		case SoftwareSystemPackage.COMPONENT__SIL:
			return getSil();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case SoftwareSystemPackage.COMPONENT__IMPORTS:
			getImports().clear();
			getImports().addAll((Collection<? extends Component>) newValue);
			return;
		case SoftwareSystemPackage.COMPONENT__NAME:
			setName((String) newValue);
			return;
		case SoftwareSystemPackage.COMPONENT__FUNCTION_POINTS:
			setFunctionPoints((Integer) newValue);
			return;
		case SoftwareSystemPackage.COMPONENT__SIL:
			setSil((SIL) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case SoftwareSystemPackage.COMPONENT__IMPORTS:
			getImports().clear();
			return;
		case SoftwareSystemPackage.COMPONENT__NAME:
			setName(NAME_EDEFAULT);
			return;
		case SoftwareSystemPackage.COMPONENT__FUNCTION_POINTS:
			setFunctionPoints(FUNCTION_POINTS_EDEFAULT);
			return;
		case SoftwareSystemPackage.COMPONENT__SIL:
			setSil(SIL_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case SoftwareSystemPackage.COMPONENT__IMPORTS:
			return imports != null && !imports.isEmpty();
		case SoftwareSystemPackage.COMPONENT__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case SoftwareSystemPackage.COMPONENT__FUNCTION_POINTS:
			return functionPoints != FUNCTION_POINTS_EDEFAULT;
		case SoftwareSystemPackage.COMPONENT__SIL:
			return sil != SIL_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", functionPoints: ");
		result.append(functionPoints);
		result.append(", sil: ");
		result.append(sil);
		result.append(')');
		return result.toString();
	}

} //ComponentImpl

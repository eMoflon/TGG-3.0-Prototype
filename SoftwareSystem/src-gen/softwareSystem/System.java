/**
 */
package softwareSystem;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link softwareSystem.System#getSubSystems <em>Sub Systems</em>}</li>
 *   <li>{@link softwareSystem.System#getComponents <em>Components</em>}</li>
 *   <li>{@link softwareSystem.System#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see softwareSystem.SoftwareSystemPackage#getSystem()
 * @model
 * @generated
 */
public interface System extends EObject {
	/**
	 * Returns the value of the '<em><b>Sub Systems</b></em>' containment reference list.
	 * The list contents are of type {@link softwareSystem.System}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Systems</em>' containment reference list.
	 * @see softwareSystem.SoftwareSystemPackage#getSystem_SubSystems()
	 * @model containment="true"
	 * @generated
	 */
	EList<System> getSubSystems();

	/**
	 * Returns the value of the '<em><b>Components</b></em>' containment reference list.
	 * The list contents are of type {@link softwareSystem.Component}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Components</em>' containment reference list.
	 * @see softwareSystem.SoftwareSystemPackage#getSystem_Components()
	 * @model containment="true"
	 * @generated
	 */
	EList<Component> getComponents();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see softwareSystem.SoftwareSystemPackage#getSystem_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link softwareSystem.System#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // System

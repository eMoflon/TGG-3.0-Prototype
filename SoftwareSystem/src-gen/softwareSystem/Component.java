/**
 */
package softwareSystem;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link softwareSystem.Component#getImports <em>Imports</em>}</li>
 *   <li>{@link softwareSystem.Component#getName <em>Name</em>}</li>
 *   <li>{@link softwareSystem.Component#getFunctionPoints <em>Function Points</em>}</li>
 *   <li>{@link softwareSystem.Component#getSil <em>Sil</em>}</li>
 * </ul>
 *
 * @see softwareSystem.SoftwareSystemPackage#getComponent()
 * @model
 * @generated
 */
public interface Component extends EObject {
	/**
	 * Returns the value of the '<em><b>Imports</b></em>' reference list.
	 * The list contents are of type {@link softwareSystem.Component}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imports</em>' reference list.
	 * @see softwareSystem.SoftwareSystemPackage#getComponent_Imports()
	 * @model
	 * @generated
	 */
	EList<Component> getImports();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see softwareSystem.SoftwareSystemPackage#getComponent_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link softwareSystem.Component#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Function Points</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function Points</em>' attribute.
	 * @see #setFunctionPoints(int)
	 * @see softwareSystem.SoftwareSystemPackage#getComponent_FunctionPoints()
	 * @model
	 * @generated
	 */
	int getFunctionPoints();

	/**
	 * Sets the value of the '{@link softwareSystem.Component#getFunctionPoints <em>Function Points</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function Points</em>' attribute.
	 * @see #getFunctionPoints()
	 * @generated
	 */
	void setFunctionPoints(int value);

	/**
	 * Returns the value of the '<em><b>Sil</b></em>' attribute.
	 * The literals are from the enumeration {@link softwareSystem.SIL}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sil</em>' attribute.
	 * @see softwareSystem.SIL
	 * @see #setSil(SIL)
	 * @see softwareSystem.SoftwareSystemPackage#getComponent_Sil()
	 * @model
	 * @generated
	 */
	SIL getSil();

	/**
	 * Sets the value of the '{@link softwareSystem.Component#getSil <em>Sil</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sil</em>' attribute.
	 * @see softwareSystem.SIL
	 * @see #getSil()
	 * @generated
	 */
	void setSil(SIL value);

} // Component

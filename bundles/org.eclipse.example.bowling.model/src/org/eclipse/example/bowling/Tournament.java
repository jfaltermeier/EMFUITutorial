/**
 */
package org.eclipse.example.bowling;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tournament</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.example.bowling.Tournament#getMatchups <em>Matchups</em>}</li>
 * </ul>
 *
 * @see org.eclipse.example.bowling.BowlingPackage#getTournament()
 * @model
 * @generated
 */
public interface Tournament extends EObject {
	/**
	 * Returns the value of the '<em><b>Matchups</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.example.bowling.Matchup}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Matchups</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Matchups</em>' containment reference list.
	 * @see org.eclipse.example.bowling.BowlingPackage#getTournament_Matchups()
	 * @model containment="true"
	 * @generated
	 */
	EList<Matchup> getMatchups();

} // Tournament

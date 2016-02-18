/**
 */
package org.eclipse.example.bowling;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Matchup</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.example.bowling.Matchup#getGames <em>Games</em>}</li>
 * </ul>
 *
 * @see org.eclipse.example.bowling.BowlingPackage#getMatchup()
 * @model
 * @generated
 */
public interface Matchup extends EObject {
	/**
	 * Returns the value of the '<em><b>Games</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.example.bowling.Game}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.example.bowling.Game#getMatchup <em>Matchup</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Games</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Games</em>' containment reference list.
	 * @see org.eclipse.example.bowling.BowlingPackage#getMatchup_Games()
	 * @see org.eclipse.example.bowling.Game#getMatchup
	 * @model opposite="matchup" containment="true" lower="2" upper="2"
	 * @generated
	 */
	EList<Game> getGames();

} // Matchup

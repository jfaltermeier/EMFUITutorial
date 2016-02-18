/**
 */
package org.eclipse.example.bowling;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.example.bowling.BowlingFactory
 * @model kind="package"
 * @generated
 */
public interface BowlingPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "bowling";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/example/bowling/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "bowling";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BowlingPackage eINSTANCE = org.eclipse.example.bowling.impl.BowlingPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.example.bowling.impl.PlayerImpl <em>Player</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.example.bowling.impl.PlayerImpl
	 * @see org.eclipse.example.bowling.impl.BowlingPackageImpl#getPlayer()
	 * @generated
	 */
	int PLAYER = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAYER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Date Of Birth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAYER__DATE_OF_BIRTH = 1;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAYER__HEIGHT = 2;

	/**
	 * The feature id for the '<em><b>Professional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAYER__PROFESSIONAL = 3;

	/**
	 * The feature id for the '<em><b>Gender</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAYER__GENDER = 4;

	/**
	 * The number of structural features of the '<em>Player</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAYER_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Player</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAYER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.example.bowling.impl.LeagueImpl <em>League</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.example.bowling.impl.LeagueImpl
	 * @see org.eclipse.example.bowling.impl.BowlingPackageImpl#getLeague()
	 * @generated
	 */
	int LEAGUE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAGUE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Players</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAGUE__PLAYERS = 1;

	/**
	 * The number of structural features of the '<em>League</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAGUE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>League</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAGUE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.example.bowling.impl.TournamentImpl <em>Tournament</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.example.bowling.impl.TournamentImpl
	 * @see org.eclipse.example.bowling.impl.BowlingPackageImpl#getTournament()
	 * @generated
	 */
	int TOURNAMENT = 2;

	/**
	 * The feature id for the '<em><b>Matchups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOURNAMENT__MATCHUPS = 0;

	/**
	 * The number of structural features of the '<em>Tournament</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOURNAMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Tournament</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOURNAMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.example.bowling.impl.MatchupImpl <em>Matchup</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.example.bowling.impl.MatchupImpl
	 * @see org.eclipse.example.bowling.impl.BowlingPackageImpl#getMatchup()
	 * @generated
	 */
	int MATCHUP = 3;

	/**
	 * The feature id for the '<em><b>Games</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHUP__GAMES = 0;

	/**
	 * The number of structural features of the '<em>Matchup</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHUP_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Matchup</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATCHUP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.example.bowling.impl.GameImpl <em>Game</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.example.bowling.impl.GameImpl
	 * @see org.eclipse.example.bowling.impl.BowlingPackageImpl#getGame()
	 * @generated
	 */
	int GAME = 4;

	/**
	 * The feature id for the '<em><b>Matchup</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAME__MATCHUP = 0;

	/**
	 * The feature id for the '<em><b>Player</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAME__PLAYER = 1;

	/**
	 * The feature id for the '<em><b>Frames</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAME__FRAMES = 2;

	/**
	 * The number of structural features of the '<em>Game</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAME_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Game</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAME_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.example.bowling.Gender <em>Gender</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.example.bowling.Gender
	 * @see org.eclipse.example.bowling.impl.BowlingPackageImpl#getGender()
	 * @generated
	 */
	int GENDER = 5;


	/**
	 * Returns the meta object for class '{@link org.eclipse.example.bowling.Player <em>Player</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Player</em>'.
	 * @see org.eclipse.example.bowling.Player
	 * @generated
	 */
	EClass getPlayer();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.example.bowling.Player#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.example.bowling.Player#getName()
	 * @see #getPlayer()
	 * @generated
	 */
	EAttribute getPlayer_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.example.bowling.Player#getDateOfBirth <em>Date Of Birth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date Of Birth</em>'.
	 * @see org.eclipse.example.bowling.Player#getDateOfBirth()
	 * @see #getPlayer()
	 * @generated
	 */
	EAttribute getPlayer_DateOfBirth();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.example.bowling.Player#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.eclipse.example.bowling.Player#getHeight()
	 * @see #getPlayer()
	 * @generated
	 */
	EAttribute getPlayer_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.example.bowling.Player#isProfessional <em>Professional</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Professional</em>'.
	 * @see org.eclipse.example.bowling.Player#isProfessional()
	 * @see #getPlayer()
	 * @generated
	 */
	EAttribute getPlayer_Professional();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.example.bowling.Player#getGender <em>Gender</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Gender</em>'.
	 * @see org.eclipse.example.bowling.Player#getGender()
	 * @see #getPlayer()
	 * @generated
	 */
	EAttribute getPlayer_Gender();

	/**
	 * Returns the meta object for class '{@link org.eclipse.example.bowling.League <em>League</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>League</em>'.
	 * @see org.eclipse.example.bowling.League
	 * @generated
	 */
	EClass getLeague();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.example.bowling.League#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.example.bowling.League#getName()
	 * @see #getLeague()
	 * @generated
	 */
	EAttribute getLeague_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.example.bowling.League#getPlayers <em>Players</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Players</em>'.
	 * @see org.eclipse.example.bowling.League#getPlayers()
	 * @see #getLeague()
	 * @generated
	 */
	EReference getLeague_Players();

	/**
	 * Returns the meta object for class '{@link org.eclipse.example.bowling.Tournament <em>Tournament</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tournament</em>'.
	 * @see org.eclipse.example.bowling.Tournament
	 * @generated
	 */
	EClass getTournament();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.example.bowling.Tournament#getMatchups <em>Matchups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Matchups</em>'.
	 * @see org.eclipse.example.bowling.Tournament#getMatchups()
	 * @see #getTournament()
	 * @generated
	 */
	EReference getTournament_Matchups();

	/**
	 * Returns the meta object for class '{@link org.eclipse.example.bowling.Matchup <em>Matchup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Matchup</em>'.
	 * @see org.eclipse.example.bowling.Matchup
	 * @generated
	 */
	EClass getMatchup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.example.bowling.Matchup#getGames <em>Games</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Games</em>'.
	 * @see org.eclipse.example.bowling.Matchup#getGames()
	 * @see #getMatchup()
	 * @generated
	 */
	EReference getMatchup_Games();

	/**
	 * Returns the meta object for class '{@link org.eclipse.example.bowling.Game <em>Game</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Game</em>'.
	 * @see org.eclipse.example.bowling.Game
	 * @generated
	 */
	EClass getGame();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.example.bowling.Game#getMatchup <em>Matchup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Matchup</em>'.
	 * @see org.eclipse.example.bowling.Game#getMatchup()
	 * @see #getGame()
	 * @generated
	 */
	EReference getGame_Matchup();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.example.bowling.Game#getPlayer <em>Player</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Player</em>'.
	 * @see org.eclipse.example.bowling.Game#getPlayer()
	 * @see #getGame()
	 * @generated
	 */
	EReference getGame_Player();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.example.bowling.Game#getFrames <em>Frames</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Frames</em>'.
	 * @see org.eclipse.example.bowling.Game#getFrames()
	 * @see #getGame()
	 * @generated
	 */
	EAttribute getGame_Frames();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.example.bowling.Gender <em>Gender</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Gender</em>'.
	 * @see org.eclipse.example.bowling.Gender
	 * @generated
	 */
	EEnum getGender();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BowlingFactory getBowlingFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.example.bowling.impl.PlayerImpl <em>Player</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.example.bowling.impl.PlayerImpl
		 * @see org.eclipse.example.bowling.impl.BowlingPackageImpl#getPlayer()
		 * @generated
		 */
		EClass PLAYER = eINSTANCE.getPlayer();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLAYER__NAME = eINSTANCE.getPlayer_Name();

		/**
		 * The meta object literal for the '<em><b>Date Of Birth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLAYER__DATE_OF_BIRTH = eINSTANCE.getPlayer_DateOfBirth();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLAYER__HEIGHT = eINSTANCE.getPlayer_Height();

		/**
		 * The meta object literal for the '<em><b>Professional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLAYER__PROFESSIONAL = eINSTANCE.getPlayer_Professional();

		/**
		 * The meta object literal for the '<em><b>Gender</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLAYER__GENDER = eINSTANCE.getPlayer_Gender();

		/**
		 * The meta object literal for the '{@link org.eclipse.example.bowling.impl.LeagueImpl <em>League</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.example.bowling.impl.LeagueImpl
		 * @see org.eclipse.example.bowling.impl.BowlingPackageImpl#getLeague()
		 * @generated
		 */
		EClass LEAGUE = eINSTANCE.getLeague();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LEAGUE__NAME = eINSTANCE.getLeague_Name();

		/**
		 * The meta object literal for the '<em><b>Players</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LEAGUE__PLAYERS = eINSTANCE.getLeague_Players();

		/**
		 * The meta object literal for the '{@link org.eclipse.example.bowling.impl.TournamentImpl <em>Tournament</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.example.bowling.impl.TournamentImpl
		 * @see org.eclipse.example.bowling.impl.BowlingPackageImpl#getTournament()
		 * @generated
		 */
		EClass TOURNAMENT = eINSTANCE.getTournament();

		/**
		 * The meta object literal for the '<em><b>Matchups</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOURNAMENT__MATCHUPS = eINSTANCE.getTournament_Matchups();

		/**
		 * The meta object literal for the '{@link org.eclipse.example.bowling.impl.MatchupImpl <em>Matchup</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.example.bowling.impl.MatchupImpl
		 * @see org.eclipse.example.bowling.impl.BowlingPackageImpl#getMatchup()
		 * @generated
		 */
		EClass MATCHUP = eINSTANCE.getMatchup();

		/**
		 * The meta object literal for the '<em><b>Games</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MATCHUP__GAMES = eINSTANCE.getMatchup_Games();

		/**
		 * The meta object literal for the '{@link org.eclipse.example.bowling.impl.GameImpl <em>Game</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.example.bowling.impl.GameImpl
		 * @see org.eclipse.example.bowling.impl.BowlingPackageImpl#getGame()
		 * @generated
		 */
		EClass GAME = eINSTANCE.getGame();

		/**
		 * The meta object literal for the '<em><b>Matchup</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GAME__MATCHUP = eINSTANCE.getGame_Matchup();

		/**
		 * The meta object literal for the '<em><b>Player</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GAME__PLAYER = eINSTANCE.getGame_Player();

		/**
		 * The meta object literal for the '<em><b>Frames</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GAME__FRAMES = eINSTANCE.getGame_Frames();

		/**
		 * The meta object literal for the '{@link org.eclipse.example.bowling.Gender <em>Gender</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.example.bowling.Gender
		 * @see org.eclipse.example.bowling.impl.BowlingPackageImpl#getGender()
		 * @generated
		 */
		EEnum GENDER = eINSTANCE.getGender();

	}

} //BowlingPackage

package org.eclipse.example.bowling.dataservice.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.example.bowling.BowlingFactory;
import org.eclipse.example.bowling.Game;
import org.eclipse.example.bowling.Gender;
import org.eclipse.example.bowling.League;
import org.eclipse.example.bowling.Matchup;
import org.eclipse.example.bowling.Player;
import org.eclipse.example.bowling.Tournament;
import org.eclipse.example.bowling.dataservice.BowlingDataService;
import org.osgi.service.component.annotations.Component;

@Component(name = "BowlingDatabaseServiceImpl", service = BowlingDataService.class)
public class BowlingDataServiceImpl implements BowlingDataService {

	private League league;
	private Tournament tournament;
	private Resource resource;
	private ComposedAdapterFactory composedAdapterFactory;
	private EditingDomain editingDomain;

	@Override
	public League getLeage() {
		if (league == null) {
			league = createExampleLeague();
			getResource().getContents().add(league);
		}
		return league;
	}

	@Override
	public Tournament getTournament() {
		if (tournament == null) {
			tournament = createExampleTournament();
			getResource().getContents().add(tournament);
		}
		return tournament;
	}

	private Resource getResource() {
		if (resource == null) {
			EditingDomain editingDomain = getEditingDomain();
			resource = editingDomain.createResource("bowling.xmi");
		}
		return resource;
	}

	protected AdapterFactory getAdapterFactory() {
		if (composedAdapterFactory == null) {
			composedAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		}
		return composedAdapterFactory;
	}

	private League createExampleLeague() {
		League league = BowlingFactory.eINSTANCE.createLeague();
		league.setName("Example League");

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			Player player1 = BowlingFactory.eINSTANCE.createPlayer();
			player1.setName("Leonard Hofstadter");
			player1.setGender(Gender.MALE);
			player1.setHeight(165);
			player1.setDateOfBirth(dateFormat.parse("1981-01-01"));
			player1.setProfessional(false);

			Player player2 = BowlingFactory.eINSTANCE.createPlayer();
			player2.setName("Sheldon Cooper");
			player2.setGender(Gender.MALE);
			player2.setHeight(185);
			player2.setDateOfBirth(dateFormat.parse("1981-02-02"));
			player2.setProfessional(false);

			Player player3 = BowlingFactory.eINSTANCE.createPlayer();
			player3.setName("Howard Wolowitz");
			player3.setGender(Gender.MALE);
			player3.setHeight(160);
			player3.setDateOfBirth(dateFormat.parse("1982-01-01"));
			player3.setProfessional(false);

			Player player4 = BowlingFactory.eINSTANCE.createPlayer();
			player4.setName("Amy Farrah Fowler");
			player4.setGender(Gender.FEMALE);
			player4.setHeight(175);
			player4.setDateOfBirth(dateFormat.parse("1981-03-03"));
			player4.setProfessional(false);

			Player player5 = BowlingFactory.eINSTANCE.createPlayer();
			player5.setName("Penny");
			player5.setGender(Gender.FEMALE);
			player5.setHeight(170);
			player5.setDateOfBirth(dateFormat.parse("1983-01-01"));
			player5.setProfessional(true);

			league.getPlayers().add(player1);
			league.getPlayers().add(player2);
			league.getPlayers().add(player3);
			league.getPlayers().add(player4);
			league.getPlayers().add(player5);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return league;
	}

	private Tournament createExampleTournament() {
		Tournament tournament = BowlingFactory.eINSTANCE.createTournament();

		Matchup matchup1 = BowlingFactory.eINSTANCE.createMatchup();

		Game game1_1 = BowlingFactory.eINSTANCE.createGame();
		game1_1.setPlayer(getLeage().getPlayers().get(0));
		game1_1.getFrames().add(8);
		game1_1.getFrames().add(7);
		game1_1.getFrames().add(6);
		matchup1.getGames().add(game1_1);

		Game game1_2 = BowlingFactory.eINSTANCE.createGame();
		game1_2.setPlayer(getLeage().getPlayers().get(1));
		game1_2.getFrames().add(9);
		game1_2.getFrames().add(6);
		game1_2.getFrames().add(7);
		matchup1.getGames().add(game1_2);

		Matchup matchup2 = BowlingFactory.eINSTANCE.createMatchup();

		Game game2_1 = BowlingFactory.eINSTANCE.createGame();
		game2_1.setPlayer(getLeage().getPlayers().get(2));
		game2_1.getFrames().add(8);
		game2_1.getFrames().add(7);
		game2_1.getFrames().add(6);
		matchup2.getGames().add(game2_1);

		Game game2_2 = BowlingFactory.eINSTANCE.createGame();
		game2_2.setPlayer(getLeage().getPlayers().get(1));
		game2_2.getFrames().add(9);
		game2_2.getFrames().add(6);
		game2_2.getFrames().add(7);
		matchup2.getGames().add(game2_2);

		tournament.getMatchups().add(matchup1);
		tournament.getMatchups().add(matchup2);
		return tournament;
	}

	@Override
	public EditingDomain getEditingDomain() {
		if (editingDomain == null) {
			editingDomain = new AdapterFactoryEditingDomain(getAdapterFactory(), new BasicCommandStack());
		}
		return editingDomain;
	}

}

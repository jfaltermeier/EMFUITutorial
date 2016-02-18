
package org.eclipse.example.bowling.application.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.example.bowling.BowlingFactory;
import org.eclipse.example.bowling.BowlingPackage;
import org.eclipse.example.bowling.Tournament;
import org.eclipse.example.bowling.dataservice.BowlingDataService;

public class AddMatchupHandler {

	private static final BowlingFactory BOWLING_FACTORY = BowlingFactory.eINSTANCE;
	private static final EReference TOURNAMENT_MATCHUPS = BowlingPackage.eINSTANCE.getTournament_Matchups();

	@Execute
	public void execute(BowlingDataService dataService) {
		Tournament tournament = dataService.getTournament();
		EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(tournament);
		Command command = AddCommand.create(editingDomain, tournament, TOURNAMENT_MATCHUPS,
				BOWLING_FACTORY.createMatchup());
		editingDomain.getCommandStack().execute(command);

	}

}
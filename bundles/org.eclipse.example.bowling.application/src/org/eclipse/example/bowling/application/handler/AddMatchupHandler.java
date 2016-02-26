
package org.eclipse.example.bowling.application.handler;

import static org.eclipse.example.bowling.BowlingPackage.Literals.TOURNAMENT__MATCHUPS;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.example.bowling.BowlingFactory;
import org.eclipse.example.bowling.Tournament;
import org.eclipse.example.bowling.dataservice.BowlingDataService;

public class AddMatchupHandler {

	private static final BowlingFactory BOWLING_FACTORY = BowlingFactory.eINSTANCE;

	@Execute
	public void execute(BowlingDataService dataService) {
		Tournament tournament = dataService.getTournament();
		// Task: get editing domain from dataService
		// Task: create AddCommand for adding a Matchup to tournament
		// Task: execute AddCommand on the editing domain's command stack
		EditingDomain editingDomain = dataService.getEditingDomain();
		Command command = AddCommand.create(editingDomain, tournament, TOURNAMENT__MATCHUPS,
				BOWLING_FACTORY.createMatchup());
		editingDomain.getCommandStack().execute(command);
	}

}
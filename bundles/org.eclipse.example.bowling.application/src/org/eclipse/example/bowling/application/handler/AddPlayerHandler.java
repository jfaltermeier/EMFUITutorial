
package org.eclipse.example.bowling.application.handler;

import static org.eclipse.example.bowling.BowlingPackage.Literals.LEAGUE__PLAYERS;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.example.bowling.BowlingFactory;
import org.eclipse.example.bowling.League;
import org.eclipse.example.bowling.dataservice.BowlingDataService;

public class AddPlayerHandler {

	private static final BowlingFactory BOWLING_FACTORY = BowlingFactory.eINSTANCE;

	@Execute
	public void execute(BowlingDataService dataService) {
		League leage = dataService.getLeage();
		EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(leage);
		Command command = AddCommand.create(editingDomain, leage, LEAGUE__PLAYERS, BOWLING_FACTORY.createPlayer());
		editingDomain.getCommandStack().execute(command);
	}

}
 
package org.eclipse.example.bowling.application.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.example.bowling.BowlingFactory;
import org.eclipse.example.bowling.BowlingPackage;
import org.eclipse.example.bowling.League;
import org.eclipse.example.bowling.dataservice.BowlingDataService;

public class AddPlayerHandler {
	
	private static final BowlingFactory BOWLING_FACTORY = BowlingFactory.eINSTANCE;
	private static final EReference LEAGUE_PLAYERS = BowlingPackage.eINSTANCE.getLeague_Players();

	@Execute
	public void execute(BowlingDataService dataService) {
		League leage = dataService.getLeage();
		EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(leage);
		Command command = AddCommand.create(editingDomain, leage, LEAGUE_PLAYERS, BOWLING_FACTORY.createPlayer());
		editingDomain.getCommandStack().execute(command);
	}
		
}

package org.eclipse.example.bowling.application.handler;

import static org.eclipse.example.bowling.BowlingPackage.Literals.MATCHUP__GAMES;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.example.bowling.BowlingFactory;
import org.eclipse.example.bowling.Matchup;

public class AddGameHandler {

	private static final BowlingFactory BOWLING_FACTORY = BowlingFactory.eINSTANCE;

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) Matchup parent) {
		EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(parent);
		Command command = AddCommand.create(editingDomain, parent, MATCHUP__GAMES, BOWLING_FACTORY.createGame());
		editingDomain.getCommandStack().execute(command);
	}

	@CanExecute
	public boolean canExecute(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) Matchup parent) {
		return parent != null;
	}

}
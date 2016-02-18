
package org.eclipse.example.bowling.application.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.example.bowling.Game;

public class DeleteGameHandler {

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) Game game) {
		EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(game);
		Command command = DeleteCommand.create(editingDomain, game);
		editingDomain.getCommandStack().execute(command);
	}

	@CanExecute
	public boolean canExecute(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) Game game) {
		return game != null;
	}

}
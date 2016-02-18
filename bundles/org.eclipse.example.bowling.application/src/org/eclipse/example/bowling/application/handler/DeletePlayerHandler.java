
package org.eclipse.example.bowling.application.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.example.bowling.Player;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;

public class DeletePlayerHandler {
	
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) Player player) {
		EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(player);
		Command command = DeleteCommand.create(editingDomain, player);
		editingDomain.getCommandStack().execute(command);
	}

	@CanExecute
	public boolean canExecute(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) Player player) {
		return player != null;
	}

}
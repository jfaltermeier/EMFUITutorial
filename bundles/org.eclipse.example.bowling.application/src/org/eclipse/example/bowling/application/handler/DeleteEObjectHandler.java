
package org.eclipse.example.bowling.application.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.example.bowling.dataservice.BowlingDataService;

public class DeleteEObjectHandler {

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) EObject eObject, BowlingDataService dataService) {
		// Task: get editing domain from dataService
		// Task: create DeleteCommand for deleting eObject
		// Task: execute DeleteCommand on the editing domain's command stack
		EditingDomain editingDomain = dataService.getEditingDomain();
		Command command = DeleteCommand.create(editingDomain, eObject);
		editingDomain.getCommandStack().execute(command);
	}

	@CanExecute
	public boolean canExecute(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) EObject eObject) {
		return eObject != null;
	}
}
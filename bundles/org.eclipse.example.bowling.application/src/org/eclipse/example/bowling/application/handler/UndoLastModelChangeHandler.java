
package org.eclipse.example.bowling.application.handler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.example.bowling.dataservice.BowlingDataService;

public class UndoLastModelChangeHandler {

	@Execute
	public void execute(BowlingDataService dataService) {
		EditingDomain editingDomain = dataService.getEditingDomain();
		editingDomain.getCommandStack().undo();
	}

	@CanExecute
	public boolean canExecute(BowlingDataService dataService) {
		EditingDomain editingDomain = dataService.getEditingDomain();
		return editingDomain.getCommandStack().canUndo();
	}

}
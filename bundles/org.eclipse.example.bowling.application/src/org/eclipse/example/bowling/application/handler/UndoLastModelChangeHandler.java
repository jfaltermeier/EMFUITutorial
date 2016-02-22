
package org.eclipse.example.bowling.application.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledItem;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.example.bowling.League;
import org.eclipse.example.bowling.dataservice.BowlingDataService;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;

public class UndoLastModelChangeHandler {

	private BowlingDataService dataService;

	@Inject
	public UndoLastModelChangeHandler(BowlingDataService dataService) {
		this.dataService = dataService;
	}

	@Execute
	public void execute(MHandledItem handledItem) {
		getEditingDomain().getCommandStack().undo();
		handledItem.setEnabled(canExecute());
	}

	@CanExecute
	public boolean canExecute() {
		EditingDomain editingDomain = getEditingDomain();
		if (editingDomain != null) {
			return editingDomain.getCommandStack().canUndo();
		}
		return false;
	}

	private EditingDomain getEditingDomain() {
		League league = dataService.getLeage();
		if (league != null) {
			EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(league);
			if (editingDomain != null) {
				return editingDomain;
			}
		}
		return null;
	}

}
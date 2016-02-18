package org.eclipse.example.bowling.application.parts;

import javax.inject.Inject;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.example.bowling.dataservice.BowlingDataService;
import org.eclipse.jface.viewers.DoubleClickEvent;

public class TournamentNavigatorPart extends AbstractNavigatorPart {

	private static final String POPUPMENU_ID = "org.eclipse.example.bowling.application.popupmenu.trounamentviewer";

	@Inject
	private BowlingDataService dataService;

	@Override
	protected EObject getInput() {
		return dataService.getTournament();
	}

	@Override
	protected boolean supportDragAndDrop() {
		return true;
	}

	@Override
	protected void handleDoubleClick(DoubleClickEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getMenuId() {
		return POPUPMENU_ID;
	}

}
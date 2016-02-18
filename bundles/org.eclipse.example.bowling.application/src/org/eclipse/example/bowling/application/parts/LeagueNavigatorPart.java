package org.eclipse.example.bowling.application.parts;

import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.example.bowling.dataservice.BowlingDataService;
import org.eclipse.jface.viewers.DoubleClickEvent;

public class LeagueNavigatorPart extends AbstractNavigatorPart {

	private static final String PLAYER_PART = "org.eclipse.example.bowling.application.partdescriptor.player";
	private static final String POPUPMENU_ID = "org.eclipse.example.bowling.application.popupmenu.leagueviewer";

	@Inject
	private BowlingDataService dataService;

	@Inject
	private EPartService partService;

	@Override
	protected EObject getInput() {
		return dataService.getLeage();
	}

	@Override
	protected void handleDoubleClick(DoubleClickEvent event) {
		closePersonPart();
		openPersonPart();
	}

	protected String getMenuId() {
		return POPUPMENU_ID;
	}

	private void closePersonPart() {
		MPart personPart = partService.findPart(PLAYER_PART);
		if (personPart != null) {
			partService.hidePart(personPart, true);
		}
	}

	private MPart openPersonPart() {
		return partService.showPart(PLAYER_PART, PartState.ACTIVATE);
	}
}
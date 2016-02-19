package org.eclipse.example.bowling.application.parts;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.example.bowling.Game;
import org.eclipse.example.bowling.dataservice.BowlingDataService;
import org.eclipse.jface.viewers.DoubleClickEvent;

public class TournamentNavigatorPart extends AbstractNavigatorPart {

	private static final String GAME_PART_ID = "org.eclipse.example.bowling.application.partdescriptor.game";
	private static final String POPUPMENU_ID = "org.eclipse.example.bowling.application.popupmenu.trounamentviewer";

	@Inject
	private BowlingDataService dataService;

	@Inject
	private EPartService partService;

	private Game selectedGame;

	@Override
	protected EObject getInput() {
		return dataService.getTournament();
	}

	@Override
	protected boolean supportDragAndDrop() {
		return true;
	}

	@Inject
	public void setSelectedGame(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) Game game) {
		this.selectedGame = game;
	}

	@Override
	protected void handleDoubleClick(DoubleClickEvent event) {
		if (selectedGame != null) {
			closeGamePart();
			openPersonPart();
		}
	}

	private void closeGamePart() {
		MPart gamePart = partService.findPart(GAME_PART_ID);
		if (gamePart != null) {
			partService.hidePart(gamePart, true);
		}
	}

	private MPart openPersonPart() {
		return partService.showPart(GAME_PART_ID, PartState.ACTIVATE);
	}

	@Override
	protected String getMenuId() {
		return POPUPMENU_ID;
	}

}
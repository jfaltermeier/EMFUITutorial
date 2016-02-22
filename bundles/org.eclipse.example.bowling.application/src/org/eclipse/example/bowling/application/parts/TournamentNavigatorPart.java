package org.eclipse.example.bowling.application.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.example.bowling.Game;
import org.eclipse.example.bowling.Tournament;
import org.eclipse.example.bowling.dataservice.BowlingDataService;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

public class TournamentNavigatorPart extends AbstractNavigatorPart {

	private static final String GAME_PART_ID = "org.eclipse.example.bowling.application.partdescriptor.game";
	private static final String POPUPMENU_ID = "org.eclipse.example.bowling.application.popupmenu.trounamentviewer";

	private Game selectedGame;

	private EPartService partService;

	private TreeViewer viewer;
	private ComposedAdapterFactory composedAdapterFactory;

	@Inject
	public TournamentNavigatorPart(EPartService partService) {
		super();
		this.partService = partService;
	}

	@PostConstruct
	public void postConstruct(Composite parent, BowlingDataService dataService, EMenuService menuService,
			ESelectionService selectionService) {
		parent.setLayout(new FillLayout());
		viewer = new TreeViewer(parent, SWT.SINGLE);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		viewer.getControl().setLayoutData(gridData);

		// Task: set content and label provider
		composedAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		viewer.setContentProvider(new AdapterFactoryContentProvider(composedAdapterFactory));
		viewer.setLabelProvider(new AdapterFactoryLabelProvider(composedAdapterFactory));

		// Task: set input provider
		Tournament tournament = dataService.getTournament();
		viewer.setInput(tournament);

		enableDragAndDrop(viewer, AdapterFactoryEditingDomain.getEditingDomainFor(tournament));

		installSelectionForwardingListener(viewer, selectionService);
		menuService.registerContextMenu(viewer.getControl(), POPUPMENU_ID);
		viewer.addDoubleClickListener(new ExpandingDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				super.doubleClick(event);
				handleDoubleClick(event);
			}
		});
	}

	@PreDestroy
	public void dispose() {
		if (composedAdapterFactory != null) {
			composedAdapterFactory.dispose();
		}
	}

	public void setFocus() {
		if (viewer != null) {
			return;
		}
		viewer.getControl().setFocus();
	}

	@Inject
	public void setSelectedGame(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) Game game) {
		this.selectedGame = game;
	}

	private void handleDoubleClick(DoubleClickEvent event) {
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

}
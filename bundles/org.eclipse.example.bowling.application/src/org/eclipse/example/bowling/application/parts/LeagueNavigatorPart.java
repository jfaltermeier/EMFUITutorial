package org.eclipse.example.bowling.application.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.example.bowling.League;
import org.eclipse.example.bowling.dataservice.BowlingDataService;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class LeagueNavigatorPart extends AbstractNavigatorPart {

	private static final String PLAYER_PART_ID = "org.eclipse.example.bowling.application.partdescriptor.player";

	private EPartService partService;

	private TreeViewer viewer;
	private ComposedAdapterFactory composedAdapterFactory;

	@Inject
	public LeagueNavigatorPart(EPartService partService) {
		super();
		this.partService = partService;
	}

	@PostConstruct
	public void postConstruct(Composite parent, BowlingDataService dataService, ESelectionService selectionService) {
		viewer = new TreeViewer(parent, SWT.SINGLE);

		// Task: set content and label provider
		composedAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		viewer.setContentProvider(new AdapterFactoryContentProvider(composedAdapterFactory));
		viewer.setLabelProvider(new AdapterFactoryLabelProvider(composedAdapterFactory));

		// Task: set input provider
		League league = dataService.getLeage();
		viewer.setInput(league);

		enableDragAndDrop(viewer, dataService.getEditingDomain());

		installSelectionForwardingListener(viewer, selectionService);
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

	private void handleDoubleClick(DoubleClickEvent event) {
		closePersonPart();
		openPersonPart();
	}

	private void closePersonPart() {
		MPart personPart = partService.findPart(PLAYER_PART_ID);
		if (personPart != null) {
			partService.hidePart(personPart, true);
		}
	}

	private MPart openPersonPart() {
		return partService.showPart(PLAYER_PART_ID, PartState.ACTIVATE);
	}
}
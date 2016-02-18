package org.eclipse.example.bowling.application.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

public abstract class AbstractNavigatorPart {

	@Inject
	private ESelectionService selectionService;

	private TreeViewer viewer;
	private ComposedAdapterFactory composedAdapterFactory;

	public AbstractNavigatorPart() {
		super();
	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new FillLayout());
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.BORDER);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		viewer.getControl().setLayoutData(gridData);

		viewer.setContentProvider(new AdapterFactoryContentProvider(getAdapterFactory()));
		viewer.setLabelProvider(new AdapterFactoryLabelProvider(getAdapterFactory()));
		viewer.setInput(getInput());
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				TreeViewer viewer = (TreeViewer) event.getViewer();
				IStructuredSelection thisSelection = (IStructuredSelection) event.getSelection();
				Object selectedNode = thisSelection.getFirstElement();
				viewer.setExpandedState(selectedNode, !viewer.getExpandedState(selectedNode));
				handleDoubleClick(event);
			}
		});

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectionService.setSelection(((IStructuredSelection) event.getSelection()).getFirstElement());
			}
		});

		final MenuManager menuMgr = new MenuManager();
		final Menu menu = menuMgr.createContextMenu(viewer.getControl());
		// menuMgr.addMenuListener(new MainPartMenuListener(getViewer(),
		// databaseService));
		menuMgr.setRemoveAllWhenShown(true);
		viewer.getControl().setMenu(menu);

		if (supportDragAndDrop()) {
			EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(getInput());
			if (editingDomain != null) {
				int dndOps = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
				Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance() };
				viewer.addDragSupport(dndOps, transfers, new ViewerDragAdapter(viewer));
				viewer.addDropSupport(dndOps, transfers, new EditingDomainViewerDropAdapter(editingDomain, viewer));
			}
		}
	}

	abstract protected EObject getInput();

	abstract protected void handleDoubleClick(DoubleClickEvent event);

	protected boolean supportDragAndDrop() {
		return false;
	}

	@Focus
	public void setFocus() {
		if (viewer != null) {
			return;
		}
		viewer.getControl().setFocus();
	}

	protected AdapterFactory getAdapterFactory() {
		if (composedAdapterFactory == null) {
			composedAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		}
		return composedAdapterFactory;
	}

}
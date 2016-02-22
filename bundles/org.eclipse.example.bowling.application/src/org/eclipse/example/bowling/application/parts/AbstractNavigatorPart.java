package org.eclipse.example.bowling.application.parts;

import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;

public abstract class AbstractNavigatorPart {

	protected static class ExpandingDoubleClickListener implements IDoubleClickListener {
		@Override
		public void doubleClick(DoubleClickEvent event) {
			TreeViewer viewer = (TreeViewer) event.getViewer();
			IStructuredSelection thisSelection = (IStructuredSelection) event.getSelection();
			Object selectedNode = thisSelection.getFirstElement();
			viewer.setExpandedState(selectedNode, !viewer.getExpandedState(selectedNode));
		}
	}

	protected void installSelectionForwardingListener(TreeViewer viewer, ESelectionService selectionService) {
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectionService.setSelection(((IStructuredSelection) event.getSelection()).getFirstElement());
			}
		});
	}

	protected void enableDragAndDrop(TreeViewer viewer, EditingDomain editingDomain) {
		if (editingDomain != null) {
			int dndOps = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
			Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance() };
			viewer.addDragSupport(dndOps, transfers, new ViewerDragAdapter(viewer));
			viewer.addDropSupport(dndOps, transfers, new EditingDomainViewerDropAdapter(editingDomain, viewer));
		}
	}

}
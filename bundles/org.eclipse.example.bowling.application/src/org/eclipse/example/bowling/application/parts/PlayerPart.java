package org.eclipse.example.bowling.application.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecp.ui.view.ECPRendererException;
import org.eclipse.emf.ecp.ui.view.swt.ECPSWTView;
import org.eclipse.emf.ecp.ui.view.swt.ECPSWTViewRenderer;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.example.bowling.Player;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class PlayerPart {

	private Player player;
	private AdapterImpl nameAdapter;
	private ComposedAdapterFactory composedAdapterFactory;

	private ECPSWTView ecpswtView;

	@PostConstruct
	public void createComposite(Composite parent, MPart part,
			@Named(IServiceConstants.ACTIVE_SELECTION) Player player) {
		if (player == null) {
			new Label(parent, SWT.NULL).setText("No player selected.");
			return;
		}

		createPartNameUpdater(player, part);

		try {
			Composite composite = new Composite(parent, SWT.NONE);
			composite.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
			GridLayoutFactory.fillDefaults().applyTo(composite);
			ecpswtView = ECPSWTViewRenderer.INSTANCE.render(composite, player);
			GridDataFactory.fillDefaults().applyTo(ecpswtView.getSWTControl());
		} catch (ECPRendererException e) {
			new Label(parent, SWT.NULL).setText("Failed to render player UI.");
		}
	}

	private void createPartNameUpdater(Player player, MPart part) {
		AdapterFactoryLabelProvider labelProvider = new AdapterFactoryLabelProvider(getAdapterFactory());
		part.setLabel(labelProvider.getText(player));
		this.player = player;
		nameAdapter = new AdapterImpl() {
			@Override
			public void notifyChanged(Notification msg) {
				part.setLabel(labelProvider.getText(player));
			}
		};
		player.eAdapters().add(nameAdapter);
	}

	protected AdapterFactory getAdapterFactory() {
		if (composedAdapterFactory == null) {
			composedAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		}
		return composedAdapterFactory;
	}

	@Focus
	public void setFocus() {
		if (ecpswtView != null) {
			ecpswtView.getSWTControl().setFocus();
		}
	}

	@PreDestroy
	public void dispose() {
		if (composedAdapterFactory != null) {
			composedAdapterFactory.dispose();
		}
		if (player != null) {
			player.eAdapters().remove(nameAdapter);
		}
		if (ecpswtView != null) {
			ecpswtView.dispose();
		}
	}

}
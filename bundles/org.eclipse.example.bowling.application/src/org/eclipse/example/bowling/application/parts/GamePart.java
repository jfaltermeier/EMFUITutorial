package org.eclipse.example.bowling.application.parts;

import static org.eclipse.example.bowling.BowlingPackage.Literals.GAME__FRAMES;
import static org.eclipse.example.bowling.BowlingPackage.Literals.GAME__PLAYER;
import static org.eclipse.example.bowling.BowlingPackage.Literals.LEAGUE__PLAYERS;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.IEMFListProperty;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecp.edit.internal.swt.util.ECPObservableValue;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.example.bowling.Game;
import org.eclipse.example.bowling.dataservice.BowlingDataService;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

@SuppressWarnings("restriction")
public class GamePart {

	@Inject
	private BowlingDataService dataService;

	private ComposedAdapterFactory composedAdapterFactory;

	private ComboViewer comboViewerPlayer;

	private DataBindingContext databindingContext;

	@PostConstruct
	public void createComposite(Composite parent, MPart part, @Named(IServiceConstants.ACTIVE_SELECTION) Game game) {
		parent.setLayout(new GridLayout(2, false));

		new Label(parent, SWT.NULL).setText("Player");
		comboViewerPlayer = new ComboViewer(parent);
		comboViewerPlayer.setContentProvider(new ObservableListContentProvider());
		comboViewerPlayer.setLabelProvider(new AdapterFactoryLabelProvider(getAdapterFactory()));

		IEMFListProperty playersObservableList = EMFProperties.list(LEAGUE__PLAYERS);
		comboViewerPlayer.setInput(playersObservableList.observe(dataService.getLeage()));

		databindingContext = new EMFDataBindingContext();
		EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(game);
		databindingContext.bindValue(ViewerProperties.singleSelection().observe(comboViewerPlayer),
				EMFEditProperties.value(editingDomain, GAME__PLAYER).observe(game));

		new Label(parent, SWT.NULL).setText("Frames");
		Composite framesComposite = new Composite(parent, SWT.BORDER);
		framesComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		Text txtFrame1 = new Text(framesComposite, SWT.BORDER);
		Text txtFrame2 = new Text(framesComposite, SWT.BORDER);
		Text txtFrame3 = new Text(framesComposite, SWT.BORDER);

		IObservableList frameObservable = EMFEditProperties.list(editingDomain, GAME__FRAMES).observe(game);
		databindingContext.bindValue(WidgetProperties.text(SWT.Modify).observeDelayed(400, txtFrame1),
				new ECPObservableValue(frameObservable, 0, GAME__FRAMES.getEType().getInstanceClass()));
		databindingContext.bindValue(WidgetProperties.text(SWT.Modify).observe(txtFrame2),
				new ECPObservableValue(frameObservable, 1, GAME__FRAMES.getEType().getInstanceClass()));
		databindingContext.bindValue(WidgetProperties.text(SWT.Modify).observe(txtFrame3),
				new ECPObservableValue(frameObservable, 2, GAME__FRAMES.getEType().getInstanceClass()));
	}

	protected AdapterFactory getAdapterFactory() {
		if (composedAdapterFactory == null) {
			composedAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		}
		return composedAdapterFactory;
	}
	
	@PreDestroy
	public void dispose() {
		if (composedAdapterFactory != null) {
			composedAdapterFactory.dispose();
		}
		if (databindingContext != null) {
			databindingContext.dispose();
		}
	}

}

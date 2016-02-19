package org.eclipse.example.bowling.application.parts;

import static org.eclipse.example.bowling.BowlingPackage.Literals.PLAYER__DATE_OF_BIRTH;
import static org.eclipse.example.bowling.BowlingPackage.Literals.PLAYER__NAME;
import static org.eclipse.example.bowling.BowlingPackage.Literals.PLAYER__PROFESSIONAL;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.internal.databinding.conversion.DateToStringConverter;
import org.eclipse.core.internal.databinding.conversion.StringToDateConverter;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.example.bowling.Player;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationUpdater;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

@SuppressWarnings("restriction")
public class PlayerPart {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	private static final int DELAY = 300;

	private Text txtName;
	private Text txtBirthDate;
	private Button chkProfessional;

	private Player player;
	private DataBindingContext databindingContext;
	private AdapterImpl nameAdapter;
	private ComposedAdapterFactory composedAdapterFactory;

	@PostConstruct
	public void createComposite(Composite parent, MPart part,
			@Named(IServiceConstants.ACTIVE_SELECTION) Player player) {
		if (player == null) {
			new Label(parent, SWT.NULL).setText("No player selected.");
			return;
		}

		createPartNameUpdater(player, part);

		parent.setLayout(new GridLayout(2, false));

		new Label(parent, SWT.NULL).setText("Name");
		txtName = new Text(parent, SWT.BORDER);
		txtName.setMessage("Enter name of the player");
		txtName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(parent, SWT.NULL).setText("Date");
		txtBirthDate = new Text(parent, SWT.BORDER);
		txtBirthDate.setMessage("Enter birthdate of the player in the format yyyy-MM-dd");
		txtBirthDate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(parent, SWT.NULL).setText("Is Professional");
		chkProfessional = new Button(parent, SWT.CHECK);

		setupDatabinding(player);
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

	protected void setupDatabinding(Player player) {
		databindingContext = new EMFDataBindingContext();
		EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(player);
		databindingContext.bindValue(WidgetProperties.text(SWT.Modify).observeDelayed(DELAY, txtName),
				EMFEditProperties.value(editingDomain, PLAYER__NAME).observe(player));

		EMFUpdateValueStrategy stringToDateStrategy = new EMFUpdateValueStrategy();
		stringToDateStrategy.setConverter(new StringToDateConverterWithDateFormat());
		stringToDateStrategy.setAfterGetValidator(new DateValidator());
		EMFUpdateValueStrategy dateToStringStrategy = new EMFUpdateValueStrategy();
		dateToStringStrategy.setConverter(new DateToStringConverterWithDateFormat());

		Binding dateBinding = databindingContext.bindValue(
				WidgetProperties.text(SWT.Modify).observeDelayed(DELAY, txtBirthDate),
				EMFEditProperties.value(editingDomain, PLAYER__DATE_OF_BIRTH).observe(player), stringToDateStrategy,
				dateToStringStrategy);
		ControlDecorationSupport.create(dateBinding, SWT.TOP | SWT.LEFT, null,
				new BackgroundSettingControlDecorationUpdater());

		databindingContext.bindValue(WidgetProperties.selection().observe(chkProfessional),
				EMFEditProperties.value(editingDomain, PLAYER__PROFESSIONAL).observe(player));
	}

	@Focus
	public void setFocus() {
		txtName.setFocus();
	}

	@PreDestroy
	public void dispose() {
		if (composedAdapterFactory != null) {
			composedAdapterFactory.dispose();
		}
		if (databindingContext != null) {
			databindingContext.dispose();
		}
		if (player != null) {
			player.eAdapters().remove(nameAdapter);
		}
	}

	private final class BackgroundSettingControlDecorationUpdater extends ControlDecorationUpdater {
		@Override
		protected void update(ControlDecoration decoration, IStatus status) {
			if (status.isOK()) {
				decoration.getControl().setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
			} else {
				decoration.getControl().setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
			}
			super.update(decoration, status);
		}
	}

	private final class DateValidator implements IValidator {
		@Override
		public IStatus validate(Object value) {
			try {
				DATE_FORMAT.parse((String) value);
				return ValidationStatus.ok();
			} catch (ParseException e) {
				return ValidationStatus.error(value + " is not a valid date.");
			}
		}
	}

	private final class DateToStringConverterWithDateFormat extends DateToStringConverter {
		@Override
		protected String format(Date date) {
			return DATE_FORMAT.format(date);
		}
	}

	private final class StringToDateConverterWithDateFormat extends StringToDateConverter {
		@Override
		protected Date parse(String str) {
			try {
				return DATE_FORMAT.parse(str);
			} catch (ParseException e) {
				return super.parse(str);
			}
		}
	}
}
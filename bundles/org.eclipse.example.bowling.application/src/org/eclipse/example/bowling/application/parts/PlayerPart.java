/*******************************************************************************
 * Copyright (c) 2010 - 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Lars Vogel <lars.Vogel@gmail.com> - Bug 419770
 *******************************************************************************/
package org.eclipse.example.bowling.application.parts;

import static org.eclipse.example.bowling.BowlingPackage.Literals.PLAYER__DATE_OF_BIRTH;
import static org.eclipse.example.bowling.BowlingPackage.Literals.PLAYER__NAME;
import static org.eclipse.example.bowling.BowlingPackage.Literals.PLAYER__PROFESSIONAL;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
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
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.example.bowling.Player;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationUpdater;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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

	@PostConstruct
	public void createComposite(Composite parent, MPart part,
			@Named(IServiceConstants.ACTIVE_SELECTION) Player player) {
		parent.setLayout(new GridLayout(2, false));

		new Label(parent, SWT.NULL).setText("Name");
		txtName = new Text(parent, SWT.BORDER);
		txtName.setMessage("Enter name of the player");
		txtName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txtName.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				part.setLabel("Player: " + txtName.getText());
			}
		});

		new Label(parent, SWT.NULL).setText("Date");
		txtBirthDate = new Text(parent, SWT.BORDER);
		txtBirthDate.setMessage("Enter birthdate of the player in the format yyyy-MM-dd");
		txtBirthDate.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(parent, SWT.NULL).setText("Is Professional");
		chkProfessional = new Button(parent, SWT.CHECK);

		setupDatabinding(player);
	}

	protected void setupDatabinding(Player player) {
		DataBindingContext databindingContext = new EMFDataBindingContext();
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
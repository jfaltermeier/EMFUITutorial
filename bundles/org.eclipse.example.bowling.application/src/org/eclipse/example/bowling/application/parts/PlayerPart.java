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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.internal.databinding.conversion.DateToStringConverter;
import org.eclipse.core.internal.databinding.conversion.StringToDateConverter;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.example.bowling.BowlingPackage;
import org.eclipse.example.bowling.Player;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class PlayerPart {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static final EAttribute PLAYER_NAME = BowlingPackage.eINSTANCE.getPlayer_Name();
	private static final EAttribute PLAYER_BIRTHDAY = BowlingPackage.eINSTANCE.getPlayer_DateOfBirth();
	private static final EAttribute PLAYER_PROFESSIONAL = BowlingPackage.eINSTANCE.getPlayer_Professional();

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

		DataBindingContext databindingContext = new EMFDataBindingContext();
		databindingContext.bindValue(WidgetProperties.text(SWT.Modify).observe(txtName),
				EMFProperties.value(PLAYER_NAME).observe(player));

		EMFUpdateValueStrategy t2s = new EMFUpdateValueStrategy();
		t2s.setConverter(new StringToDateConverter() {
			@Override
			protected Date parse(String str) {
				try {
					return DATE_FORMAT.parse(str);
				} catch (ParseException e) {
					return super.parse(str);
				}
			}
		});
		EMFUpdateValueStrategy s2t = new EMFUpdateValueStrategy();
		s2t.setConverter(new DateToStringConverter() {
			@Override
			protected String format(Date date) {
				return DATE_FORMAT.format(date);
			}
		});

		databindingContext.bindValue(WidgetProperties.text(SWT.Modify).observe(txtBirthDate),
				EMFProperties.value(PLAYER_BIRTHDAY).observe(player), t2s, s2t);

		databindingContext.bindValue(WidgetProperties.selection().observe(chkProfessional),
				EMFProperties.value(PLAYER_PROFESSIONAL).observe(player));
	}

	@Focus
	public void setFocus() {
		txtName.setFocus();
	}
}
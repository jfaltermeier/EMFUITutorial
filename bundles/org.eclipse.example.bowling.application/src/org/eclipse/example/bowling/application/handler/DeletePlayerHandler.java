package org.eclipse.example.bowling.application.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.example.bowling.Player;

public class DeletePlayerHandler extends DeleteEObjectHandler {

	@CanExecute
	public boolean canExecute(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) EObject eObject) {
		return eObject instanceof Player && super.canExecute(eObject);
	}

}

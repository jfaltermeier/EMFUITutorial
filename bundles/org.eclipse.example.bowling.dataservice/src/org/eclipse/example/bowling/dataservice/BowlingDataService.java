package org.eclipse.example.bowling.dataservice;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.example.bowling.League;
import org.eclipse.example.bowling.Tournament;

public interface BowlingDataService {
	
	public League getLeage();
	
	public Tournament getTournament();
	
	public EditingDomain getEditingDomain();

}

package com.store.components.interface_i;

import java.util.List;

import com.store.components.entity.Subtypes;

public interface ISubtypes {
	public Subtypes searchByIdSubtype(int id);

	List<Subtypes> infoAboutAllSubtype();
	public void addSubtype(Subtypes subtype);
}

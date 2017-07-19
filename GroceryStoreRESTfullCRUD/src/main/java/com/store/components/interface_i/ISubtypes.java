package com.store.components.interface_i;

import java.util.Collection;

import com.store.components.entity.Subtypes;

public interface ISubtypes {
	public Subtypes searchByIdSubtype(int id);

	Collection<Subtypes> infoAboutSubtype(int page);
	public void addSubtype(Subtypes subtype);
}
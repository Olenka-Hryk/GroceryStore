package com.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.components.entity.Subtypes;
import com.store.components.interface_i.ISubtypes;

@Service
public class SubtypesService {

	@Autowired
	private ISubtypes iSubtypes;

	public Subtypes searchByIdSubtype(int id) {
		return iSubtypes.searchByIdSubtype(id);
	}

	public List<Subtypes> infoAboutAllSubtype() {
		return iSubtypes.infoAboutAllSubtype();
	}

	public void addSubtype(Subtypes subtype) {
		iSubtypes.addSubtype(subtype);
	}
}

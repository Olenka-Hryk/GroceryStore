package com.store.components.entity;

public class Workers {
	private int id;
	private String surName;
	private String name;
	private String middleName;
	private String dateBirth;

	public Workers() {
	}

	public Workers(int id, String surName, String name, String middleName, String dateBirth){
		this.id = id;
		this.surName = surName;
		this.name = name;
		this.middleName = middleName;
		this.dateBirth = dateBirth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}
}

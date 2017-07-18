package com.store.components.entity;

public class Products {
	private int id;
	private int idSubtype;
	private String name;
	private String firm;
	
	public Products() {
	}

	public Products(int id, int idSubtype, String name, String firm) {
		this.id = id;
		this.idSubtype = idSubtype;
		this.name = name;
		this.firm = firm;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdSubtype() {
		return idSubtype;
	}

	public void setIdSubtype(int idSubtype) {
		this.idSubtype = idSubtype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirm() {
		return firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}
}

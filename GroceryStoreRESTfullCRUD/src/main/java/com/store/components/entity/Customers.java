package com.store.components.entity;

public class Customers {
	private int id;
	private String surName;
	private String name;
	private String middleName;
	private String dateBirth;
	private String phoneNumber;
	private String numberCard;
	private int discountSale;

	public Customers() {
	}

	public Customers(int id, String surName, String name, String middleName, String dateBirth, String phoneNumber, String numberCard, int discountSale){
		this.id = id;
		this.surName = surName;
		this.name = name;
		this.middleName = middleName;
		this.dateBirth = dateBirth;
		this.phoneNumber = phoneNumber;
		this.numberCard = numberCard;
		this.discountSale = discountSale;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNumberCard() {
		return numberCard;
	}

	public void setNumberCard(String numberCard) {
		this.numberCard = numberCard;
	}

	public int getDiscountSale() {
		return discountSale;
	}

	public void setDiscountSale(int discountSale) {
		this.discountSale = discountSale;
	}
}

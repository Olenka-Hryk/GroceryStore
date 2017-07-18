package com.store.components.entity;

public class Sale {
	private int id;
	private int idProduct;
	private int percent;
	private String nameSale;
	private String dateStart;
	private String dateFinish;

	public Sale() {
	}

	public Sale(int id, int idProduct, int percent, String nameSale, String dateStart, String dateFinish){
		this.id = id;
		this.idProduct = idProduct;
		this.percent =percent;
		this.nameSale = nameSale;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public String getNameSale() {
		return nameSale;
	}

	public void setNameSale(String nameSale) {
		this.nameSale = nameSale;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(String dateFinish) {
		this.dateFinish = dateFinish;
	}
}

package com.store.components.entity;

public class Reserve {
	private int id;
	private int idStore;
    private int idProduct;
	private String expirationDate;
	private float quantity;
	private float price;
	private String barCode;
	private float productSize;

	public Reserve() {
	}

	public Reserve(int id, int idStore, int idProduct, String expirationDate, float quantity, float price, String barCode, float productSize){
		this.id = id;
		this.idStore = idStore;
		this.idProduct = idProduct;
		this.expirationDate = expirationDate;
		this.quantity = quantity;
		this.price = price;
		this.barCode = barCode;
		this.productSize = productSize;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdStore() {
		return idStore;
	}

	public void setIdStore(int idStore) {
		this.idStore = idStore;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public float getProductSize() {
		return productSize;
	}

	public void setProductSize(float productSize) {
		this.productSize = productSize;
	}
}

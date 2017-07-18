package com.store.components.entity;

public class Cheque {
	private int id;
	private int idReserve;
	private int idOrder;
	private int idCustomer;
	private int idSale;
	private float amount;
	private String orderDate;

	public Cheque() {
	}

	public Cheque(int id, int idReserve, int idOrder, int idCustomer, int idSale, float amount, String orderDate) {
		this.id = id;
		this.idReserve = idReserve;
		this.idOrder = idOrder;
		this.idCustomer = idCustomer;
		this.idSale = idSale;
		this.amount = amount;
        this.orderDate = orderDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdReserve() {
		return idReserve;
	}

	public void setIdReserve(int idReserve) {
		this.idReserve = idReserve;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public int getIdSale() {
		return idSale;
	}

	public void setIdSale(int idSale) {
		this.idSale = idSale;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
}

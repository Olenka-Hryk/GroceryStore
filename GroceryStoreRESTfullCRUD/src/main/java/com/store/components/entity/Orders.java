package com.store.components.entity;

public class Orders {
	private int id;
	private int idSale;
	private int idReserve;
	private int idWorker;
	private float totalSum;

	public Orders() {
	}

	public Orders(int id, int idSale, int idReserve, int idWorker, float totalSum){
		this.id = id;
		this.idSale = idSale;
		this.idReserve = idReserve;
		this.idWorker = idWorker;
		this.totalSum = totalSum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdSale() {
		return idSale;
	}

	public void setIdSale(int idSale) {
		this.idSale = idSale;
	}

	public int getIdReserve() {
		return idReserve;
	}

	public void setIdReserve(int idReserve) {
		this.idReserve = idReserve;
	}

	public int getIdWorker() {
		return idWorker;
	}

	public void setIdWorker(int idWorker) {
		this.idWorker = idWorker;
	}

	public float getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(float totalSum) {
		this.totalSum = totalSum;
	}
}

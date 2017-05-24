package com.store.components.entity;

public class TimeTable {
	private int id;
	private int idWorker;
    private int idStore;
	private String workDate;
	private float salary;

	public TimeTable() {
	}

	public TimeTable(int id, int idWorker, int idStore, String workDate, float salary){
		this.id = id;
		this.idWorker = idWorker;
		this.idStore = idStore;
		this.workDate = workDate;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdWorker() {
		return idWorker;
	}

	public void setIdWorker(int idWorker) {
		this.idWorker = idWorker;
	}

	public int getIdStore() {
		return idStore;
	}

	public void setIdStore(int idStore) {
		this.idStore = idStore;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}
}

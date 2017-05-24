package com.store.components.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.store.components.entity.Customers;
import com.store.components.interface_i.ICustomers;

@Repository
public class CustomersDao implements ICustomers{
	/**
	 * variable to work with the base
	 */
	@Autowired
	private JdbcTemplate jdbc;

	public static class workingWithRowMap implements RowMapper<Customers> {
		/**
		 * copy data from DB to customer
		 */
		public Customers mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customers customer = new Customers();
			customer.setId(rs.getInt(1));
			customer.setSurName(rs.getString(3));
			customer.setName(rs.getString(2));	
			customer.setMiddleName(rs.getString(3));
			customer.setDateBirth(rs.getString(4));
			customer.setPhoneNumber(rs.getString(8));
			customer.setNumberCard(rs.getString(10));
			customer.setDiscountSale(rs.getInt(4));

			return customer;
		}
	}

	/**
	 * Looking information about customer by id
	 * 
	 * @param id
	 * @return customer
	 */
	public Customers searchByIdCustomer(int id) {
		final String SQL = "SELECT * FROM Customers WHERE idCustomer=?";
		Customers customer = jdbc.queryForObject(SQL, new workingWithRowMap(), id);
		return customer;
	}
	
	/**
	 * Select all information about customer
	 * @param page
	 * @return customers
	 */
	public Collection<Customers> infoAboutCustomer(int page) {
		page *= 3;
		final String SQL = "SELECT * FROM Customers LIMIT 3 offset ?";
		List<Customers> customers = jdbc.query(SQL, new workingWithRowMap(), page);
		return customers;
	}

	public void addCustomer(Customers customer) {
		final String SQL = "INSERT INTO Customers (SurnameCustomer, NameCustomer, MiddleNameCustomer, DateOfBirthCustomer, PhoneNumberCustomer, NumberCardCustomer, DiscountCustomer) values (?, ?, ?, ?, ?, ?, ?) ";
		final String surName = customer.getSurName();
		final String name = customer.getName();	
		final String middleName = customer.getMiddleName();
		final String dateBirth = customer.getDateBirth();
		final String phoneNumber = customer.getPhoneNumber();
		final String numberCard = customer.getNumberCard();
		final int discountSale = customer.getDiscountSale();

		jdbc.update(SQL, new Object[] { surName, name, middleName, dateBirth, phoneNumber, numberCard, discountSale });
	}

}

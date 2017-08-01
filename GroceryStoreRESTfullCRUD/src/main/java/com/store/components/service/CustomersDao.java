package com.store.components.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.store.components.entity.Customers;
import com.store.components.interface_i.ICustomers;

@Repository
public class CustomersDao implements ICustomers {

	@Autowired
	private JdbcTemplate jdbc;

	public static class CustomersRowMapper implements RowMapper<Customers> {
		/**
		 * copy data from DB to customer
		 */
		public Customers mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customers customer = new Customers();
			customer.setId(rs.getInt(1));
			customer.setSurName(rs.getString(2));
			customer.setName(rs.getString(3));
			customer.setMiddleName(rs.getString(4));
			customer.setDateBirth(rs.getString(5));
			customer.setPhoneNumber(rs.getString(6));
			customer.setNumberCard(rs.getString(7));
			customer.setDiscountSale(rs.getInt(8));

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
		Customers customer = jdbc.queryForObject(SQL, new CustomersRowMapper(), id);
		return customer;
	}

	/**
	 * Select all information about customer
	 * 
	 * @param page
	 * @return customers
	 */
	public List<Customers> infoAboutAllCustomer() {
		List<Customers> customers = jdbc.query("SELECT * FROM Customers", new BeanPropertyRowMapper(Customers.class));
		return customers;
	}

	public void addCustomer(Customers customer) {
		final String SQL = "INSERT INTO Customers (SurnameCustomer, NameCustomer, MiddleNameCustomer, DateOfBirthCustomer, PhoneNumberCustomer, NumberCardCustomer, DiscountCustomer) values (?, ?, ?, ?, ?, ?, ?) ";	
		jdbc.update(SQL, customer.getSurName(), customer.getName(), customer.getMiddleName(), customer.getDateBirth(), customer.getPhoneNumber(), customer.getNumberCard(), customer.getDiscountSale());
	}
}

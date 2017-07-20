package com.store.components.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.store.components.entity.Cheque;
import com.store.components.interface_i.ICheque;

@Repository
public class ChequeDao implements ICheque{
	/**
	 * variable to work with the base
	 */
	@Autowired
	private JdbcTemplate jdbc;

	public static class workingWithRowMap implements RowMapper<Cheque> {
		/**
		 * copy data from DB to cheque
		 */
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cheque cheque = new Cheque();
			cheque.setId(rs.getInt(1));
			cheque.setIdReserve(rs.getInt(2));
			cheque.setIdOrder(rs.getInt(3));
			cheque.setIdCustomer(rs.getInt(4));
			cheque.setIdSale(rs.getInt(5));
			cheque.setAmount(rs.getInt(6));
			cheque.setOrderDate(rs.getString(7));

			return cheque;
		}
	}
	
	public List<Cheque> infoAboutAllCheque(int page) {
		page *= 3;
		final String SQL = "SELECT * FROM Cheque LIMIT 3 offset ?";
		List<Cheque> cheque = jdbc.query(SQL, new workingWithRowMap(), page);
		return cheque;
	}
	
	/**
	 * Looking information about amount of order by order id
	 * 
	 * @param id
	 * @return cheque
	 */
	public Cheque infoAboutAmountOfOrderById(int id) {
		final String SQL = "SELECT * from Cheque WHERE idOrder=?";
		Cheque cheque = jdbc.queryForObject(SQL, new workingWithRowMap(), id);
		return cheque;
	}
	
	public void addCheque(Cheque cheque) {
		final String SQL = "INSERT INTO Cheque (idReserve, idOrder, idCustomer, idSale, Amount, OrderDate) values (?, ?, ?, ?, ?, ?) ";
		jdbc.update(SQL,cheque.getIdReserve(), cheque.getIdOrder(), cheque.getIdCustomer(), cheque.getIdSale(), cheque.getAmount(), cheque.getOrderDate());
	}
}

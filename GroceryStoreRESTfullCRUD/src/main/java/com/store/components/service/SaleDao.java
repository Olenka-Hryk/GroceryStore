package com.store.components.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.store.components.entity.Sale;
import com.store.components.interface_i.ISale;

@Repository
public class SaleDao implements ISale {
	/**
	 * variable to work with the base
	 */
	@Autowired
	private JdbcTemplate jdbc;

	public static class workingWithRowMap implements RowMapper<Sale> {
		/**
		 * copy data from DB to sale
		 */
		public Sale mapRow(ResultSet rs, int rowNum) throws SQLException {
			Sale sale = new Sale();
			sale.setId(rs.getInt(1));
			sale.setIdProduct(rs.getInt(1));
			sale.setPercent(rs.getInt(3));
			sale.setNameSale(rs.getString(5));
			sale.setDateStart(rs.getString(4));
			sale.setDateFinish(rs.getString(4));
			
			return sale;
		}
	}

	/**
	 * Looking information about sale by id
	 * 
	 * @param id
	 * @return sale
	 */
	public Sale searchByIdSale(int id) {
		final String SQL = "SELECT * FROM Sale WHERE idSale=?";
		Sale sale = jdbc.queryForObject(SQL, new workingWithRowMap(), id);
		return sale;
	}

	/**
	 * Select all information about sale
	 * 
	 * @param page
	 * @return sale
	 */
	public Collection<Sale> infoAboutSale(int page) {
		page *= 3;
		final String SQL = "SELECT * FROM Sale LIMIT 3 offset ?";
		List<Sale> sale = jdbc.query(SQL, new workingWithRowMap(), page);
		return sale;
	}

	public void addSale(Sale sale) {
		final String SQL = "INSERT INTO Sale (idProduct, Percent, NameSale, DateOfstart, DateOfFinish) values (?, ?, ?, ?, ?) ";
		final int idProduct = sale.getIdProduct();
		final int percent = sale.getPercent();
		final String nameSale = sale.getNameSale();
		final String dateStart = sale.getDateStart();
		final String dateFinish = sale.getDateFinish();
		
		jdbc.update(SQL, new Object[] { idProduct, percent, nameSale, dateStart, dateFinish });
	}

}

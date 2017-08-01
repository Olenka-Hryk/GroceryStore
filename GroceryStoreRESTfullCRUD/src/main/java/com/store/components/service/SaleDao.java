package com.store.components.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
			sale.setIdProduct(rs.getInt(2));
			sale.setPercent(rs.getInt(3));
			sale.setNameSale(rs.getString(4));
			sale.setDateStart(rs.getString(5));
			sale.setDateFinish(rs.getString(6));
			
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
	public List<Sale> infoAboutAllSale() {
		List<Sale> sale = jdbc.query("SELECT * FROM Sale", new BeanPropertyRowMapper(Sale.class));
		return sale;
	}

	public void addSale(Sale sale) {
		final String SQL = "INSERT INTO Sale (idProduct, Percent, NameSale, DateOfstart, DateOfFinish) values (?, ?, ?, ?, ?) ";
		
		jdbc.update(SQL, sale.getIdProduct(), sale.getPercent(), sale.getNameSale(), sale.getDateStart(), sale.getDateFinish());
	}

}

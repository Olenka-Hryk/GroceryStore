package com.store.components.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.store.components.entity.Reserve;
import com.store.components.interface_i.IReserve;

/**
 * Class for working with db MySQL
 * 
 * @author reserve
 * 
 */
@Repository
public class ReserveDao implements IReserve {

	/**
	 * variable to work with the base
	 */
	@Autowired
	private JdbcTemplate jdbc;

	public static class workingWithRowMap implements RowMapper<Reserve> {
		/**
		 * copy data from DB to reserve
		 */
		public Reserve mapRow(ResultSet rs, int rowNum) throws SQLException {
			Reserve reserve = new Reserve();
			reserve.setId(rs.getInt(1));
			reserve.setIdStore(rs.getInt(2));
			reserve.setIdProduct(rs.getInt(3));
			reserve.setExpirationDate(rs.getString(4));
			reserve.setQuantity(rs.getFloat(5));
			reserve.setPrice(rs.getFloat(6));
			reserve.setBarCode(rs.getString(6));
			reserve.setProductSize(rs.getFloat(6));
			
			return reserve;
		}
	}

	/**
	 * Looking information about product by id
	 * 
	 * @param id
	 * @return product
	 */
	public Reserve searchByIdProduct(int id) {
		final String SQL = "SELECT * FROM Reserve WHERE idProduct=?";
		Reserve product = jdbc.queryForObject(SQL, new workingWithRowMap(), id);
		return product;
	}

	/**
	 * Select all information about products
	 */
	public Collection<Reserve> infoAboutProduct(int page) {
		page *= 3;
		final String SQL = "SELECT * FROM Reserve LIMIT 3 offset ?";
		List<Reserve> products = jdbc.query(SQL, new workingWithRowMap(), page);
		return products;
	}

	public void addProduct(Reserve product) {
		final String SQL = "INSERT INTO Reserve (idStore, idProduct, ExpirationDate, Quantity, Price, BarCode, ProductSize) values (?, ?, ?, ?, ?, ?, ?) ";
		final int idStore = product.getIdStore();
		final int idProduct = product.getIdProduct();
		final String expirationDate = product.getExpirationDate();
		final float quantity = product.getQuantity();
		final float price = product.getPrice();
		final String barCode = product.getBarCode();
		final float productSize = product.getProductSize();

		jdbc.update(SQL, new Object[] { idStore, idProduct, expirationDate, quantity, price, barCode, productSize });
	}

	/**
	 * Delete product by id
	 * 
	 * @param id
	 */
	public void deleteByIdProduct(int id) {
		final String SQL = "DELETE FROM Reserve WHERE idProduct=?";
		jdbc.update(SQL, id);
	}

	/**
	 * Update information about product by id
	 * 
	 * @param product
	 */

	public void updateProduct(Reserve product) {
		final String SQL = "UPDATE Reserve SET idStore=?, idProduct=?, ExpirationDate=?, Quantity=?, Price=?, BarCode=?, ProductSize=?   WHERE idProduct=?";
		final int id = product.getId();
		final int idStore = product.getIdStore();
		final int idProduct = product.getIdProduct();
		final String expirationDate = product.getExpirationDate();
		final float quantity = product.getQuantity();
		final float price = product.getPrice();
		final String barCode = product.getBarCode();
		final float productSize = product.getProductSize();

		jdbc.update(SQL, new Object[] { id, idStore, idProduct, expirationDate, quantity, price, barCode, productSize });
	}

}
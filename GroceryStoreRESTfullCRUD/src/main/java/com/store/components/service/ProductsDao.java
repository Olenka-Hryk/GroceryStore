package com.store.components.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.store.components.entity.Products;
import com.store.components.interface_i.IProducts;

@Repository
public class ProductsDao implements IProducts{
	/**
	 * variable to work with the base
	 */
	@Autowired
	private JdbcTemplate jdbc;

	public static class workingWithRowMap implements RowMapper<Products> {
		/**
		 * copy data from DB to products
		 */
		public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
			Products product = new Products();
			product.setId(rs.getInt(1));
			product.setIdSubtype(rs.getInt(1));
			product.setName(rs.getString(4));
			product.setFirm(rs.getString(4));
			
			return product;
		}
	}

	/**
	 * Looking information about product by id
	 * 
	 * @param id
	 * @return product
	 */
	public Products searchByIdProducts(int id) {
		final String SQL = "SELECT * FROM Products WHERE idProduct=?";
		Products product = jdbc.queryForObject(SQL, new workingWithRowMap(), id);
		return product;
	}
	
	/**
	 * Select all information about products
	 * @param page
	 * @return products
	 */
	public Collection<Products> infoAboutProducts(int page) {
		page *= 3;
		final String SQL = "SELECT * FROM Products LIMIT 3 offset ?";
		List<Products> products = jdbc.query(SQL, new workingWithRowMap(), page);
		return products;
	}

	public void addProduct(Products product) {
		final String SQL = "INSERT INTO Products (idSubtype, Name, Firm) values (?, ?, ?) ";
		final int idSubtype = product.getIdSubtype();
		final String name = product.getName();
		final String firm = product.getFirm();
		
		jdbc.update(SQL, new Object[] { idSubtype, name, firm });
	}

}

package com.store.components.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.store.components.entity.Store;
import com.store.components.interface_i.IStore;

@Repository
public class StoreDao implements IStore{
	/**
	 * variable to work with the base
	 */
	@Autowired
	private JdbcTemplate jdbc;

	public static class workingWithRowMap implements RowMapper<Store> {
		/**
		 * copy data from DB to store
		 */
		public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
			Store store = new Store();
			store.setId(rs.getInt(1));
			store.setAddress(rs.getString(2));

			return store;
		}
	}

	/**
	 * Looking information about store by id
	 * 
	 * @param id
	 * @return store
	 */
	public Store searchByIdStore(int id) {
		final String SQL = "SELECT * FROM Store WHERE idStore=?";
		Store store = jdbc.queryForObject(SQL, new workingWithRowMap(), id);
		return store;
	}
	
	/**
	 * Select all information about store
	 * @param page
	 * @return store
	 */
	public Collection<Store> infoAboutStore(int page) {
		page *= 2;
		final String SQL = "SELECT * FROM Store LIMIT 2 offset ?";
		List<Store> store = jdbc.query(SQL, new workingWithRowMap(), page);
		return store;
	}

	public void addStore(Store store) {
		final String SQL = "INSERT INTO Store (Address) values (?) ";
		final String address = store.getAddress();

		jdbc.update(SQL, new Object[] { address });
	}

}

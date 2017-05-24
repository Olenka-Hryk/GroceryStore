package com.store.components.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.store.components.entity.Subtypes;
import com.store.components.interface_i.ISubtypes;

@Repository
public class SubtypesDao implements ISubtypes{
	/**
	 * variable to work with the base
	 */
	@Autowired
	private JdbcTemplate jdbc;

	public static class workingWithRowMap implements RowMapper<Subtypes> {
		/**
		 * copy data from DB to subtype
		 */
		public Subtypes mapRow(ResultSet rs, int rowNum) throws SQLException {
			Subtypes subtype = new Subtypes();
			subtype.setId(rs.getInt(1));
			subtype.setName(rs.getString(3));

			return subtype;
		}
	}

	/**
	 * Looking information about subtype by id
	 * 
	 * @param id
	 * @return subtype
	 */
	public Subtypes searchByIdSubtype(int id) {
		final String SQL = "SELECT * FROM Subtypes WHERE idSubtype=?";
		Subtypes subtype = jdbc.queryForObject(SQL, new workingWithRowMap(), id);
		return subtype;
	}
	
	/**
	 * Select all information about subtype
	 * @param page
	 * @return subtypes
	 */
	public Collection<Subtypes> infoAboutSubtype(int page) {
		page *= 3;
		final String SQL = "SELECT * FROM Subtypes LIMIT 3 offset ?";
		List<Subtypes> subtypes = jdbc.query(SQL, new workingWithRowMap(), page);
		return subtypes;
	}

	public void addSubtype(Subtypes subtype) {
		final String SQL = "INSERT INTO Subtypes (NameSubtype) values (?) ";
		final String nameSubtype = subtype.getName();

		jdbc.update(SQL, new Object[] { nameSubtype });
	}

}

package com.store.components.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.store.components.entity.Workers;
import com.store.components.interface_i.IWorkers;

@Repository
public class WorkersDao implements IWorkers{
	/**
	 * variable to work with the base
	 */
	@Autowired
	private JdbcTemplate jdbc;

	public static class workingWithRowMap implements RowMapper<Workers> {
		/**
		 * copy data from DB to customer
		 */
		public Workers mapRow(ResultSet rs, int rowNum) throws SQLException {
			Workers worker = new Workers();
			worker.setId(rs.getInt(1));
			worker.setSurName(rs.getString(3));
			worker.setName(rs.getString(2));	
			worker.setMiddleName(rs.getString(3));
			worker.setDateBirth(rs.getString(4));

			return worker;
		}
	}

	/**
	 * Looking information about worker by id
	 * 
	 * @param id
	 * @return worker
	 */
	public Workers searchByIdWorker(int id) {
		final String SQL = "SELECT * FROM Workers WHERE idWorker=?";
		Workers worker = jdbc.queryForObject(SQL, new workingWithRowMap(), id);
		return worker;
	}
	
	/**
	 * Select all information about workers
	 * @param page
	 * @return workers
	 */
	public Collection<Workers> infoAboutWorker(int page) {
		page *= 3;
		final String SQL = "SELECT * FROM Workers LIMIT 3 offset ?";
		List<Workers> workers = jdbc.query(SQL, new workingWithRowMap(), page);
		return workers;
	}

	public void addWorker(Workers worker) {
		final String SQL = "INSERT INTO Workers (SurnameWorker, NameWorker, MiddleNameWorker, DateOfBirthWorker) values (?, ?, ?, ?) ";
		final String surName = worker.getSurName();
		final String name = worker.getName();	
		final String middleName = worker.getMiddleName();
		final String dateBirth = worker.getDateBirth();

		jdbc.update(SQL, new Object[] { surName, name, middleName, dateBirth });
	}

}
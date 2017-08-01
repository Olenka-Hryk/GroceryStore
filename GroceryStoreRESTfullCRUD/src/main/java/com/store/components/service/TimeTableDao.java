package com.store.components.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.store.components.entity.TimeTable;
import com.store.components.interface_i.ITimeTable;

@Repository
public class TimeTableDao implements ITimeTable {
	/**
	 * variable to work with the base
	 */
	@Autowired
	private JdbcTemplate jdbc;

	public static class workingWithRowMap implements RowMapper<TimeTable> {
		/**
		 * copy data from DB to timetable
		 */
		public TimeTable mapRow(ResultSet rs, int rowNum) throws SQLException {
			TimeTable timetable = new TimeTable();
			timetable.setId(rs.getInt(1));
			timetable.setIdWorker(rs.getInt(2));
			timetable.setIdStore(rs.getInt(3));
			timetable.setWorkDate(rs.getString(4));
			timetable.setSalary(rs.getInt(5));

			return timetable;
		}
	}

	/**
	 * Looking information about timetable by id
	 * 
	 * @param id
	 * @return timetable
	 */
	public TimeTable searchByIdWorker(int id) {
		final String SQL = "SELECT * FROM TimeTable WHERE idWorker=?";
		TimeTable timetable = jdbc.queryForObject(SQL, new workingWithRowMap(), id);
		return timetable;
	}
	
	/**
	 * Select all information about timetable
	 * @param page
	 * @return timetable
	 */
	public List<TimeTable> infoAboutAllTimeTable() {
		List<TimeTable> timetable = jdbc.query("SELECT * FROM TimeTable", new BeanPropertyRowMapper(TimeTable.class));
		return timetable;
	}
}

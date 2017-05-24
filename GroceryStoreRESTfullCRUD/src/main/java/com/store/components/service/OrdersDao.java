package com.store.components.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.store.components.entity.Orders;
import com.store.components.interface_i.IOrders;

@Repository
public class OrdersDao implements IOrders{
	/**
	 * variable to work with the base
	 */
	@Autowired
	private JdbcTemplate jdbc;

	public static class workingWithRowMap implements RowMapper<Orders> {
		/**
		 * copy data from DB to order
		 */
		public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
			Orders order = new Orders();
			order.setId(rs.getInt(1));
			order.setIdSale(rs.getInt(2));
			order.setIdReserve(rs.getInt(3));
			order.setIdWorker(rs.getInt(4));
			order.setTotalSum(rs.getInt(4));

			return order;
		}
	}
	
	public Collection<Orders> infoAboutAllOrder(int page) {
		page *= 3;
		final String SQL = "SELECT * FROM Orders LIMIT 3 offset ?";
		List<Orders> order = jdbc.query(SQL, new workingWithRowMap(), page);
		return order;
	}
	
	/**
	 * Looking information about summa of order by order id
	 * 
	 * @param id
	 * @return order
	 */
	public Orders infoAboutSumOfOrderById(int id) {
		final String SQL = "SELECT * from Orders WHERE idOrder=?";
		Orders order = jdbc.queryForObject(SQL, new workingWithRowMap(), id);
		return order;
	}
	
	public void addOrder(Orders order) {
		final String SQL = "INSERT INTO Orders (idSale, idReserve, idWorker, TotalSum) values (?, ?, ?, ?) ";
		final int idSale = order.getIdSale();
		final int idReserve = order.getIdReserve();	
		final int idWorker = order.getIdWorker();
		final float totalSum = order.getTotalSum();

		jdbc.update(SQL, new Object[] { idSale, idReserve, idWorker, totalSum });
	}


}

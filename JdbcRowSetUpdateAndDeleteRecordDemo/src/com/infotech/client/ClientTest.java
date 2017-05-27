package com.infotech.client;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class ClientTest {

	private static final String DB_USERNAME="root";
	private static final String DB_PASSWORD="root";
	private static final String DB_URL ="jdbc:mysql://localhost:3306/jdbcdb";

	public static void main(String[] args) throws SQLException {
		
		RowSetFactory rowSetFactory = RowSetProvider.newFactory();
		JdbcRowSet jdbcRowSet = rowSetFactory.createJdbcRowSet();
		
		jdbcRowSet.setUrl(DB_URL);
		jdbcRowSet.setUsername(DB_USERNAME);
		jdbcRowSet.setPassword(DB_PASSWORD);
		
		jdbcRowSet.setCommand("SELECT *FROM employee_table");
		
		jdbcRowSet.execute();
		
		showEmployeesInfo(jdbcRowSet);
		System.out.println("-------------------------------------------------------");
		//updateEmployeeEmailById(jdbcRowSet);
		deleteEmployeeById(jdbcRowSet);
		System.out.println("-------------------------------------------------------");
		showEmployeesInfo(jdbcRowSet);
	}

	private static void deleteEmployeeById(JdbcRowSet jdbcRowSet) throws SQLException {
		
		int employeeId = 1;
		while (jdbcRowSet.next()) {
			int empId = jdbcRowSet.getInt("employee_id");
			if(employeeId ==empId ){
				jdbcRowSet.deleteRow();
			}
		}
		jdbcRowSet.beforeFirst();
	}


	private static void updateEmployeeEmailById(JdbcRowSet jdbcRowSet) throws SQLException {
		
		int employeeId = 5;
		while (jdbcRowSet.next()) {
			int empId = jdbcRowSet.getInt("employee_id");
			if(employeeId ==empId ){
				jdbcRowSet.updateString("email", "tommy.cs2017@siffy.com");
				jdbcRowSet.updateRow();
			}
		}
		jdbcRowSet.beforeFirst();
	}


	private static void showEmployeesInfo(JdbcRowSet jdbcRowSet) throws SQLException {
		while (jdbcRowSet.next()) {
			int empId = jdbcRowSet.getInt("employee_id");
			String eName = jdbcRowSet.getString("employee_name");
			String email = jdbcRowSet.getString("email");
			Double salary = jdbcRowSet.getDouble("salary");
			BigDecimal bonus = jdbcRowSet.getBigDecimal("bonus");

			System.out.println(empId + "\t" + eName + "\t" + salary + "\t" + email + "\t" + bonus);
		
		}
		jdbcRowSet.beforeFirst();
		
	}
}

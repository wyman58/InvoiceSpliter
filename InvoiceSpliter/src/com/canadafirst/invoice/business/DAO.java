package com.canadafirst.invoice.business;

import java.sql.*;

import com.canadafirst.invoice.dao.JDBC;

public class DAO {
	public Group getGroupInfo() throws Exception{
		Group group = new Group();
		
		String sql = "select * from group";
		Connection conn = JDBC.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()){
			group.setGroupName(rs.getString(1));
			group.setHST(rs.getString(2));
			group.setAddress1(rs.getString(3));
			group.setAddress2(rs.getString(4));
			group.setSuburb(rs.getString(6));
			group.setProvince(rs.getString(7));
			group.setPostcode(rs.getString(9));
		}
		stmt.close();
		JDBC.closeConnection();
		return group;
	}
}

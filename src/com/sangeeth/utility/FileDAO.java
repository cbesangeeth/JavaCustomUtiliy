/**
 * 
 */
package com.sangeeth.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Sangeeth
 *
 */
public class FileDAO {
	
	public void insertIntoDB(String aFile,String fileName) {
		String url = "jdbc:mysql://localhost:3306/contactdb";
		String user = "root";
		String password = "secret";

		try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = "INSERT INTO person (fileName, file) values (?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			InputStream inputStream = new FileInputStream(aFile);

			statement.setString(1, fileName);
			statement.setBlob(2, inputStream);

			int row = statement.executeUpdate();
			if (row > 0) {
				System.out.println(fileName+"-Inserted into DB");
			}
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}


}

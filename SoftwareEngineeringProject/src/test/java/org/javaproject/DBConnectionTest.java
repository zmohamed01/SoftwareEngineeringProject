package org.javaproject;

import static org.junit.Assert.*;

import org.javaproject.DBConnection;
import org.junit.Test;

public class DBConnectionTest {

	@Test
	public void testConnection() {
		DBConnection connection = new DBConnection();
		assertEquals( connection != null, true);
	}

}

package com.library.dbConnectionTest;

import static org.junit.Assert.*;


import org.junit.Test;

import com.library.dbConnection.DatabaseConnection;;

public class DatabaseConnectionTest {

	@Test
	public void test() {
		
		DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionInstance();
		assertNotNull(databaseConnection);
		
		
	}

}

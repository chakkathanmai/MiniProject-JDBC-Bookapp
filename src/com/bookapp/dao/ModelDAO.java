package com.bookapp.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ModelDAO {
      static Connection connection; //connection variable
	public static Connection openConnection(){ //connection method
		// TODO Auto-generated method stub	
     Properties properties=new Properties();   //properties object creation
     connection=null;
      try {
		properties.load(new FileReader("bookdb.properties")); 
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

      String url=(String)properties.getProperty("url");
      String username=(String)properties.getProperty("username");
      String password=(String)properties.getProperty("password");
      
      try {
    	// Class.forName(drivername);
    				connection = DriverManager.getConnection(url, username, password);

    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    			return connection;   
	}
	public static void closeConnection() {
			try {
				if(connection!=null) {
				connection.close();
			} 
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


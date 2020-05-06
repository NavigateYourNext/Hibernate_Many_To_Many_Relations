package com.code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC
{
	public static void main(String[] args)
	{
		String url = "jdbc:postgresql://localhost:5432/hb-01-one-to-one-uni";
		String user = "postgres";
		String pass = "admin";
		
		try
		{
			//Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url,user,pass);
			
			if(connection != null)
				System.out.println("true");
			else
				System.out.println("false");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
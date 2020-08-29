/*
 * Randy Edward Penird July 17 2020
 * the following application manipulates and displays information stored in a connected SQLite database. Showing an understanding of the 
 * manipulation of data
 */

import java.sql.*;

public class main {

	public static void main(String[] args) throws Exception
	{
		Connection sqLite = null;
		Statement statement = null;
		ResultSet result = null;
		String command = null;
		String fName;
		String lName;
		int age;
		long social;
		long cCard;
//ensures a database is connected to execute following code, throws exception if failure.
		try
		{
			Class.forName("org.sqlite.JDBC");
			sqLite = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
			System.out.println("Connection To Database - Successful");
		}
		catch(Exception e)
		{
			System.out.println("Connection To Database - Failure");
		}
		statement = sqLite.createStatement();
		
//creates table with PERSON attributes
		try
		{
	         command = "CREATE TABLE PERSON " +
	                        "(SSN LONG PRIMARY KEY NOT NULL," +
	                        " FNAME TEXT NOT NULL, " + 
	                        " LNAME TEXT NOT NULL, " +
	                        " AGE INT NOT NULL, " + 
	                        " CCARD LONG NOT NULL)"; 
	         statement.executeUpdate(command);
	         System.out.println("Table Creation Successful");
		}
		catch(Exception e)
		{
			System.out.println("Table Creation Failure");
		}
		
//Uses INSERT command.
		 command = "INSERT INTO PERSON (SSN,FNAME,LNAME,AGE,CCARD) " +
                 "VALUES (647598216, 'Joey', 'Frank', 60, 2568559856421020 );"; 
		 statement.executeUpdate(command);
		 command = "INSERT INTO PERSON (SSN,FNAME,LNAME,AGE,CCARD) " +
                 "VALUES (252164875, 'Franny', 'James', 20, 5645523152422320 );"; 
		 statement.executeUpdate(command);
		 command = "INSERT INTO PERSON (SSN,FNAME,LNAME,AGE,CCARD) " +
                 "VALUES (212115349, 'Isabella', 'Poots', 18, 2232564154258965 );"; 
		 statement.executeUpdate(command);
		 
//Inserts Person Object into the database.
		Person daniel = new Person("Daniel", "Tammy", 25, 564558754, 2565559856485752L);
		insertPerson(daniel, sqLite);
		Person tony = new Person("Tony", "Bananas", 19, 878569020, 6569888543268530L);
		insertPerson(tony, sqLite);
		Person sarah = new Person("Sarah", "Floops", 89, 323120507, 6669586745200014L);
		insertPerson(sarah, sqLite);
		
//Select and Print SPECIFIC from Table
		System.out.println("The following entries are called using an SELECT statement");
		 result = statement.executeQuery( "SELECT * FROM PERSON WHERE SSN = " + daniel.social + ";" );
		  while ( result.next() ) 
		  {
		         social = result.getLong("SSN");
		         fName = result.getString("FNAME");
		         lName  = result.getString("LNAME");
		         age = result.getInt("AGE");
		         cCard = result.getLong("CCARD");
		         
		         System.out.println( "SSN = " + social );
		         System.out.println( "First Name = " + fName );
		         System.out.println( "Last Name = " + lName );
		         System.out.println( "Age = " + age );
		         System.out.println( "Credit Card Number = " + cCard );
		         System.out.println();
		  }
		  result = statement.executeQuery( "SELECT * FROM PERSON WHERE SSN = 323120507;" );
		  while ( result.next() ) 
		  {
		         social = result.getLong("SSN");
		         fName = result.getString("FNAME");
		         lName  = result.getString("LNAME");
		         age = result.getInt("AGE");
		         cCard = result.getLong("CCARD");
		         
		         System.out.println( "SSN = " + social );
		         System.out.println( "First Name = " + fName );
		         System.out.println( "Last Name = " + lName );
		         System.out.println( "Age = " + age );
		         System.out.println( "Credit Card Number = " + cCard );
		         System.out.println();
		  }

//Select Person, returns object
		  System.out.println("The following entry uses the selectPerson method");	
		  Person Select = selectPerson(564558754, sqLite);
		  System.out.println( "SSN = " + Select.social );
	         System.out.println( "First Name = " + Select.fName );
	         System.out.println( "Last Name = " + Select.lName );
	         System.out.println( "Age = " + Select.age );
	         System.out.println( "Credit Card Number = " + Select.cCard );
	         System.out.println();
	         
//deletes entry based on primarykey
	 		System.out.println("The Following entry removed using DELETE method:");
	         deletePerson(878569020, sqLite);
		
		  
//Select and Print ALL remaining from Table
		  System.out.println("The following remaining entries are called using a SELECT ALL statement");
		  result = statement.executeQuery( "SELECT * FROM PERSON;" );
		  while ( result.next() ) 
		  {
		         social = result.getLong("SSN");
		         fName = result.getString("FNAME");
		         lName  = result.getString("LNAME");
		         age = result.getInt("AGE");
		         cCard = result.getLong("CCARD");
		         
		         System.out.println( "SSN = " + social );
		         System.out.println( "First Name = " + fName );
		         System.out.println( "Last Name = " + lName );
		         System.out.println( "Age = " + age );
		         System.out.println( "Credit Card Number = " + cCard );
		         System.out.println();
		  }
	}
//insert person method takes the person object and the connection to add the object to the PERSON table in the database
	static public void insertPerson(Person person, Connection sqLite)
	{
		Statement statement = null;
		String command = null;
		try
		{
		statement = sqLite.createStatement();
		command = "INSERT INTO PERSON (SSN,FNAME,LNAME,AGE,CCARD) " +
				"VALUES ("+person.social+", '"+person.fName+"', '"+person.lName+"', "+person.age+", "+person.cCard+" );"; 
		statement.executeUpdate(command);
		System.out.println("Person Successfully Added");
		}
		catch(Exception E)
		{
		System.out.println("Person Failed to be added");
		}
	}
//select person method takes the social and connection to search for the primary key and return an object with the selected primary key as
//its contents.
	static public Person selectPerson(long social, Connection sqLite)
	{
		Statement statement = null;
		ResultSet result = null;
		String fName = null;
		String lName = null;
		int age =0;
		long cCard = 0;
		try
		{
		statement = sqLite.createStatement();
		result = statement.executeQuery("SELECT * FROM PERSON WHERE SSN = " + social + ";");
	    fName = result.getString("FNAME");
	    lName  = result.getString("LNAME");
	    age = result.getInt("AGE");
	    cCard = result.getLong("CCARD");
		
		}
		catch(Exception E)
		{
		System.out.println("Person Not Found");
		}
		
		Person person = new Person(fName, lName, age, social, cCard);
		
		return person;
	}
//delete person method takes the primary key as well as database and deletes the associated entry in that database
	static public void deletePerson(long social, Connection sqLite)
	{
		String command = null;
		Statement statement = null;
		ResultSet result = null;
		try
		{
		statement = sqLite.createStatement();
		result = statement.executeQuery("SELECT * FROM PERSON WHERE SSN = " + social + ";");
		System.out.println( "SSN = " + social );
         System.out.println( "First Name = " + result.getString("FNAME"));
         System.out.println( "Last Name = " + result.getString("LNAME") );
         System.out.println( "Age = " + result.getInt("AGE") );
         System.out.println( "Credit Card Number = " + result.getLong("CCARD") );
         System.out.println();
		
		}
		catch(Exception E)
		{
		System.out.println("Person Not Found");
		}
		try
		{
		statement = sqLite.createStatement();
	     command = "DELETE FROM PERSON WHERE SSN = "+social+";";
	     statement.executeUpdate(command);
		}
		catch(Exception E)
		{
		System.out.println("Entry Not Found");
		}
	}
}

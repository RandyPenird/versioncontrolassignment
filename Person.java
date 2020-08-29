/*
 * Randy Edward Penird July 17 2020
 * the following application manipulates and displays information stored in a connected SQLite database. Showing an understanding of the 
 * manipulation of data
 */
public class Person {
	
	String fName;
	String lName;
	int age;
	long social;
	long cCard;
	
	public Person(String fName, String lName, int age, long social, long cCard)
	{
		this.fName = fName;
		this.lName = lName;
		this.age = age;
		this.social = social;
		this.cCard = cCard;
	}
}

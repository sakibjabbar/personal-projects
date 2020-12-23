package proj3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DataBase
{
	private StudentRecord[] data;
	private Index ID, First, Last;
	private int next;
	
	Scanner input = new Scanner(System.in);
	
	// constructor populates DataBase and Index arrays
	public DataBase()
	{
		data = new StudentRecord[100]; // database given capacity of 100
		ID = new Index(); // creates new Index
		First = new Index(); // creates new Index
		Last = new Index(); // creates new Index
		next = 0; // initialize array pointer
		
		try {
			Scanner file = new Scanner(new File("COSC311_Proj1_Data.txt")); // creates file
			
			while (file.hasNextLine())
			{
				String line = file.nextLine(); // reads file line by line
				String[] array = line.split(" "); // splits each line into manageable pieces
				
				data[next] = new StudentRecord(array[0], array[1], array[2]); // populate DataBase
				
				Last.insert(array[0], next); // populate last name Index
				First.insert(array[1], next); // populate first name Index
				ID.insert(array[2], next); // populate ID index

				next++; //increment pointer
			}
			file.close(); // close file for data safety purposes
			
		} catch (FileNotFoundException | NullPointerException a) { // catches errors
			System.out.print("Error!");
		}
	}
	
	public void addIt() // choice 1
	{
		// prompt for first name
		System.out.print("Enter first name: ");
		String inputFirst = input.nextLine();
		
		// prompt for last name
		System.out.print("Enter last name: ");
		String inputLast = input.nextLine();
		
		// prompt for ID number
		System.out.print("Enter ID: ");
		String inputID = input.nextLine();
		
		for (int i = 0; i < next; i++)
		{
			if (data[i].getID().compareTo(inputID) == 0) // checks if ID is in use
			{
				System.out.println("ID already in use"); // if ID in use
				return; // exits method
			}
		}
		
		data[next] = new StudentRecord(inputLast, inputFirst, inputID); // add to DataBase if ID not in use
		
		First.insert(inputFirst, next); // add to First index
		Last.insert(inputLast, next); // add to Last index
		ID.insert(inputID, next); // add to ID index
		
		next++; // increment pointer
		
		System.out.println("ID added successfully");
	}
	
	public void deleteIt() // choice 2
	{
		// prompt for ID
		System.out.print("Enter ID: ");
		String inputID = input.nextLine();
		
		int i = ID.find(inputID); // search for ID
		
		if (i == -1) // if ID not found
		{
			System.out.println("ID not found");
			return; // exit
		}

		First.delete(data[i].getFirst()); // delete from First index
		Last.delete(data[i].getLast()); // delete from Last index
		ID.delete(data[i].getID()); // delete from ID index
		
		System.out.println("Deleted");
	}
	
	public void findIt() // choice 3
	{
		System.out.print("Enter ID: ");
		String inputID = input.nextLine(); // prompt user for ID, for which we will search the index ID
		
		int i = ID.find(inputID); // stores the value of either the database location or -1 (if not found)
		
		if (i == -1) // if not found
			System.out.println("ID not Found");
		else // if found
			System.out.println(data[i]);
	}

	public void ListByIDAscending() // choice 4
	{
		ID.printIt(data, ID.getRoot(), 1);
	}
	
	public void ListByFirstAscending() // choice 5
	{
		First.printIt(data, First.getRoot(), 1);
	}
	
	public void ListByLastAscending() // choice 6
	{
		Last.printIt(data, Last.getRoot(), 1);
	}
	
	public void ListByIDDescending() // choice 7
	{
		ID.printIt(data, ID.getRoot(), 2);
	}
	
	public void ListByFirstDescending() // choice 8
	{
		First.printIt(data, First.getRoot(), 2);
	}
	
	public void ListByLastDescending() // choice 9
	{
		Last.printIt(data, Last.getRoot(), 2);
	}
	
}

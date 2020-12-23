package proj3;
public class StudentRecord
{
    private String last; // last name
    private String first; // first name
    private String ID; // ID number
    
    StudentRecord(String a, String b, String c) // pulls from database constructor and text file
    {
        this.last = a;
        this.first = b;
        this.ID = c;
    }  
   
    public String toString() // overrides built-in toString()
    {
        return last + " " + first + " " + ID;
    }
    
    public String getLast() // returns last name
    {
    	return last;
    }
    
    public String getFirst() // returns first name
    {
    	return first;
    }
    
    public String getID() // returns ID number
    {
    	return ID;
    }
}
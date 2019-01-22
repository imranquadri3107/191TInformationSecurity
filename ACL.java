
/*Implement an ACL such that when a user login, he/she can type username 
(i) R: display files that the  user has read access.
(ii)  W: display files that the user has write access. 
(iii)  X: display files that the user has execute access.

Imran Quadri = 109882298
CSCI=191T InfoSec

 */
 
package ACL;
import java.util.*;
import java.io.File;
import java.lang.String;

public class ACL 
{
    public static class User
    {
    static Node head;  
     static class Node 
	{
        String username;
	String right;
       
        Node next;
        Node( String u, String r) 
		{ 
		username = u; 
                right = r;
		next=null; } 
        }
    

    public static void append(String u, String r)
    {
      
        Node new_node = new Node(u,r);
 
        
        if (head == null)
        {
            head = new Node(u,r);
            return;
        }
 
 
        new_node.next = null;
 

        Node last = head; 
        while (last.next != null)
            last = last.next;
 
       
        last.next = new_node;
        return;
    }
    }
public static String usernameDB [] = {"user1", "user2", "user3", "user4", "user5", "user6", "user7", "user8", "user9", "user10" };
public static String passwordDB [] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

	
public static int authenticateUser(String name, String password)
{
int check;
for(int i=0; i<10; i++)
	{
	 if ( (name.equals(usernameDB[i])) && (password.equals(passwordDB[i])))
	   {
	   return 0;
           }
         else 
             return 1;
	}
return 0;
}
    public static void main(String[] args) throws Exception
    {
       Scanner sc = new Scanner(System. in); 
   
        User u = new User ();
         int count = 0, i=0;
        String[] content = new String[10];
	
	
        File file = new File("C:\\Users\\Mr Imran Quadri\\Desktop\\New folder (3)\\data.txt");
        Scanner scan = new Scanner(file);
 
    System.out.println("Enter Username: ");
    String name = sc.nextLine();
    System.out.println("Enter Password: ");
    String password = sc.nextLine();
    int found = authenticateUser(name, password);
	
	if (found ==0)
	      System.out.println("Login successful");
	else
        {
          System.out.println("Username or Password Incorrect");	
          System.exit(0);
        }
	String str=null;
        String right = "R W X";
        
        
       
       
        while(scan.hasNext())
        {
         content[count] = scan.nextLine();
         String[] split = content[count].split("\\s+");
         u.append(split[0],right );
         count++;
        do {
        System.out.println();
        
        System.out.println("Which File you want to Access ?");
        System.out.println("R: Read");
        System.out.println("W: Write");
        System.out.println("X: Excecute");
        
        System.out.println("Type  R, W or X: ");
        char ch = sc.next().charAt(0);
        System.out.println();
        System.out.println("____________________________________________________");
        
        if (name.equals(split[0]))
        {
          int k;
            if (ch=='R')
            {
               System.out.println(name + ", List of files with read access:");
                for(k=1;k<split.length;k++)
               {
               if(split[k].equals("R"))
               System.out.println("File"+k);
               } 
            }
            else if (ch=='W')
            {
               System.out.println(name + "List of files with  write access:");
                for(k=1;k<split.length;k++)
               {
                   if(split[k].equals("W"))
               System.out.println("File"+k);
               } 
            }
            else if (ch=='X')
            {
                System.out.println(name + "List of files with excecute access to the following files :");
               for(k=1;k<split.length;k++)
               {
                   if(split[k].equals("X"))
               System.out.println("File"+k);
               } 
              }
            else
                System.out.println("Please enter valid character");
          
        }
        System.out.println("Would you like to continue(Y/N)?");
      str = sc.next();
    }while(str.equals("Y"));
       }
      

 }
}

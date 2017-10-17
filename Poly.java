import java.util.*;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import java.lang.*;

public class Poly 
{
	public static void main(String args[])
	{
		System.out.print("Plain Text: ");
	    char ch='a';
	    char characterList[] = new char[26];
		for(int i=0;i<26;i++)
		{
			
		System.out.print(ch+" ");
		int c=(int)ch;
		c+=1;
		ch=(char)c;
		}
		System.out.print("\nC1        : ");
		ch = 'f';
		for (int x = 0; x<26; x++) 
		{
			characterList[x] = ch;
			if (ch == 'z')
			{
				ch = 'a';
			}
			else
				ch++;
			System.out.print(characterList[x]+" ");
		}
		
		System.out.print("\nC2        : ");
		ch = 't';
		for (int x = 0; x<26; x++) {
			characterList[x] = ch;
			if (ch == 'z')
			{
				ch = 'a';
			}
			else
				ch++;
			System.out.print(characterList[x]+" ");
		}
		while(true)
		{
		System.out.print("\nEnter the message m:\n");
		
		Scanner s=new Scanner(System.in);
		String OriginalMessage=s.nextLine();
		String upperStr = OriginalMessage.toUpperCase();
		String EncryptedMessage="";
		int k1=5,k2=19;
		int flag=2;
		for(int i=0;i<upperStr.length();i++)
		{
			
            
		     int a= (int)upperStr.charAt(i);
		     if(a!=32)
		     {
			 if(flag==2)
			{
				if(a>=86)
					a-=21;
				else
					a+=5;
				flag=1;
				
			}
			else if(flag==1)
			{
			    if(a>=72)
			        a-=7;
			    else
			    	a+=19;
			    flag=2;
			    
			}
		    }
	        //EncryptedMessage.concat((Character.toString((char)a)));
			EncryptedMessage+=(char)a;
			
		}
		flag=2;
		System.out.println("The encrypted message is:"+EncryptedMessage);
	
        String DecryptedMessage="";
		for(int i=0;i<EncryptedMessage.length();i++)
		{
		    int a= (int)EncryptedMessage.charAt(i);
		    if(a!=32)
		    {
			if(flag==2)
			{
				if(a<=69)
				{
					a+=21;
				}
				else
					a-=5;
				

				flag=1;
				
			}
			else if(flag==1)
			{
				
				if(a>=65 && a<=83)
				{
					a+=7;
				}
				else 
				   a-=19;
				
				flag=2;
				
			}
		    }
	        
			DecryptedMessage+=(char)a;
		}  	
	
		System.out.println("The decrypted message is:"+DecryptedMessage);
	}
	}
}

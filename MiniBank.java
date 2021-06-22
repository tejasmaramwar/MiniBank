import java.util.*;
import java.io.*;

class io
{
    Scanner s=new Scanner(System.in);
    //The 'chio()' function is used for InputMismatchException handling
	int chio(String n)
	{
	    int ip=0;
			System.out.print("Enter Your "+n+":");
			while(ip!=11)
			{
				 try
				 {
					 ip=s.nextInt();
					 if(ip<0)
					 System.out.println(n+ " can't be negative");
					 else
					 break;
				 }
				 catch(InputMismatchException e)
				 {
					 System.out.println(n+" cannot include any alphabets or special characters"); 
				 }
				 System.out.println("(enter '11' to exit)");
				 System.out.print("Enter proper "+n+":");
				 s.nextLine();
				 ip=0;
			}
			return(ip);
	}
}
class MiniBank1 extends io
{
	Scanner s=new Scanner(System.in);
	
	int a[][];//={{1000,1234,10000},{1001,2345,20000},{1002,3456,30000},{1003,4567,50000},{1004,5678,10000}};
	int i=0, accno=0, pass,t=0;
	String name[];//={"Varad","Krishna","Hetal","Tejas","Mayur"};
	int count;
	int k,l,password,password1;
       
void Account()
{
    //The commented segment in the below try block and above  is in only used when we run the prog for 1st time
    //This is used to store the data in the file initially
	try
	{   
//		ObjectOutputStream op= new ObjectOutputStream(new FileOutputStream("MiniBank.txt"));
//		op.writeObject(a);
		ObjectInputStream ip= new ObjectInputStream(new FileInputStream("MiniBank.txt"));
		a=(int[][])ip.readObject();
//		op.close();
		ip.close();
//		ObjectOutputStream op1= new ObjectOutputStream(new FileOutputStream("MiniBank1.txt"));
//        op1.writeObject(name);
		ObjectInputStream ip1= new ObjectInputStream(new FileInputStream("MiniBank1.txt"));
		name =(String[])ip1.readObject();
//		op1.close();
		ip1.close();
	}
	catch(Exception e)
	{
		e.printStackTrace();
    }
    //Start of user interactive part
    System.out.println("Welcome to the AISSMS MiniBank !");
    //The below choice is provided so that anyone can create their account
    System.out.println("Enter 1 to create account else enter any other number to continue");
    int ch=chio("choice");
    if(ch==11)
    {
        System.exit(0);
    }
    if(ch==1)
    {
    createaccount();
    return;
    }
	accno=chio("Account Number");

	if(accno ==11)
        return;
    //The below 'while' loop checks if account exists
    //The value of 'i' is used to refer to specific account's data  
	while(i < a.length && a[i][0] != accno)
	  i++; 
   if(i==a.length)
   {
       //This will be executed only if account number is not found in the array
	  System.out.println("Account No does not exists"); 
	  return;	  
   }
   else  
    pass=chio("Password");
    if(pass==11)
        System.exit(0);
    int x=1;
	while(a[i] [1 ] != pass)
	{
      System.out.println("You have entered wrong password '"+x+"' times");
      //The below 'if' statement is used to ensure that wrong password is not enterd more than 3 times
      if(x==3)
      {
      System.out.println("You have crossed the limit");
      System.exit(0);
      }
      System.out.println("You have '"+(3-x)+"' chances left to enter correct password");
	  pass=chio("Password");
	      if (pass==11)
	      {
	    	  return;
          }
        x++;
	}
		System.out.println("\nWelcome "+name[i]+"\n");
		getChecking();

}

void withdraw()
{
	int rupees=chio("Withdrawal Amount");
	if(rupees==11)
        System.exit(0);;
	while(rupees<=0&&rupees!=1)
	{
        //This ensures amt can't be zero or less than it
		System.out.println("cant withdraw negative or 0 amount");
		System.out.println("Enter 1 to cancel withdrawal");
		System.out.println("Enter your withdrawl amount : ");
        rupees = chio("Withdrawal amount");
        if(rupees==11)
            System.exit(0);
    }
    //the below is executed if amount to withdraw is more than available balance
	if(rupees>a[i][2])
	{
		System.out.println("insufficient balance");
		return;
	}
	else
		a[i][2]-=rupees;
	 System.out.println("New  Account balance:"+ a[i][2]);
}

void deposit()
{
	int bal=chio("Deposit Amount");
	if(bal==11)
	    return;
	if(bal==0&&bal<0)
	{
        System.out.println("Amt can't be zero or negative");
		return;
	}
	else
        a[i][2]+=bal;

	System.out.println("New  Account balance:"+ a[i][2]);
}

void deleteaccount()
{
    //Checks if any account exits in the data
    //i.e. whether arrays 'name' and 'a' are empty
    if(name.length==0|| a.length==0)
    {
        System.out.println("No account exists in the bank's data. Please create a new account");
        System.exit(0);
    }
	System.out.println("Are you Sure you want to delete your account? ");
	System.out.println("Enter 1 to confirm else enter 0 ");
    int choice1 = chio("Choice");
    if(choice1==11)
        System.exit(0);
    //New arrays are initiated whose lengths are '-1' than that of initial arrays
	String[] newname = new String[(name.length)-1];
	int[][] newa=new int[(a.length)-1][3];
	if(choice1 == 0)
	{
		return;
	}
	else
	{
		System.out.println("Confirm Account number :");
        int accno=chio("Account number");
        if(accno==11)
        System.exit(0);
        //Checks if the account to delete exists
		while(i<a.length && a[i][0]!=accno)
			   i++;
		   if(i==a.length)
			{
			    System.out.println("Account No does not exists");
				return;
			}
		   else
              pass=chio("password");
              if(pass==11)
                System.exit(0);
			  while(a[i][1]!=pass&&pass!=0)
			   {
				  System.out.println("Wrong password");
			      System.out.println("Enter '11' to exit");
				  System.out.println("Enter password");
				  pass=chio("password");
				      if (pass==11)
				      {
                        System.exit(0);
				      }
			   }
			  System.out.println("Deleting account");
			  System.out.println("Please Wait..");
              //The old array name is as it is stored in the new array
              //And the account's data to be deleted is replaced with last account's data in the array
			  for(int t=0;t<(name.length)-1;t++)
			  {
				  if(t==i)
				  newname[t] = name[(name.length)-1];
				  else
				  newname[t] = name[t];
			  }
			  for(int j=0;j<(a.length)-1;j++)
			  {
				for(int t=0;t<3;t++)
				{
					if(j==i)
					newa[j][t]=a[(a.length)-1][t];
					else
                    newa[j][t]=a[j][t];
				}
              }
              //the new updated array is updated in the file which overides the old array
			  try
			  {
				  ObjectOutputStream op= new ObjectOutputStream(new FileOutputStream("MiniBank.txt"));
				  op.writeObject(newa);
				  op.close();
				  ObjectOutputStream op1= new ObjectOutputStream(new FileOutputStream("MiniBank1.txt"));
				  op1.writeObject(newname);
				  op1.close();
			}
			catch(Exception e1)
			  {
				System.out.print("From catch of del");          
			  }
			return;
		}
	}

void createaccount()
{
	System.out.println("Welcome to AISSMS IOIT ATM!");
	System.out.print("Enter your Name:");
    String name1 = s.nextLine();
    //New arrays are initiated whose lengths are '+1' than that of initial arrays
	String[] nname = new String[(name.length)+1];
	int[][] na =new int[(a.length)+1][3];
	System.out.println("Welcome " + name1);
	
    password=checkpassword();
    
    int amount = chio("Amount to be deposited");
    if(amount==11)
        System.exit(0);
    //The old array name is as it is stored in the new array
    //And the account's data to be deleted is replaced with last account's data in the array
	for(t=0;t<name.length;t++)
	{
		nname[t] = name[t];
	}
	nname[(nname.length)-1]=name1;
	count=a[0][0];
	for(k=0;k<a.length;k++)
	{
		if(count<a[k][0])
		count=a[k][0];
		for(l=0;l<3;l++)
		{
			na[k][l] = a[k][l];
		}
	}
	na[(nname.length)-1][0] = count+1;
	na[(nname.length)-1][1] = password;
	na[(nname.length)-1][2] = amount;
    System.out.println("Your account no is "+na[(nname.length)-1][0]);
    //the new updated array is updated in the file which overides the old array
	try
    {
        ObjectOutputStream op= new ObjectOutputStream(new FileOutputStream("MiniBank.txt"));
		op.writeObject(na);
		op.close();
		ObjectOutputStream op1= new ObjectOutputStream(new FileOutputStream("MiniBank1.txt"));
		op1.writeObject(nname);
		op1.close();
	}
	catch(Exception e1)
	{
		System.out.print("From catch of create");          
	}
	return;
}
//The below method is used to accept the password
int checkpassword()
{
     password = chio("Password");
     if(password==11)
     System.exit(0);
	System.out.println("Please re-enter to confirm your password ");
	 password1 = chio("Password");
	if(password != password1)
	{
		System.out.println("Password doesn't match");
        checkpassword();
	}
    return (password);
}
//This fuction is used instead of while loop
void getChecking()
{
        System.out.println("Enter 1 - View Balance");
		System.out.println("Enter 2 - Withdraw Funds");
		System.out.println("Enter 3 - Deposit Funds");
		System.out.println("Enter 4 - Exit\nEnter 5 - To Delete Account");
		System.out.println("Enter 6 - To Create account");
		int selection=chio("Choice");
		if(selection==11)
		   return;

		switch (selection) 
		{
		case 1:
		     System.out.println("Please wait....Checking Account Balance: \n");
		     System.out.println("Your Account Balance: " + a[i][2] + "\n");
             getChecking();
			 break;

		case 2:
             withdraw();
			 getChecking();
			 break;
			
		case 3:
			 deposit();
             getChecking();
			 break;

		case 4:
		try
		{
			ObjectOutputStream op= new ObjectOutputStream(new FileOutputStream("MiniBank.txt"));
			op.writeObject(a);
			op.close();
			ObjectOutputStream op1= new ObjectOutputStream(new FileOutputStream("MiniBank1.txt"));
			op1.writeObject(name);
			op1.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();          
		}
		    break;
		case 5:
			deleteaccount();
			return;
			
		case 6:
			createaccount();
			return;
		
		default:
			System.out.println("\n" + "Invalid choice." + "\n");
			getChecking();
			
		}
}


}    
class MiniBank
{
	public static void main(String[] args)
	{
		try
		{
			MiniBank1 acc = new MiniBank1();
			acc.Account();			
		}
	    finally
	    {
			System.out.println("Thank You for using AISSMS MiniBank, bye.");
	    }
	 }
}
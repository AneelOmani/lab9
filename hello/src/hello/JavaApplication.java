package hello;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
//import java.util.Arrays;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

import java.sql.PreparedStatement;



/**
 *
 * @author nmal.bscs15seecs
 */
public class JavaApplication {
      private int orderprice;                    //attributes for storing information
      private String[]Order;
      private static String address;
      private String[] cook;                             //cook information
      private int time;
      int orderid;
      
      void JavaApplication()
      {
          orderprice=0;
          address=null;                             //Constructor 
          cook=new String[4];
      }
      void SetOrder(String[]order,int y)
      {
          int l =y;
           Order=new String[l];
           for(int i=0;i<y;i++)
           {
               Order[i]=order[i];                      //this is for setting order
           }
      }
      void setorderid(int x)
      {
    	  orderid=x;
      }
      int getorderid()
      {
    	  return orderid;
      }
      void ViewCook()
      {
          int l=Order.length;
          int y=4;
          for(int i=0;i<l  && l<8;i++)                     //viewing cook performance 
          {
              if(l>4)
              {
                                              //where they are working
              System.out.print("Cook" +(i-y)+ "is working on      "+Order[i]);
              System.out.print("\n");
              }
              
              System.out.print("Cook" +i+ "is working on      "+Order[i]);
              System.out.print("\n");
          }
      }
      void SetOrderPrice(int price)                    //Set order price  
      {
          orderprice=price;
      }
      void printmenu(PreparedStatement prep_statement,Connection connection) throws SQLException
      {
    	  ResultSet rs=null;
    	  String select = "SELECT * FROM FOOD";
			prep_statement= connection.prepareStatement(select);
			rs =prep_statement.executeQuery(select);
			//Extract data from result set
			//System.out.print(rs.getInt(2));
			int j=1;
			while (rs.next()){
				// Wrong this will generate an error
				//String value0 = rs.getString(0);
				// Correct!
				
				System.out.print("  "+j+"  ");
				System.out.print(rs.getString(1)+"\t\t");       //print them as your menu
				System.out.print(rs.getInt(2));
				System.out.print("\n");
				j+=1;
			}
      }
      void printOrder(PreparedStatement prep_statement) throws SQLException                           //print order if x=2 else if x==1 then it will print menu
      {
    	  ResultSet rs=null;
			rs =prep_statement.executeQuery();
			//Extract data from result set
			//System.out.print(rs.getInt(2));
			int j=1;
			while (rs.next()){
				// Wrong this will generate an error
				//String value0 = rs.getString(0);
				// Correct!
				
				System.out.print("  "+j+"  ");
				System.out.print(rs.getInt(1)+"\t\t");       //print them as your menu
				System.out.print(rs.getString(2));
				System.out.print("\n");
				j+=1;
			}
               
      }
      void setaddress()                                //this will set the address 
      {
          Scanner sc=new Scanner(System.in);
         System.out.print(" Enter your address of delivery   ");
         address=sc.nextLine(); 
      }
      static String getaddress()
      {
    	  return address;               //this returns the address 
      }
      
      Boolean pickup(int x)                  //check whether shop is open or not  
      {
    	   
          if((x<11) || (x >22))                    //if your order is out of time 
          {
              System.out.print(" Shop IS Closed   \n\n");
              return false;
          }
          else
          {
               System.out.print(" your delivery will be in 30 mintues    \n\n");     //else it will be in 30 mintues 
               return true;
          }
              
      }
      
   
    public static void main(String[] args) throws SQLException {
        
        JavaApplication obj=new JavaApplication();
        Scanner sc=new Scanner(System.in);
        int y=0;
        int price1=0;
        int orderid=1122;
        int customerid=02;
        String order[]=new String[15];//declaration and instantiation
        
        System.out.println("-------- MySQL JDBC Connection Testing ------------");
        

		try {
			Class.forName("com.mysql.jdbc.Driver");            //include your driver 
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
   
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lab6","root", "");    //makes a connection with databases 

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
		PreparedStatement prep_statement=null,order_statement=null;
		CallableStatement cstmt = null;
		String select = "SELECT * FROM orderdetail where orderid=?";
		order_statement= connection.prepareStatement(select);
		ResultSet rs=null;
		if (connection != null) {                               //if connection created succesfully
			System.out.println("You made it, take control your database now!");
			String SQL = "{call getEmpName(?,?,?)}";
			cstmt = connection.prepareCall (SQL);
			cstmt.setInt(1,1112);
			cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
			cstmt.registerOutParameter(3, java.sql.Types.INTEGER);
			cstmt.execute(); // could use executeUpdate()
			// get String OUT and INOUT
			int b = cstmt.getInt(2);
			int d= cstmt.getInt(3);
			System.out.print(b);
			System.out.print(d);
			obj.printmenu(prep_statement,connection);
			int x;
		        char c='y';                                      //gets the order from customer 
		        for(int i=0;i<15 && c=='y';i++)
		        {
		        	System.out.print("Select one value from above list\n");
			        x=sc.nextInt();
		         switch(x){  
		    case 1:order[i]="karai     450";price1+=450; break;                     //check order each item
		    case 2:order[i]="Soup   250";price1+=250;break;  
		    case 3: order[i]="pepperPopper   200";price1+=200;break;
		    case 4:order[i]="Glazed Chicken Wings   300";price1+=300;break;  
		    case 5: order[i]="one pot lasme   100";price1+=100;break;
		    case 6:order[i]="Easy Meatloaf   500";price1+=500;break;  
		    case 7:order[i]="Simple Baked Chicken Breasts  250";price1+=250;break;  
		    case 8: order[i]="World's Best Lasagna   350";price1+=350;break;
		    case 9:order[i]="Crispy and Tender Baked Chicken Thighs  450";price1+=450;break;  
		    case 10: order[i]="Homemade Mac and Cheese    1000";price1+=1000;break;
		    case 11:order[i]="Stuffed Peppers    700";price1+=700;break;  
		    case 12:order[i]="CHINESE FRIED RICE   100";price1+=100;break;  
		    case 13: order[i]="TACO BELL QUESADILLAS   100";price1+=100;break;
		    case 14:order[i]="EASY FRIED RICE   100";price1+=100;break;  
		    case 15: order[i]="MOZZARELLA STICKSn   150";price1+=150;break; 
		    default:System.out.println("invalid");  
		    }
		         y=y+1;
		           System.out.print("do you select another item\n");                          //if user wants to select another item
		           c=sc.next().charAt(0);
		        }
		  
		        System.out.print("1. For Order Delivery  \n");          //for delievry or pick up
		        System.out.print("2. For Order Pickup  \n");
		        x=sc.nextInt();
		        
		        int hours=LocalDateTime.now().getHour();           //gets number of hours of system
		       String sql = "Insert into orderdetail(orderid,itemdesc) values(?,?)";      //insert into order detail
	        	prep_statement = connection.prepareStatement(sql);             //prepared statement 
		       switch(x)
		       {  
		       case 1:obj.setaddress();break;          //call the function to enter address 
		       case 2:obj.pickup(hours);break;
		       }
		        obj.SetOrder(order,y);                    //sets the order              
		        obj.SetOrderPrice(price1);            //sets the order price 
		        String str = "";
		        for(int a=0;a<y;a++)                                   //use prepared statement 
		        {
		        	str+=order[a];                   //put data in orderdetail table 
		        	str+=" ";
		        	prep_statement.setInt(1, orderid); // This would set age
		        	prep_statement.setString(2,order[a]); // This would set FN
		        	int rows = prep_statement.executeUpdate();
		        }
		        
		        obj.setorderid(orderid);              //set order id 
		        //System.out.print(str);
		        String str1=getaddress();                    //get address from user 
		
		        //System.out.print(hours);                   //put in order table      
		        sql="Insert into orderinfo(orderid,customerid,orderprice,time)values("+orderid+","+customerid+","+price1+","+hours+")";
		       // System.out.print(sql);
		        int iReturnValue = prep_statement.executeUpdate();
		        //System.out.print(iReturnValue);
		        
				order_statement.setInt(1,orderid);
				obj.printOrder(order_statement);
		        orderid+=1;
		        customerid+=1;
		      //  obj.printOrder(2);    //print it  
		        obj.ViewCook();            //view cook performance 
		        
		} else {
			System.out.println("Failed to make connection!");
		}
    }
    
}

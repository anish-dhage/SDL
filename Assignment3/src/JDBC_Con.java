
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;
import java.lang.*;


class jdbc {
	String url;
	String user;
	String password;
	Connection con;
	String sql;
	Statement stmt;
	ResultSet rs;
	int seats;
	private ArrayList l1 = new ArrayList<Integer>();
	private Queue q1 = new PriorityQueue<Integer>();
	private Stack s1 = new Stack<Integer>();
	CallBackFunctionInterface cbf
	Scanner sc = new Scanner(System.in);
	jdbc(){	
		seats = 0;
		url="jdbc:mysql://10.10.10.54:3306/t31105db";;
		user="t31105";
		password="t31105";
		
		this.cbf = new CallBackFunctionClass();
		con=null;
		stmt=null;
	}
	public void open(){
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
			  
            //Reference to connection interface 
            con = DriverManager.getConnection(url,user,password); 
  
			//STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      con = DriverManager.getConnection(url, user, password);
		   //   System.out.println("Creating table in given database...");
		      stmt = con.createStatement();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
	}
	
	public void close(){
		try {
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insert(){ //make this asynchronous
		      System.out.println("Enter Seat No and Name : \n");
		      int seatno = sc.nextInt();
		      String Name = sc.next();
		      //stmt.executeUpdate(sql);
		      if(seats<16){
		    	  sql = "insert into BookingQueue values( "+seatno+" , '"+Name+"' , 'Y' )";
		    	  l1.add(seatno);
		    	  s1.add(seatno);
		      }
		      else{
		    	  sql = "insert into BookingQueue values( "+seatno+" , '"+Name+"' , 'N' )";
		    	  q1.add(seatno);
		    	  s1.add(seatno);
		      }
		      try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      seats++;
		      
	}
	public void display(){
		
	    System.out.println("Seat No\t\tName\tConfirmation\t");
	   	sql = "SELECT * FROM BookingQueue";
	    try {
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				
		         int r  = rs.getInt("SeatNo");
		         String n = rs.getString("Name");
		         String c = rs.getString("Conf");
		         
		         System.out.println(r+"\t\t"+n+"\t"+c+"\t");
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeData(){ //make this synchronous and use multi-threading (threadpool) 
		int coreCount = Runtime.getRuntime().availableProcessors();
	    ExecutorService service = Executors.newFixedThreadPool(coreCount);
	    sql = "SELECT * FROM BookingQueue";
	    try {
			rs = stmt.executeQuery(sql);
			while(rs.next()){
		        
		         Thread t1 = new Thread(new Runnable()
				 {
					 public void run() {
						try {
							int r = rs.getInt("SeatNo");
							String n = rs.getString("Name");
							l1.add(r);
							
							cbf.callBackList(l1, jdbc);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				         
					 }
				 },"Adding to List");
				 
				 Thread t2 = new Thread(new Runnable()
				 {
					 public void run() {
						try {
							int r = rs.getInt("SeatNo");
							String n = rs.getString("Name");
							s1.push(r);

							cbf.callBackPriorityQueue(q1, jdbc);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				         
					 }
				 },"Adding to Stack");
				 
				 service.execute(t1);
		         service.execute(t2);
		 		 	
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}		 
	}
}

public class JDBC_Con {
	

	public static void main(String[] args) {

		int op = 0;
		Scanner sc = new Scanner(System.in);
		jdbc A = new jdbc();
		A.open();
		while(op != 5){
			System.out.print("Enter Choice: \n1.Add Entry\n2.Remove Entry\n3.Display Data\n4.Copy from Database\n5.Exit\n");
			op = sc.nextInt();
			switch(op){
			
			case 1: 
				A.insert();
				break;
				
			case 2: 
				//A.remove;
				break;
				
			case 3: 
				A.display();
				break;
				
			case 4: 
				A.writeData();
				break;
				
			case 5: 
				break;

			}
		}
		A.close();
		sc.close();
	}	
}

import java.util.*;

//Booking Queue class which keeps track of Train Seats, Waiting Queue for Seats 
//and Food Requirements of the passengers.
class BookingQueue <T> {
	private ArrayList <T> q1 = new ArrayList<T>();
	private Queue <T> q2 = new PriorityQueue<T>();
	private Stack <T> s1 = new Stack<T>();
	Scanner sc = new Scanner(System.in);
	
	//Add Details of a Person to the List of Train Seats, if Train is fully booked,
	//add to waiting queue. Also ask for Food requirements.
	void add(T e){
		System.out.println("Booking");
		if(q1.size()<16){
			q1.add(e);
		}
		else{
			System.out.println("Booking Full, Added to Waiting Queue");
			q2.add(e);
		}
		System.out.println("Do you want in-transit Food ? (y/n)");
		String op = sc.next();
		if(op.equals("y")){
			s1.add(e);
		}
	}
	
	//Check Number of Booked Seats and Waiting Queue
	void size(){
		System.out.println("Size of Booking List is : "+q1.size());
		System.out.println("Size of Waiting Queue is : "+q2.size());
	}
	
	//Clear the Entire List
	void delete(){
		q1.clear();
	}
	
	//Remove particular Seat Number from the List
	void remove(int i){
		q1.remove(i);
		if(q2.size() > 0){
			T e = q2.poll();
			q1.add(e);
		}
	}
	
	//Modify the Details of a seat number
	void modify(T e,int p){
		q1.set(p,e);
	}
	
	//Display the Seats of the Bus, the Waiting List and the Food Requirements.
	void display(){
		int c = 0;
		Iterator<T> i = q1.iterator();
		while(c<16){
			if(i.hasNext()){
				System.out.print(i.next()+" ");
			}
			else{
				System.out.print("[]");
			}
			c++;
			if (c%2 == 0 && c%4 !=0){
				System.out.print("\t");
			}
			else if (c%2 == 0 && c%4 ==0){
				System.out.print("\n");
			}
		}
		System.out.println("Booking List : "+q1);
		System.out.println("Waiting List : "+q2);
		System.out.println("Food List : "+s1);
		System.out.println();
	}
	
	//Remove a Person from the Food List
	void spop(){
		s1.pop();
	}
}

public class Assignment1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("1.Age\n2.Name");
		int ch = 0;
		int seatno = 0;
		ch = sc.nextInt();
		int op;
		switch(ch){
		case 1:
			//This Case uses Generic Class to operate on Details of type : INTEGER
			//Using a HashMap to keep track of the details of each Seat Number.
			LinkedHashMap<Integer,Integer> h1= new LinkedHashMap<Integer, Integer>(); 
			BookingQueue <Integer>A = new BookingQueue<Integer>();  
			op = 0;
			seatno = 0;
			
			while(op != 9){
				System.out.print("Enter Choice: \n1.Add to Queue\n2.Remove from Queue\n3.Display Queue\n4.Modify Details\n5.Size of Queue\n6.Delete Queue\n7.Remove from Food List\n8.Check Seat\n9.Exit\n");
				op = sc.nextInt();
				switch(op){
				
				case 1: 
					System.out.println("Enter Age :");
					int input = sc.nextInt();
					A.add(input);
					//Add to HashMap
					h1.put(seatno , input);
					seatno += 1;
					break;
					
				case 2: 
					System.out.println("Which seat to remove?");
					int seat = sc.nextInt();
					A.remove(seat);
					h1.remove(seat);
					seatno -= 1;
					//Moves up the Seats of the HashMap after a cancellation
					if(seatno > 1){
						int opr;
						for (int i = seat ; i<16 ; i++){
							opr = h1.get(seat+1);
							h1.put(seat, opr);
						}
					}
					break;
					
				case 3: 
					A.display();
					break;
					
				case 4: 
					System.out.println("Enter Seat Number to Modify : ");
					int ip = sc.nextInt();
					System.out.println("Enter New Age :");
					int age1 = sc.nextInt();
					A.modify(age1,ip);
					h1.put(ip,age1);
					
				case 5: 
					A.size();
					break;
					
				case 6: 
					A.delete();
					break;
				
				case 7:
					A.spop();
					break;
					
				case 8: 
					//Search in HashMap to show details of a Seat Number
					System.out.println("Enter Seat Number to Check : ");
					int searchno = sc.nextInt();
					int ageseat = h1.get(searchno);
					System.out.println("Age of Seat No. " + searchno + " is " + ageseat);
					break;
					
				case 9: 
					break;	
				}
			}
			break;
			
		case 2:
			//This Case uses Generic Class to operate on Details of type : STRING
			BookingQueue<String>B = new BookingQueue<String>();  
			LinkedHashMap<Integer,String> h2= new LinkedHashMap<Integer, String>(); 
			op = 0;
			seatno = 0;
			
			while(op != 9){
				System.out.print("Enter Choice: \n1.Add to Queue\n2.Remove from Queue\n3.Display Queue\n4.Modify Details\n5.Size of Queue\n6.Delete Queue\n7.Remove from Food List\n8.Check Seat\n9.Exit\n");
				op = sc.nextInt();
				switch(op){
				
				case 1: 
					System.out.println("Enter Name :");
					String input = new String();
					input = sc.next();
					B.add(input);
					h2.put(seatno,input);
					seatno += 1;
					break;
					
				case 2: 
					System.out.println("Which seat to remove?");
					int seat = sc.nextInt();
					B.remove(seat);
					h2.remove(seat);
					seatno -= 1;
					String opr;
					for (int i = seat ; i<16 ; i++){
						opr = h2.get(i+1);
						h2.put(i, opr);
					}
					break;
					
				case 3: 
					B.display();
					break;
					
				case 4: 
					System.out.println("Enter Seat Number to Modify : ");
					int ip = sc.nextInt();
					System.out.println("Enter New Element");
					String name = sc.next();
					B.modify(name,ip);
					h2.put(ip, name);
					break;
					
				case 5: 
					B.size();
					break;
					
				case 6: 
					B.delete();
					break;
				
				case 7:
					B.spop();
					break;
					
				case 8: 
					System.out.println("Enter Seat Number to Check : ");
					int searchno = sc.nextInt();
					String ageseat = h2.get(searchno);
					System.out.println("Age of Seat No. " + searchno + " is " + ageseat);
					break;
					
				case 9: 
					break;	
				}
			}
			break;
		}
		sc.close();
	}
}































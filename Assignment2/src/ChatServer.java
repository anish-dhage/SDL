import java.net.*; 
import java.io.*; 
  
public class ChatServer 
{ 
    //initialize socket and input stream 
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream  input   = null; 
    private DataOutputStream out     = null; 
    private DataInputStream in       = null; 
  
    // constructor with port 
    public ChatServer(int port) 
    { 

        // starts server and waits for a connection 
        try
        { 
            server = new ServerSocket(port); 
            System.out.println("Server started"); 
  
            System.out.println("Waiting for a client ..."); 
  
            socket = server.accept(); 
            System.out.println("Client accepted"); 
  
            // takes input from the client socket 
            in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream()));
            
            input  = new DataInputStream(System.in); 
            
            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream()); 
  
            String line = ""; 
  
            // reads message from client until "Over" is sent 
            while(!line.equals("Exit")){
	            do 
	            { 
	                try
	                { 
	                    line = in.readUTF(); 
	                    System.out.println(line); 
	                    if(line.equals("Exit")){
	                    	break;
	                    }
	  
	                } 
	                catch(IOException i) 
	                { 
	                    System.out.println(i); 
	                } 
	            } while (!line.equals("Over"));
	            
	            do 
		        { 
		            try
		            { 
		                if(line.equals("Exit")){
	                    	break;
	                    }
		                line = input.readLine(); 
		                out.writeUTF(line);
		            } 
		            catch(IOException i) 
		            { 
		                System.out.println(i); 
		            } 
		        }while (!line.equals("Over"));
            }
            System.out.println("Closing connection"); 
  
            // close connection 
            socket.close(); 
            in.close(); 
            input.close();
            out.close();
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        ChatServer server = new ChatServer(5010); 
    } 
} 
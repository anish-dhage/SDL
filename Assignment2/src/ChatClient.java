// A Java program for a Client 
import java.net.*; 
import java.io.*; 
  
class ChatClient 
{ 
    // initialize socket and input output streams 
    private Socket socket            = null; 
    private DataInputStream  input   = null; 
    private DataOutputStream out     = null; 
    private DataInputStream in       = null; 
  
    // constructor to put ip address and port 
    public ChatClient(String address, int port) 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
  
            // takes input from terminal 
            input  = new DataInputStream(System.in); 
  
            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream()); 
            
            in = new DataInputStream( 
                    new BufferedInputStream(socket.getInputStream()));
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
  
        // string to read message from input 
        String line = ""; 
  
        // keep reading until "Over" is input 
        while(!line.equals("Exit")){
	        do
	        { 
	            try
	            { 
	                line = input.readLine(); 
	                out.writeUTF(line); 
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
                    line = in.readUTF(); 
                    System.out.println(line);
  
                } 
                catch(IOException i) 
                { 
                    System.out.println(i); 
                } 
            }while (!line.equals("Over"));
        }
  
        // close the connection 
        try
        { 
            input.close(); 
            out.close(); 
            socket.close();
            in.close();
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        ChatClient client = new ChatClient("10.10.12.0", 6969); 
	} 
} 


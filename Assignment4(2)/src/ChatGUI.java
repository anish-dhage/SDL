import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
  
public class ChatGUI {

		public class TextFieldExample implements ActionListener{   
		    JButton b1,b2;
		    int choice;
		    TextFieldExample(){  
		    			    	
		        JFrame f= new JFrame();     
		        b1=new JButton("Client ");  
		        b1.setBounds(50,200,100,50);  
		        b2=new JButton("Server ");  
		        b2.setBounds(150,200,100,50);
		        b1.addActionListener(this);  
		        b2.addActionListener(this);  
		        f.add(b1);f.add(b2);
		        f.setSize(512,1024);  
		        f.setLayout(null);  
		        f.setVisible(true);	
		    }         
		    
		    public void actionPerformed(ActionEvent e) { 
		        int a = 0; 
		        if(e.getSource()==b1){  
		        	ChatClient client = new ChatClient("10.10.13.70", 5010);
		        }
		        else if(e.getSource()==b2){  
		        	ChatServer server = new ChatServer(5010);
		        }		        
		    }  
		}

	public static void main(String[] args) {
		TextFieldExample A = new ChatGUI().new TextFieldExample();
	}

}

import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

import javax.swing.*;

public class GUI {
	
		public class CustomOutputStream extends OutputStream {
		    private JTextArea textArea;
	
		    public CustomOutputStream(JTextArea textArea) {
		        this.textArea = textArea;
		    }
	
		    @Override
		    public void write(int b) throws IOException {
		        // redirects data to the text area
		        textArea.append(String.valueOf((char)b));
		        // scrolls the text area to the end of data
		        textArea.setCaretPosition(textArea.getDocument().getLength());
		        // keeps the textArea up to date
		        textArea.update(textArea.getGraphics());
		    }
		}
	
		public class RadioButtonExample extends JFrame implements ActionListener{    
			JRadioButton rb1,rb2;    
			JButton b;    
			RadioButtonExample(){      
				rb1=new JRadioButton("Int");    
				rb1.setBounds(100,50,100,30);      
				rb2=new JRadioButton("Char");    
				rb2.setBounds(100,100,100,30);    
				ButtonGroup bg=new ButtonGroup();    
				bg.add(rb1);bg.add(rb2);    
				b=new JButton("click");    
				b.setBounds(100,150,80,30);    
				b.addActionListener(this);    
				add(rb1);add(rb2);add(b);    
				setSize(300,300);    
				setLayout(null);    
				setVisible(true);    
			}    
			public void actionPerformed(ActionEvent e){    
				if(rb1.isSelected()){     
					TextFieldExample B = new GUI().new TextFieldExample(1);
				}    
				if(rb2.isSelected()){    
					TextFieldExample B = new GUI().new TextFieldExample(2);
				}    
			}			
		}
		
		public class TextFieldExample implements ActionListener{  
		    JTextField tf1,tf3;  
		    JButton b1,b2,b3,b4;  
		    BookingQueue A;
		    int choice;
		    TextFieldExample(int x){  
		    	
		    	JTextArea textArea = new JTextArea(400, 400);
		    	textArea.setBounds(50, 400, 400, 250);
		    	JScrollPane sp;
		    	PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		    	System.setOut(printStream);
		    	System.setErr(printStream);
		    	sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    	sp.setBounds(50, 400 , 400, 300);
		    	
		    	
		        JFrame f= new JFrame();  
		        tf1=new JTextField();  
		        tf1.setBounds(50,50,150,20);   
		        b1=new JButton("Add ");  
		        b1.setBounds(50,200,100,50);  
		        b2=new JButton("Display ");  
		        b2.setBounds(150,200,100,50);  
		        b3=new JButton("Size ");  
		        b3.setBounds(50,300,100,50);  
		        b4=new JButton("Remove ");  
		        b4.setBounds(150,300,100,50); 
		        b1.addActionListener(this);  
		        b2.addActionListener(this);  
		        b3.addActionListener(this);  
		        b4.addActionListener(this);
		        f.add(tf1);f.add(b1);f.add(b2);f.add(b3);f.add(b4); 
		        f.add(sp);
		        f.setSize(512,1024);  
		        f.setLayout(null);  
		        f.setVisible(true);
		        choice = x;
		        if( x == 1){
		        	A = new BookingQueue<Integer>();
		        }
		        else{
		        	A = new BookingQueue<String>();
		        }
		    }         
		    
		    public void actionPerformed(ActionEvent e) { 
		        String s1=tf1.getText();
		        int a = 0; 
		        if(e.getSource()==b1){  
		        	if (choice == 1){
		        		a=Integer.parseInt(s1);
		        		A.add(a);
		        	}
		        	else{
		        		A.add(s1);
		        	}
		        }
		        else if(e.getSource()==b2){  
		        	A.display();
		        }
		        else if(e.getSource()==b3){  
		        	A.size();
		        }
		        else if(e.getSource()==b4){ 
		        	a=Integer.parseInt(s1);
		        	A.remove(a);
		        }
		        
		    }  
		}
		
		
		public static void main(String[] args) {
			RadioButtonExample A = new GUI().new RadioButtonExample();	

	}
}

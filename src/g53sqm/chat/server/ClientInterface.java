package g53sqm.chat.server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class ClientInterface extends JFrame{

	private JPanel contentPane;

	JButton btnNewButton = new JButton("Join Network");
	JButton btnExit = new JButton("Exit");
	
	JTextPane textPane = new JTextPane();
	
	private Runner ci = new Runner();
	private Server server;
	private JTextField textField;
	
	public Socket socket;
	public Client chatClient;
	
	private BufferedReader in;
	private PrintWriter out;
	private volatile boolean running;
	
	final static String str = "";
	static String str2 = "";
	//private Socket client = new Socket();
	
	private Connection conn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientInterface frame = new ClientInterface();
					frame.setTitle("Kai's Messenger");
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//JOptionPane.showInputDialog("Input Username",str);
				
				//Strng adas = getString(str);
				
				try {
					socket = new Socket("localhost", Runner.PORT);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					System.out.println("You Have Already Logged In");
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("You Have Already Logged In");
					e.printStackTrace();
				}
				
				if (socket.isBound() == true){
					System.out.println("Socket Connected");
				}
				else{
					System.out.println("Fail to Connect");
				}
				
				
				chatClient = new Client(socket);
				Thread t = new Thread(chatClient);
				t.start();
				
				conn = new Connection(socket, server);
				
				//chatClient.out.println("IDEN <" + str + ">");
				//chatClient.out.flush();
				
				//conn.messageForConnection("HELLO");
	
				//return str;
			}
		});
		btnNewButton.setBounds(642, 515, 128, 25);
		contentPane.add(btnNewButton);
		
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("" + textField.getText());
				try {
					chatClient.out.println(textField.getText());
					chatClient.in.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				textField.setText("");
				
			}
		});
		textField.setBounds(12, 393, 535, 109);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//JTextPane textPane = new JTextPane();
		//textPane.setBounds(12, 13, 535, 367);
		//contentPane.add(textPane);
		textPane.setText("");
		StyledDocument doc = textPane.getStyledDocument();
		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setBounds(12, 13, 535, 367);
		contentPane.add(scrollPane);
		
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(559, 13, 211, 489);
		contentPane.add(textPane_1);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println("" + textField.getText());
				//server.broadcastMessage(textField.getText());
				str2 = textField.getText();
				try {
					chatClient.out.println(str2);
					textField.setText("");
					String str = chatClient.in.readLine();
					//textPane.setText(chatClient.in.readLine());
					//doc.insertString(0, "", null);
					doc.insertString(doc.getLength(), str + "\n", null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSend.setBounds(12, 515, 128, 25);
		contentPane.add(btnSend);
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//conn.quit();
			}
		});
		btnExit.setBounds(559, 515, 71, 25);
		contentPane.add(btnExit);
	}
	
	/*public String getString(String str){
		return str;
	}
	
	public String setString(){
		return str;
	}*/
	
	//public String str3 = getString();
}

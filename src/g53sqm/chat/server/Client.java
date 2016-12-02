package g53sqm.chat.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client implements Runnable{

	public Socket socket;
	
	public Client(Socket socket){
		
			this.socket = socket;
		}
	
	//private Socket client;
	//private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	public PrintWriter out;
	private volatile boolean running;
	
	public BufferedReader in;
	
	String line = "";
	String line2 = "";
	
	//DataInputStream din=new DataInputStream(this.socket.getInputStream());
	
	private Runner ci = new Runner();
	private ClientInterface cInterface = new ClientInterface();
	//DataInputStream din=new DataInputStream(cInterface.socket.getInputStream());
	private Server server;
	//private ArrayList<String> users = server.getUserList();
	


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		String message = "";
		//String line = "";
		//String line2 = "";
		//ArrayList<String> users = server.getUserList();
		/*for (i = 0; i < users; i++){
			String[] strings = (String[]) users.get(i);
		}*/
		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out = new PrintWriter(this.socket.getOutputStream(), true);
			
			//while(true){
				//out.println("IDEN <" + cInterface.str + ">");
				line = in.readLine();
				//cInterface.textPane.setText(line);
				//out.println(message);
				System.out.println("------Client------");
				System.out.println("Server prints: " + line);
				//System.out.println("HELLO " + users);
			//}
			
		} catch (IOException e) {
			System.out.println("in or out failed");
		}
		
		
		
		/*while(true){
			try {
				line = in.readLine();
				//validateMessage(line);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("Client is writing");
		}*/
	}
	
	/*public void printLine() throws IOException{
		in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		out = new PrintWriter(this.socket.getOutputStream(), true);
		
		line = in.readLine();
		System.out.println("Server prints: " + line);
	}*/
}

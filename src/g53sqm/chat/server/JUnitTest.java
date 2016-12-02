package g53sqm.chat.server;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.ServerSocket;

import org.junit.Test;

public class JUnitTest {
	
	//Runner ci = new 
	
	static int port = 9000;
	private Runner ci = new Runner();
	
	Server server;
	
	@Test
	public void isServerInitialized() throws IOException{

		ServerSocket thisServer = null;
		//server = new Server(port);
		
		try
		{
			thisServer = new ServerSocket(port);
			assertTrue(thisServer.accept().equals(ci.server));
		}catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	
	
}

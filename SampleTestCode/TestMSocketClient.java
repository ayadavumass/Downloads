import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import edu.umass.cs.msocket.MSocket;
import edu.umass.cs.msocket.mobility.MobilityManagerClient;
public class TestMSocketClient
{
	public static void main(String[] args) throws IOException
	{
		String serverName = args[0];
		System.out.println("Opening a connection to "+serverName);
		MSocket msock = new MSocket(serverName, 0);
		System.out.println("Connection open completed");
		OutputStream outstream = msock.getOutputStream();
		InputStream inpstream = msock.getInputStream();
		byte[] byteArray = new byte[1000];
		int i=0;
		while(i < 10)
		{
			System.out.println(i+"/10 Writing to server");
			outstream.write( new String("hello world from client").getBytes() );
			inpstream.read(byteArray);
			System.out.println(i+"/10 Message from server: "+new String(byteArray));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			i++;
		}
		System.out.println("Closing MSocket and Mobility manager");
		msock.close();
		MobilityManagerClient.shutdownMobilityManager();
		System.out.println("Client application complete");
	}
}

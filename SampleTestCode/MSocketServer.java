import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import edu.umass.cs.msocket.MServerSocket;
import edu.umass.cs.msocket.MSocket;
import edu.umass.cs.msocket.mobility.MobilityManagerServer;

public class MSocketServer
{
             public static void main(String[] args) throws IOException
             {
                      String serverName = args[0];
		      System.out.println("Starting a server with name "+serverName);
                      MServerSocket mserv = new MServerSocket(serverName);
                     //while(true)
                      {
			      	   System.out.println("Listening for incoming connections");
                                   MSocket msocket = mserv.accept();
                                   OutputStream outstream = msocket.getOutputStream();
                                   InputStream inpstream = msocket.getInputStream();
                                   byte[] byteArray = new byte[1000];
                                   int i=0;
                                   while(i<10)
                                   {
					   System.out.println(i+"/10 Writing to client");
					   outstream.write( new String("hello world from server ").getBytes() );
                                           inpstream.read(byteArray);
                                           System.out.println(i+"/10 Message from client: "+new String(byteArray));
                                                 try {
                                                         Thread.sleep(2000);
                                                      }
                                                 catch (InterruptedException e)
                                                 {
                                                       e.printStackTrace();
                                                 }
                                                 i++;
                                   }
				   System.out.println("Closing MSocket connection");
                                   msocket.close();
                      }
		      System.out.println("Closing MServerSocket and shutting down mobility manager");
                      mserv.close();
                      MobilityManagerServer.shutdownMobilityManager();
		      System.out.println("Server application complete");
             }
}

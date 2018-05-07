
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/*creates server for tcp protocol
*/
/**
 *
 * @author sb
 */
public class server_tcp implements Runnable{
    Thread[] t;
    server_tcp(){
        t=new Thread[network.num_threads];
        for(int h=0;h<network.num_threads;h++)
            t[h]=new Thread(this,String.valueOf(h));
        System.out.println("Server running");
        for(Thread j:t)
            j.start();
    }
    @Override
    public void run(){
        try {
            ServerSocket s=new ServerSocket(network.server_port++);
            Socket client = s.accept();
            InputStream in=client.getInputStream();
            OutputStream out=client.getOutputStream();
            for( ; ; ){
            in.read();
            out.write(network.use);
            }
        } catch (IOException ex) {
            System.out.println("In Client_Tcp: "+ex);
        }
    }
}

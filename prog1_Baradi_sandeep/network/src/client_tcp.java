
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
/*creates client for tcp protocol 
*/
/**
 *
 * @author sb
 */
public class client_tcp implements Runnable{
    static long start_time;
    Thread[] t;
    client_tcp(){
        t=new Thread[network.num_threads];
        for(int h=0;h<network.num_threads;h++){
          t[h]=new Thread(this,String.valueOf(h));
            }
        for(Thread j:t)
            j.start();
}
    @Override
    public void run() {
        try {//creating a socket for this thread with unique port but under the same server
            Socket s=new Socket(network.server_name,network.server_port++);
            //Obtain input and output stream
            InputStream in=s.getInputStream();
            OutputStream out=s.getOutputStream();
            //network.latency for round trip
            start_time=System.currentTimeMillis();
            //send request
            out.write(network.use);
            //get response
            in.read();
            network.latency=network.latency+System.currentTimeMillis()-start_time;
            //Troughput round trip
            start_time=System.currentTimeMillis();
            for(int i=0;i<network.loop;i++){
                out.write(network.use);
                out.flush();
            }
            for(int i=0;i<network.loop;i++)
                in.read();
            network.enlapsed_time=network.enlapsed_time+(System.currentTimeMillis()-start_time)/1000;
            s.close();
        } catch (IOException ex) {
            System.out.println("In Client_Tcp: "+ex);
        }
    }
}

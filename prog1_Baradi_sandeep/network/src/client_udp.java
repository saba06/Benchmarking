
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*creates client for udp protocol
 */

/**
 *
 * @author sb
 */
public class client_udp implements Runnable{
    static long start_time;
    Thread[] t;byte[] c=new byte[1];
    client_udp(){
        t=new Thread[network.num_threads];
        for(int h=0;h<network.num_threads;h++){
            t[h]=new Thread(this,String.valueOf(h));
            }
        for(Thread j:t)
            j.start();
    }
    public void run(){
        try {
            InetAddress address;
            address = InetAddress.getByName(network.server_name);
            DatagramSocket s=new DatagramSocket(network.client_port++);
            DatagramPacket p=new  DatagramPacket(network.use,network.use.length,address,network.server_port++);
            DatagramPacket n=new  DatagramPacket(c,1);
            //network.latency for round trip
            start_time=System.currentTimeMillis();
            //send request
            s.send(p);
            //get response
            s.receive(n);
            network.latency=network.latency+System.currentTimeMillis()-start_time;
            //Troughput round trip
            start_time=System.currentTimeMillis();
            for(int i=0;i<network.loop;i++)
               s.send(p);
            for(int i=0;i<network.loop;i++)
                s.receive(n);
            network.enlapsed_time=network.enlapsed_time+(System.currentTimeMillis()-start_time)/1000;
            s.close();
        } catch(Exception ex) {
            System.out.println("In Client_Udp: "+ex);
        }
    }
}

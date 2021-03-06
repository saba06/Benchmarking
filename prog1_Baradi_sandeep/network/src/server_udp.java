
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*creates server for udp protocol
 */

/**
 *
 * @author sb
 */
public class server_udp implements Runnable{
    byte[] c;
    Thread[] t;
    server_udp(){
        t=new Thread[network.num_threads];
        for(int h=0;h<network.num_threads;h++)
            t[h]=new Thread(this,String.valueOf(h));
        for(Thread j:t)
            j.start();
    }
    public void run(){
        try {
            InetAddress address;
            address = InetAddress.getByName(network.client_name);
            DatagramSocket s=new DatagramSocket(network.server_port++);
            DatagramPacket p=new  DatagramPacket(network.use,network.use.length,address,network.client_port++);
            DatagramPacket n=new  DatagramPacket(c,1);
            System.out.println("Server running");
            for( ; ; ){
                s.receive(p);
                s.send(n);
            }
        } catch (Exception ex) {
            System.out.println("In Server_Udp: "+ex);
          }
        }
}




import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
main class takes user input to create client & server 
*/

/**
 *
 * @author sb
 */
public class network {
    public static String server_name;
    public static String client_name;
    volatile public static int server_port=2788;
    volatile public static int client_port=2654;
    static final double data=500; 
    static double Tput=0;
    static long latency=0;
    static long enlapsed_time=0;
    static int loop;
    static int num_threads;
    static byte[] use;
    static byte[] b=new byte[1]; 
    static byte[] kb=new byte[1024];
    static byte[] kb64=new byte[65536];
    static FileWriter fr;
    public static void main(String[] args) throws IOException, InterruptedException{
        fr=new FileWriter("results",true);
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter private Domain name of other machine:");
        String name=scan.next();
        fr.append("\n"+name+"\t");
        System.out.println("Enter num of threads to execute {1,2}");
        num_threads=scan.nextInt();fr.append("num_threads"+String.valueOf(num_threads)+"\t");
        System.out.println("enter the block size: {b,kb,kb64}");
        switch(scan.next()){
            case"b":use=b;loop=524288000;fr.append("b");
                break;
            case"kb":use=kb;loop=512000;fr.append("kb");
                break;
            case"kb64":use=kb64;loop=8000;fr.append("kb64");
                break;
            default:break;
        }
        fr.append("\n");
        System.out.println("enter the typeofmachine_protocol: {client_tcp,server_tcp,client_udp,server_udp}"); 
        switch(scan.next()){
            case "client_tcp":
                server_name=name;fr.append("client_tcp");
                client_tcp clienttcp=new client_tcp();
                for(Thread j:clienttcp.t)
                   j.join();
                
        Tput=(data/enlapsed_time);
        fr.append("latency: "+String.valueOf(latency)+"\t"+"Throughput: "+String.valueOf(Tput));
        fr.close();
                break;
            case "server_tcp":
                client_name=name;fr.append("server_tcp");
                server_tcp servertcp=new server_tcp();
                for(Thread j:servertcp.t)
                   j.join();
                break;
            case "client_udp":
                server_name=name;fr.append("client_udp");
                client_udp clientudp=new client_udp();
                for(Thread j:clientudp.t)
                   j.join();
                
        Tput=(data/enlapsed_time);
        fr.append("latency: "+String.valueOf(latency)+"\t"+"Throughput: "+String.valueOf(Tput));
        fr.close();
                break;
            case "server_udp":
                client_name=name;fr.append("server_udp");
                server_udp serverudp=new server_udp();
                for(Thread j:serverudp.t)
                   j.join();
                break;
            default:
                System.out.println("Pls enter:client_tcp or client_udp or server_tcp or server_udp");
                break;
        }
    }
    
}

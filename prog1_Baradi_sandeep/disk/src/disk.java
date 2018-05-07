
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*Implements class write &(then) read operations to find 
Throughput & latency
 */

/**
 *
 * @author sb
 */
public class disk {
    static final double data=500;
    static double Tput;
    static long latency;
    static long enlapsed_time;
    static long start_time;
    static int  loop;
    static String path="/home/ec2-user/pa1/disk/big.txt";
    static byte[] use;
    static byte[] b=new byte[1]; 
    static byte[] kb=new byte[1024];
    static byte[] mb=new byte[1048576];
    static FileWriter rs;
    static file_random rd;static file_sequence se;
    public static void main(String[] args) throws IOException{
        rd = new file_random();se = new file_sequence();
        rs=new FileWriter("results",true);
        String[] input=new String[2];
        Scanner scan=new Scanner(System.in);
        sequence seq;random rand;
        System.out.println("enter the threads to run: {1,2} "); 
        input[0]=scan.next();
        System.out.println("enter the block size: {b,kb,mb}");
        input[1]=scan.next();
        switch(input[1]){
            case"b":use=b;loop=524288000;
                break;
            case"kb":use=kb;loop=512000;
                break;
            case"mb":use=mb;loop=500;
                break;
            default:break;
        }
        rs.append("For args: "+input[0]+"\t"+input[1]+"\n");
        
        rs.append("Sequential :"+"\n");
        rs.append("Write :"+"\n");
        seq = new sequence(Integer.parseInt(input[0]),true);
        try{ 
            for(Thread j:seq.t){
             j.join();
         }
        }catch(Exception e){
            System.out.println("In disk for "+e);
        } 
        {   enlapsed_time=(System.currentTimeMillis()-start_time);
           Tput=(data*1000/enlapsed_time); //-mb/s
            rs.append("Latency: "+String.valueOf(latency/1000000)+"\t"+"Throughput: "+String.valueOf(Tput)+"\n"); 
        }
        Runtime.getRuntime().exec("free & sync");
        rs.append("Read :"+"\n");
        seq = new sequence(Integer.parseInt(input[0]),false);
        try{ 
            for(Thread j:seq.t){
             j.join();
         }
        }catch(Exception e){
            System.out.println("In disk for "+e);
        } {
            enlapsed_time=(System.currentTimeMillis()-start_time);
           Tput=(data*1000/enlapsed_time); //-mb/s
            rs.append("Latency: "+String.valueOf(latency/1000000)+"\t"+"Throughput: "+String.valueOf(Tput)+"\n"); 
        }
        
        
        rs.append("Random :"+"\n");
        rs.append("Write :"+"\n");
        rand = new random(Integer.parseInt(input[0]),true);
        try{ 
            for(Thread j:seq.t){
             j.join();
         }
        }catch(Exception e){
            System.out.println("In disk for "+e);
        } {
            enlapsed_time=(System.currentTimeMillis()-start_time);
           Tput=(data*1000/enlapsed_time); //-mb/s
            rs.append("Latency: "+String.valueOf(latency/1000000)+"\t"+"Throughput: "+String.valueOf(Tput)+"\n"); 
        }
        Runtime.getRuntime().exec("free & sync");
        rs.append("Read :"+"\n");
        rand = new random(Integer.parseInt(input[0]),false);
        try{ 
            for(Thread j:seq.t){
             j.join();
         }
        }catch(Exception e){
            System.out.println("In disk for "+e);
        }{
            enlapsed_time=(System.currentTimeMillis()-start_time);
           Tput=(data*1000/enlapsed_time); //-mb/s
            rs.append("Latency: "+String.valueOf(latency/1000000)+"\t"+"Throughput: "+String.valueOf(Tput)+"\n"); 
        }
        rs.close();
        Runtime.getRuntime().exec("free & sync"); //to free files blocks in cache 
    } 
}

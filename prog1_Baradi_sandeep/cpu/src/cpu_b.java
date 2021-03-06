
import java.util.Scanner;

/*The program takes user input for number of threads and calls ops classes with execute to return GIOPS and GFLOPS
 */

/**
 *
 * @author sb
 */
public class cpu_b {
    public static void main(String[] args){
        int num;long enlapsed_time;
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter the number of threads to execute");
        num=scan.nextInt();
        //Running ips to find Giops
        ops i=new ops(num,false);
        try{ 
            for(Thread j:i.t){
             j.join();
         }
        }catch(Exception e){
            System.out.println("In cpu_b for ips"+e);
        } finally{
            enlapsed_time=(System.currentTimeMillis()-i.start_time)/1000;
            double iops=(num*Math.pow(10,9)*35)/enlapsed_time;
            System.out.println("GIOPS: "+(iops/Math.pow(10,9)));
        }
        //Running ops to find Gflpos
        ops f=new ops(num,true);
        try{
            for(Thread j:f.t)
             j.join();
        }catch(Exception e){
            System.out.println("In cpu_b for ops"+e);
        }finally{
            enlapsed_time=(System.currentTimeMillis()-f.start_time)/1000;
            double flops=(num*Math.pow(10,9)*35)/enlapsed_time; 
            System.out.println("GFLOPS: "+(flops/Math.pow(10,9)));  
        }
    }
    
}

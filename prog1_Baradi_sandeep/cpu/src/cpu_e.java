
import java.io.IOException;

/*This class creates objects of infinite_flops, which inturn creats
theads.It also implements timers to stop the threads after 10 min 
and to collect samples at 1 sec intervals*/
/**
 *
 * @author sb
 */
public class cpu_e {
       static boolean flag=true;
       static int num_threads=4;
    public static void main(String[] args) throws IOException{
        file plots;
        plots= new file("iops");
        stopTimer stop;sample s;
        infinate_ops i=new infinate_ops(num_threads,false);
           stop = new stopTimer();
           s = new sample();
         try{ 
            for(Thread j:i.t){
             j.join();
            }sample.t.join();
        }catch(Exception e){
            System.out.println("In ops for iops"+e);
        }
         
         System.out.println("for flops");
         plots= new file("flops");
        infinate_ops f=new infinate_ops(num_threads,true);
        stop=new stopTimer();
        s=new sample();
         try{ 
            for(Thread j:f.t){
             j.join();
         }sample.t.join();
        }catch(Exception e){
            System.out.println("In ops for flops"+e);
        }
        
        
  }
}

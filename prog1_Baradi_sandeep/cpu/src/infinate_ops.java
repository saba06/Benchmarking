
import java.util.Random;

/*extends interface Runnable to create a thread that loops
excuting floting point operations
untill flag is set to false(i.e by stopTimer) after 10 min
*/
/**
 *
 * @author sb
 */
public class infinate_ops implements Runnable{
    static Thread[] t;static boolean flops;static long count;
    int i;Random rand=new Random();
    infinate_ops(int numof_threads,boolean flops){
        cpu_e.flag=true;
        infinate_ops.flops=flops;
        t=new Thread[numof_threads];
        for(int h=0;h<numof_threads;h++)
            t[h]=new Thread(this,String.valueOf(h));
        for(Thread j:t)
            j.start();
    }
    public static synchronized void incrementCount() {
        count++;
    }
    @Override
    public void run(){
        try{ 
            if(flops==false)
            {   int i;
                i=rand.nextInt();
                for(;;){
                    i=((i*1)*i+(i+i)*1)+((i*2)*i+(i+i)*2)-((i*3)*i+(i+i)*3)+((i*4)*i+(i+i)*4)-((i*5)*i+(i+i)*5)+((i*6)*i+(i+i)*6);
                     incrementCount();
                    if(!cpu_e.flag)
                        break;
                }
            }
            else
            {   float i;
                i=rand.nextFloat();
                for(;;){
                    i=((i*1)*i+(i+i)*1)+((i*2)*i+(i+i)*2)-((i*3)*i+(i+i)*3)+((i*4)*i+(i+i)*4)-((i*5)*i+(i+i)*5)+((i*6)*i+(i+i)*6);
                    incrementCount();
                    if(!cpu_e.flag)
                        break;
                }
            }
            }
        catch (Exception e){
            System.out.println("In infinite_ops: "+e);
        }
    }
}


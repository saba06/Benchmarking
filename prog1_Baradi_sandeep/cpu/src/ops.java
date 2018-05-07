/*This class implements threads to perform integer operations
 */
import java.util.*;
/**
 *
 * @author sb
 */
public class ops implements Runnable{
    long start_time;static boolean flops;
    Random rand=new Random();
    Thread[] t;
    ops(int numof_threads,boolean flops){
        this.flops=flops;
        t=new Thread[numof_threads];
        for(int h=0;h<numof_threads;h++)
            t[h]=new Thread(this,String.valueOf(h));
        start_time=System.currentTimeMillis();
        for(Thread j:t)
            j.start();
    }
    @Override
    public void run(){
        try{
            if(flops==false)
            {   int i;
                i=rand.nextInt();
                for(int count=0;count<Math.pow(10,9);count++){
                    i=((i*1)*i+(i+i)*1)+((i*2)*i+(i+i)*2)-((i*3)*i+(i+i)*3)+((i*4)*i+(i+i)*4)-((i*5)*i+(i+i)*5)+((i*6)*i+(i+i)*6);
                }
            }
            else
            {   float i;
                i=rand.nextFloat();
                for(int count=0;count<Math.pow(10,9);count++){
                    i=((i*1)*i+(i+i)*1)+((i*2)*i+(i+i)*2)-((i*3)*i+(i+i)*3)+((i*4)*i+(i+i)*4)-((i*5)*i+(i+i)*5)+((i*6)*i+(i+i)*6);
                }
            }
        }
        catch (Exception e){
            System.out.println("In ops: "+e);
        }
    }
}

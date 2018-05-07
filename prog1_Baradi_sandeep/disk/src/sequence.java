
import java.io.IOException;
/*class used to read & write to file sequentially (based on value of write flag)
 */

/**
 *
 * @author sb
 */

public class sequence implements Runnable{
    static boolean write;
    Thread[] t;
    sequence(int num_threads,boolean write){
        sequence.write=write;
        try{
        if(!write){
                disk.latency=System.nanoTime();
                disk.se.read();
                disk.latency=System.nanoTime()-disk.latency;
            } 
        else{    disk.latency=System.nanoTime();
                  disk.se.write();
                disk.latency=System.nanoTime()-disk.latency;
            }
        }catch (IOException ex) {
                System.out.println("In sequence latency: "+ex);
            }
        
        t=new Thread[num_threads];
        for(int h=0;h<num_threads;h++)
            t[h]=new Thread(this,String.valueOf(h));
        disk.start_time=System.currentTimeMillis();
        for(Thread j:t)
            j.start();
    }
    @Override
    public void run(){
            try {
                if(sequence.write==false){
                for(int i=1;i<disk.loop;i++)
                    disk.se.read();
                }
                else {
                        for(int i=1;i<disk.loop;i++){
                            disk.se.write();
                        }
                 }
            }catch (IOException ex) {
                System.out.println("In read_sequential:"+ex);
            }
    }
}


import java.io.IOException;

/*class used to read or write to file (depending on value of write flag) , it creates threads*/
 /**
 * @author sb
 */
public class random implements Runnable{
    static boolean write;
    Thread[] t;
    random(int num_threads,boolean write){
        random.write=write;
        try{
        if(!write){
                disk.latency=System.nanoTime();
                disk.rd.read();
                disk.latency=System.nanoTime()-disk.latency;
            } 
        else{
        
                disk.latency=System.nanoTime();
                disk.rd.write();
                disk.latency=System.nanoTime()-disk.latency;
            }
        }catch (IOException ex) {
                System.out.println("In random disk.latency: "+ex);
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
            if(random.write==false){
                for(int i=1;i<disk.loop;i++){
                    disk.rd.read();
                }
            }else{
                for(int i=1;i<disk.loop;i++){
                    disk.rd.write();
                }
            }
        } catch (IOException ex) {
                System.out.println("In read_random:"+ex);
        }
    }
}

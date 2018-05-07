
import java.util.concurrent.TimeUnit;


/*extends TimerTask to run code to that stops all threads
after 10 min
*/
/**
 * 
 * @author sb
 */
public class stopTimer implements Runnable{
    Thread t;
    stopTimer(){
    t=new Thread(this,"stopTimer");
    t.start();
    }@Override
    public void run(){
        try {
            TimeUnit.MINUTES.sleep(10);
            cpu_e.flag=false;
            file.csv.flush();
            file.csv.close();
        } catch (Exception ex) {
           System.out.println("In stopTimer: file able to close"+ex);
        } 
    }
}

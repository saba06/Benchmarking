
import java.util.concurrent.TimeUnit;

/*
collects samples for GIOPS and GFLOPS to write them in CSV file every second
 */

/**
 *
 * @author sb
 */
public class sample implements Runnable{
    static Thread t;int time_sec;
    sample(){
    t=new Thread(this,"sample");
    t.start();
    }@Override
    public void run(){
        try {while(cpu_e.flag){
            long num=infinate_ops.count;
            infinate_ops.count=0;
            double gflops=(num*35)/Math.pow(10,9);
            file.csv.append((String.valueOf(++time_sec)));
            file.csv.append(file.COMMA_DELIMITER);
            file.csv.append(String.valueOf(gflops));
            file.csv.append(file.NEW_LINE_SEPARATOR);
            TimeUnit.SECONDS.sleep(1);
        }
        } catch (Exception ex) {
            System.out.println("In sample: cannot write to file"+ex);
        }
    }
}

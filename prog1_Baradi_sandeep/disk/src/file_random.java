
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Scanner;

/*opens an existing file big.txt for random access
 */

/**
 *
 * @author sb
 */
public class file_random {
    static RandomAccessFile fr;
    Random rand = new Random();
    file_random() throws IOException{
        try{
        fr=new RandomAccessFile(disk.path,"rws");
        } catch (FileNotFoundException ex) {
        System.out.println("In file_random:"+ex);
        }
    }
    
public synchronized void write() throws IOException{
    fr.write(disk.use);
    fr.seek(rand.nextInt((int) fr.length()));
}
public synchronized void read() throws IOException{
    fr.read(disk.use);
    fr.seek(rand.nextInt((int) fr.length()));
}
    
}

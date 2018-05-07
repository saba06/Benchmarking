
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/*opens an exting file big.txt for sequential access
/**
 *
 * @author sb
 */
class file_sequence {
    static FileInputStream fin;static FileOutputStream fout;
file_sequence() throws IOException{
        try{
        fin = new FileInputStream(disk.path);
        fout = new FileOutputStream(disk.path);
        } catch (FileNotFoundException ex) {
          System.out.println("In write_Sequential:"+ex);
        }
    }
public synchronized void  write() throws IOException{
    fout.write(disk.use);
    fout.flush();
}
public synchronized void read() throws IOException{
    fin.read(disk.use);
}
    
}

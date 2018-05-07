
import java.io.FileWriter;
import java.io.IOException;

/*
static class used to write results to a file flops.txt 
note: the file is closed at main class cpu_e after thread stops excutation
*/
/**
 *
 * @author sb
 */
  public class file{
    static final String COMMA_DELIMITER = ",";
    static final String NEW_LINE_SEPARATOR = "\n";
    static final String FILE_HEADER = "Time,value";
    static FileWriter csv=null;
    file(String file_name) throws IOException{
        try{
            csv=new FileWriter(file_name);
            csv.append(FILE_HEADER);
            csv.append(NEW_LINE_SEPARATOR);
        }catch(Exception e){
            System.out.println("In file:"+e);
        }
    }
}

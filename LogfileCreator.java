import java.io.*;
import java.util.*;
/**
 * A class for creating log files of random data.
 * 
 * @author Joseph Truelove
 * @version    2018.10.29
 */
public class LogfileCreator
{
    private Random rand;
    private ArrayList<LogEntry> years;
    /**
     * Create log files.
     */
    public LogfileCreator()
    {
        rand = new Random();
    }
    
    /**
     * Create a file of random log entries.
     * @param filename The file to write.
     * @param numEntries How many entries.
     * @return true if successful, false otherwise.
     */
    public boolean createFile(String filename, int numEntries)
    {
        boolean success = false;
        
        if(numEntries > 0) {
            try (FileWriter writer = new FileWriter(filename)) {
                LogEntry[] entries = new LogEntry[numEntries];
                for(int i = 0; i < numEntries; i++) {
                    entries[i] = createEntry();
                }
                Arrays.sort(entries);
                for(int i = 0; i < numEntries; i++) {
                    writer.write(entries[i].toString());
                    writer.write('\n');
                }
                
                success = true;
            }
            catch(IOException e) {
                System.err.println("There was a problem writing to " + filename);
            }
                
        }
        return success;
    }
    
    /**
     * Create a single (random) entry for a log file.
     * @return A log entry containing random data.
     */
    public LogEntry createEntry()
    {
            //starts the year at 2014 and will randomly add 0-4 years to it
            int year = 2014 + rand.nextInt(5);
      
            int month = 1 + rand.nextInt(12);
            // Avoid the complexities of days-per-month.
            int day = 1 + rand.nextInt(28);
            int hour = rand.nextInt(24);
            int minute = rand.nextInt(60);
            
            
            return new LogEntry(year, month, day, hour, minute);
        
    }

}

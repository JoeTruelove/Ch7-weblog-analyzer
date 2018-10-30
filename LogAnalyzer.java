
/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Joseph Truelove
 * @version    2018.10.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    private int[] monthCounts;
    private int[] dayCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly, daily and monthly
        // access counts.
        hourCounts = new int[24];
        monthCounts = new int[13];
        dayCounts = new int[29];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
    }

    /**
     * Return the number of accesses recorded in the log file
     */
    public int numberOfAccesses()
    {
        int total = 0;
        for(int i = 0; i < hourCounts.length; i++)
        {
            total = total + hourCounts[i];
        }
        return total;
    }
    
    /**
     * finds the hour with the most accesses
     */
    public int busiestHour()
    {
        int busiest = 0;
        int accesses = 0;
        int i = 0;
        while(i < hourCounts.length - 1)
        {
            if(accesses < hourCounts[i])
            {
                busiest = i;
                accesses = hourCounts[i];
                i++;
            }
            else
            {
                i++;
            }
        }
        return busiest;
    }
    
    /**
     * finds the hour with the least accesses
     */
    public int quietestHour()
    {
        int quietest = 0;
        int accesses = hourCounts[quietest];
        int i = 0;
        
        while(i < hourCounts.length - 1)
        {
            if(accesses > hourCounts[i])
            {
                quietest = i;
                accesses = hourCounts[i];
                i++;
            }
            else
            {
                i++;
            }
        }
        return quietest;
    }
    
    /**
     * finds the day with the highest amount of accesses
     */
    public int busiestDay()
    {
        int busiest = 0;
        int accesses = 0;
        int i = 0;
        while(i < dayCounts.length - 1)
        {
            if(accesses < dayCounts[i])
            {
                busiest = i;
                accesses = dayCounts[i];
                i++;
            }
            else
            {
                i++;
            }
        }
        return busiest;
    }
    
    /**
     * finds the day with the lowest amount of accesses
     */
    public int quietestDay()
    {
        int quietest = 1;
        int accesses = dayCounts[quietest];
        int i = 1;
        while(i < dayCounts.length - 1)
        {
            if(accesses > dayCounts[i])
            {
                quietest = i;
                accesses = dayCounts[i];
                i++;
            }
            else
            {
                i++;
            }
        }
        return quietest;
    }
    
    /**
     * finds the month with the most accesses
     */
    public int busiestMonth()
    {
        int busiest = 0;
        int accesses = 0;
        int i = 0;
        while(i < monthCounts.length - 1)
        {
            if(accesses < monthCounts[i])
            {
                busiest = i;
                accesses = monthCounts[i];
                i++;
            }
            else
            {
                i++;
            }
        }
        return busiest;
    }
    
    public int quietestMonth()
    {
        int quietest = 1;
        int accesses = monthCounts[quietest];
        int i = 1;
        while(i < monthCounts.length - 1)
        {
            if(accesses > monthCounts[i])
            {
                quietest = i;
                accesses = monthCounts[i];
                i++;
            }
            else
            {
                i++;
            }
        }
        return quietest;
    }
    
    /**
     * Adds all the accesses for all months
     * not sure if this is supposed to list each months amount of accesses
     * or do this
     */
    public int totalAccessesPerMonth()
    {
        int total = 0;
        for(int i = 0; i < monthCounts.length; i++)
        {
            total = total + monthCounts[i];
        }
        return total;
    }
    
    /**
     * adds the total accesses per month and divides them by how many months
     */
    public int averageAccessesPerMonth()
    {
        int total = 0;
        for(int i = 0; i < monthCounts.length; i++)
        {
            total = total + monthCounts[i];
        }
        int average = total / monthCounts.length;
        return average;
    }
    
    /**
     * finds a pair of hours with the most accesses
     */
    public int busiestTwoHour()
    {
        int busiest = 0;
        int accesses = 0;
        int i = 0;
        while(i < hourCounts.length - 1)
        {
            if(accesses < hourCounts[i] + hourCounts[i + 1] )
            {
                busiest = i;
                accesses = hourCounts[i] + hourCounts[i + 1];
                i++;
            }
            else
            {
                i++;
            }
        }
        return busiest;
    }
    
    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    
    /**
     * Analyze the daily access data from the log file
     */
    public void analyzeDailyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
    }
    
    /**
     * Analyze the monthly access data from the log file
     */
    public void analyzeMonthlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            monthCounts[month]++;
        }
    }
    
    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the daily counts.
     */
    public void printDailyCounts()
    {
        System.out.println("Day: Count");
        for(int day = 1; day < dayCounts.length; day++) {
            System.out.println(day + ": " + dayCounts[day]);
        }
    }
    
    /**
     * Print the Monthly counts.
     */
    public void printMonthlyCounts()
    {
        System.out.println("Month: Count");
        for(int month = 1; month < monthCounts.length; month++) {
            System.out.println(month + ": " + monthCounts[month]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}

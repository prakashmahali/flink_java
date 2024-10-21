import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class EpochConverter {

    public static void main(String[] args) {
        // Example epoch time in seconds
        long epochTime = 1682508800L;  // Replace this with your epoch time

        // Convert epoch time to LocalDateTime
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(epochTime), ZoneId.systemDefault());

        // Define the desired date format: yyyyMMdd HH:mm:ss
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");

        // Format the date and time
        String formattedDate = dateTime.format(formatter);

        // Output the formatted date
        System.out.println("Formatted Date: " + formattedDate);
    }
}

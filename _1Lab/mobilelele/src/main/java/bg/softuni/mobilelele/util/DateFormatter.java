package bg.softuni.mobilelele.util;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
    public String formatDate(String isoDate) {
        Instant dateTime = Instant.parse(isoDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return dateTime.toString();
    }
}

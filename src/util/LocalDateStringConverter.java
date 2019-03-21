package util;

import java.time.Instant;
import javafx.util.StringConverter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateStringConverter extends StringConverter<LocalDate> {

    private String pattern = "MM/dd/yyyy";
    private DateTimeFormatter dtFormatter;

    public LocalDateStringConverter() {
        dtFormatter = DateTimeFormatter.ofPattern(pattern);
    }

    public LocalDateStringConverter(String pattern) {
        this.pattern = pattern;
        dtFormatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public LocalDate fromString(String text) {
        LocalDate date = null;
        if (text != null && !text.trim().isEmpty()) {
            date = LocalDate.parse(text, dtFormatter);
        }
        return date;
    }

    @Override
    public String toString(LocalDate date) {
        String text = null;
        if (date != null) {
            text = dtFormatter.format(date);
        }
        return text;
    }

    public Date toDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        } else {
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.of("GMT")));
            return Date.from(instant);
        }
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}

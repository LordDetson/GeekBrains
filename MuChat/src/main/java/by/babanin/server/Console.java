package by.babanin.server;

import java.io.BufferedInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

public class Console {
    private Scanner scanner;
    private Formatter formatter;
    private String fmtString;
    private Locale locale;
    private DateTimeFormatter dateTimeFormatter;

    public Console(String clientName) {
        scanner = new Scanner(new BufferedInputStream(System.in));
        formatter = new Formatter();
        fmtString = clientName + ": %s %s";
        locale = new Locale("ru", "RU");
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd.LL.yy HH:mm:ss", locale);
    }

    public String read() {
        return scanner.nextLine();
    }

    public void write(String str) {
        LocalDateTime dateTime = LocalDateTime.now();
        formatter.format(fmtString, str, dateTime.format(dateTimeFormatter));
        System.out.println(formatter);
    }
}

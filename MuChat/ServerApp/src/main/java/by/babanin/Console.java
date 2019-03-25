package by.babanin;

import by.babanin.entity.Message;

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

    public Console() {
        scanner = new Scanner(new BufferedInputStream(System.in));
        formatter = new Formatter();
        fmtString = "%s%s: %s %s\n\n\r";
        locale = new Locale("ru", "RU");
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd.LL.yy HH:mm:ss", locale);
    }

    public String read() {
        return scanner.nextLine();
    }

    public void write(Message message) {
        LocalDateTime dateTime = LocalDateTime.now();
        formatter.format(fmtString, Message.isAll(message) ? "" : "[/w] ", message.getFrom(),
                message.getMessage(),
                dateTime.format(dateTimeFormatter));
        System.out.println(formatter);
    }
}

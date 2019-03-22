package by.babanin.entity;

import by.babanin.dao.DBHandler;

import java.io.Serializable;
import java.util.StringJoiner;

public class Message implements Serializable {

    private static final Long serialVersionUID = 1L;

    private static DBHandler dbHandler = DBHandler.getInstance();
    private String from;
    private String to;
    private String message;

    public static Message parse(String mes, User from) {
        Message message = new Message();
        String[] strings = mes.split(" ");
        if (!strings[0].equalsIgnoreCase("/w")) {
            message.setFrom(from.getLogin());
            message.setTo("All");
            message.setMessage(mes);
            return message;
        }
        User to;
        if ((to = dbHandler.getUser(strings[1])) != null) {
            message.setFrom(from.getLogin());
            message.setTo(to.getLogin());
            StringJoiner joiner = new StringJoiner(" ");
            for (int i = 2; i < strings.length; i++) joiner.add(strings[i]);
            message.setMessage(joiner.toString());
            return message;
        }
        return null;
    }

    public static boolean isAll(Message message) {
        return message.getTo().equals("All");
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Message.class.getSimpleName() + "[", "]")
                .add("from='" + from + "'")
                .add("to='" + to + "'")
                .add("message='" + message + "'")
                .toString();
    }
}

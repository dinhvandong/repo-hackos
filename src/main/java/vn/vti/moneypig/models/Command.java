package vn.vti.moneypig.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "command")
public class Command {
    @Id
    private Long id;

    @Transient
    public static final String SEQUENCE_NAME = "command_sequence";

    private int value;

    private Long time;

    private String timeUTC;

    public String getTimeUTC() {
        return timeUTC;
    }

    public void setTimeUTC(String timeUTC) {
        this.timeUTC = timeUTC;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}

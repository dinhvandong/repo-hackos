package vn.vti.moneypig.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "command_coffee_arabica")
public class CommandCoffeeArabica {

    @Id
    private Long id;
    @Transient
    public static final String SEQUENCE_NAME = "command_coffee_arabica_sequence";
    private int value; // 1 or -1  Up down
    private  int type;
    private Long time;
    private Long userId;
    private String username;
    private Long timeYYYYMMDDHHMM;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getTimeYYYYMMDDHHMM() {
        return timeYYYYMMDDHHMM;
    }

    public void setTimeYYYYMMDDHHMM(Long timeYYYYMMDDHHMM) {
        this.timeYYYYMMDDHHMM = timeYYYYMMDDHHMM;
    }
}

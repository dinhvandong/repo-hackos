package vn.vti.moneypig.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "command_coffeevn")
public class CommandCoffeeVn {

    @Id
    private Long id;
    @Transient
    public static final String SEQUENCE_NAME = "command_coffee_vn_sequence";
    private int value;
    private Long time;
    private String username;
    private Long timeYYYYMMDDHHmm;

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


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getTimeYYYYMMDDHHmm() {
        return timeYYYYMMDDHHmm;
    }

    public void setTimeYYYYMMDDHHmm(Long timeYYYYMMDDHHmm) {
        this.timeYYYYMMDDHHmm = timeYYYYMMDDHHmm;
    }
}

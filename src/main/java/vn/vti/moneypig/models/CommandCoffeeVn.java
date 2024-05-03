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
    private int value; //-1 1

    private double money;
    private Long time;

    private String timeUTC;

    public String getTimeUTC() {
        return timeUTC;
    }

    public void setTimeUTC(String timeUTC) {
        this.timeUTC = timeUTC;
    }

    private String username;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
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


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   }

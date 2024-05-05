package vn.vti.moneypig.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "command_coffee_robusta")
public class CommandCoffeeRobusta {

    @Id
    private Long id;
    @Transient
    public static final String SEQUENCE_NAME = "command_coffee_robusta_sequence";
    private int value; //-1 1
    private double money;
    private Long time;
    private boolean status = true;

    private int result=0;

    public boolean isStatus() {
        return status;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
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

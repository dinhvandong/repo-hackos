package vn.vti.moneypig.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account")
public class Account {
    @Id
    private Long id;

    @Transient
    public static final String SEQUENCE_NAME = "account_sequence";
    private String phrase;
    private String google;
    private String facebook;

    private String okx;
    private String binance;
    private Long createdDate;
    private int status;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getBinance() {
        return binance;
    }

    public void setBinance(String binance) {
        this.binance = binance;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public int getStatus() {
        return status;
    }

    public String getOkx() {
        return okx;
    }

    public void setOkx(String okx) {
        this.okx = okx;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

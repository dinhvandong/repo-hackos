package vn.vti.moneypig.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "device")
public class Device {
    @Id
    private Long id;
    @Transient
    public static final String SEQUENCE_NAME = "device_sequence";
    private String computerName;
    private String ip;
    private String uuid;
    private String country;
    private String ram;
    private String cpu;
    private String windowVersion;
    private String facebook;
    private String google;
    private String tiktok;
    private Long createdDate;
    private Long updatedDate;

    public Device() {
    }

    public Device(Long id, String computerName, String ip, String uuid, String country, String ram, String cpu, String windowVersion, String facebook, String google, String tiktok, Long createdDate, Long updatedDate) {
        this.id = id;
        this.computerName = computerName;
        this.ip = ip;
        this.uuid = uuid;
        this.country = country;
        this.ram = ram;
        this.cpu = cpu;
        this.windowVersion = windowVersion;
        this.facebook = facebook;
        this.google = google;
        this.tiktok = tiktok;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getWindowVersion() {
        return windowVersion;
    }

    public void setWindowVersion(String windowVersion) {
        this.windowVersion = windowVersion;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public String getTiktok() {
        return tiktok;
    }

    public void setTiktok(String tiktok) {
        this.tiktok = tiktok;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }
}

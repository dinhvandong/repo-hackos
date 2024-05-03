package vn.vti.moneypig.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "users")
public class User {
    @Id
    private Long id;
    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";
    private String username;
    private String email;

    private String role;
    private String password;
    private String googleId;
    private String phone;
    private int status;
    private  long dateOfBirth;
    private  long createdDate;
    private  int gender;
    private String address;
    private String avatar;
    private Address workAddress;
    private  String referCode;

    private double gold=0;
    public User(Long id, String username, String email, String password, String googleId, String phone, int status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.googleId = googleId;
        this.phone = phone;
        this.status = status;
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public User(String username, String password) {
        this.username = username;
        this.password  = password;
    }

    public User(Long id, String username, String email,
                String role, String password, String googleId,
                String phone, int status, long dateOfBirth,
                long createdDate, int gender, String address,
                String avatar, Address workAddress, String referCode) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
        this.googleId = googleId;
        this.phone = phone;
        this.status = status;
        this.dateOfBirth = dateOfBirth;
        this.createdDate = createdDate;
        this.gender = gender;
        this.address = address;
        this.avatar = avatar;
        this.workAddress = workAddress;
        this.referCode = referCode;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(){

    }

    public String getReferCode() {
        return referCode;
    }

    public void setReferCode(String referCode) {
        this.referCode = referCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", googleId='" + googleId + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
    public Address getWorkAddress() {
        return workAddress;
    }
    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }
    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
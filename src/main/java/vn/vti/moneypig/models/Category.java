package vn.vti.moneypig.models;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
//@Document(collection = "categories")
public class Category {
    private Long id;
    @Transient
    public static final String SEQUENCE_NAME = "category_sequence";
    private String name;
    private String icon;

    private Long groupID;
    private String groupName;
    private boolean active;
    private Long createdDate;

    public String desc;

    public String getDesc() {
        return desc;
    }

    public Long getGroupID() {
        return groupID;
    }

    public void setGroupID(Long groupID) {
        this.groupID = groupID;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private Long userID;
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }
}

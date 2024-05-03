package vn.vti.moneypig.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "chat_euro")
public class ChatTopicEuro {
    @Id
    private  Long id;


    @Transient
    public static final String SEQUENCE_NAME = "chat_topic_euro_sequence";


    private Long senderID;

    private Long createdDate;

    private String createDateUTCString;

    private String email;

    private String sender;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getCreateDateUTCString() {
        return createDateUTCString;
    }

    public void setCreateDateUTCString(String createDateUTCString) {
        this.createDateUTCString = createDateUTCString;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderID() {
        return senderID;
    }

    public void setSenderID(Long senderID) {
        this.senderID = senderID;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.josephaines.anonrtc.message;

import javax.persistence.*;

@Entity
@Table
public class Message {
    @Id
    @SequenceGenerator(
            name = "message_sequence",
            sequenceName = "message_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_sequence"
    )
    private Long id;
    private Long sessionId;
    private String content;

    public Message(Long id, Long sessionId, String content) {
        this.id = id;
        this.sessionId = sessionId;
        this.content = content;
    }

    public Message(Long sessionId, String content) {
        this.sessionId = sessionId;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sessionId=" + sessionId +
                ", content='" + content + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Message() {
    }
}

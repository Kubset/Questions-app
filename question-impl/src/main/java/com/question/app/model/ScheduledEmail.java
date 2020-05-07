package com.question.app.model;

import com.question.app.model.enums.SchedulerStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="scheduledEmail")
public class ScheduledEmail {

    @Id
    @Column(name="scheduled_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String cron;

    private Long fireTime;

    private String recipient;

    private String topic;

    @Enumerated(EnumType.STRING)
    private SchedulerStatus status;

    private int questionsNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "scheduled_email_category",
            joinColumns = @JoinColumn(name = "scheduled_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> choosenCategories;


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Long getFireTime() {
        return fireTime;
    }

    public void setFireTime(Long fireTime) {
        this.fireTime = fireTime;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public SchedulerStatus getStatus() {
        return status;
    }

    public void setStatus(SchedulerStatus status) {
        this.status = status;
    }

    public int getQuestionsNumber() {
        return questionsNumber;
    }

    public void setQuestionsNumber(int questionsNumber) {
        this.questionsNumber = questionsNumber;
    }

    public List<Category> getChoosenCategories() {
        return choosenCategories;
    }

    public void setChoosenCategories(List<Category> choosenCategories) {
        this.choosenCategories = choosenCategories;
    }
}

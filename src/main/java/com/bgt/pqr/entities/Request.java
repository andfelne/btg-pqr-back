package com.bgt.pqr.entities;

import com.mysema.query.annotations.QueryEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;

@QueryEntity
@Document("requests")
public class Request {

    @Transient
    public static final String SEQUENCE_NAME = "requests_sequence";

    @Id
    private String id;
    private Long sequence;
    private String requestType;
    private String description;
    private String response;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date filingDate;
    private Request requestParent;
    private Boolean haveClaim;


    public Request() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Date getFilingDate() {
        if (null == filingDate) {
            filingDate = Calendar.getInstance().getTime();
        }
        return filingDate;
    }

    public void setFilingDate(Date filingDate) {
        this.filingDate = filingDate;
    }

    public Request getRequestParent() {
        return requestParent;
    }

    public void setRequestParent(Request requestParent) {
        this.requestParent = requestParent;
    }

    public Boolean getHaveClaim() {
        return haveClaim;
    }

    public void setHaveClaim(Boolean haveClaim) {
        this.haveClaim = haveClaim;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id='" + id + '\'' +
                ", sequence=" + sequence +
                ", requestType='" + requestType + '\'' +
                ", description='" + description + '\'' +
                ", response='" + response + '\'' +
                ", filingDate=" + filingDate +
                ", requestParent=" + requestParent +
                ", haveClaim=" + haveClaim +
                '}';
    }
}

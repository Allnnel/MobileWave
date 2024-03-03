package com.example.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "systemToken", schema = "server")
public class SystemToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "systemToken", nullable = false)
    private String systemToken;
    @Column(name = "startDate", nullable = false)
    private Date startDate;
    @Column(name = "endDate", nullable = false)
    private Date endDate;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "system", nullable = false)
    private String system;

    public SystemToken() {
    }

    public SystemToken(String systemToken, Date startDate, Date endDate, String description, String system) {
        this.systemToken = systemToken;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.system = system;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemToken() {
        return systemToken;
    }

    public void setSystemToken(String systemToken) {
        this.systemToken = systemToken;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }
}

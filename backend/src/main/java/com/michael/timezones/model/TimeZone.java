package com.michael.timezones.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class TimeZone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty
    private String city;
    @NotEmpty
    private String name;
    @NotNull
    private int zoneOffset;

    @ManyToOne
    @JoinColumn("user_id")
    private User owner;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getCity() {
        return city;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getZoneOffset() {
        return zoneOffset;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setZoneOffset(int zoneOffset) {
        this.zoneOffset = zoneOffset;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

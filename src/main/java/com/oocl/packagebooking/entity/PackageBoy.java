package com.oocl.packagebooking.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PACKAGE_BOY")
public class PackageBoy {

    @Id
    private String id;

    private String name;

    private String phoneNumber;

    private String status;

    private String appointmentTime;

    private Double weight;

    public PackageBoy() {
    }

    public PackageBoy(String id, String name, String phoneNumber, String status, String appointmentTime, Double weight) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.appointmentTime = appointmentTime;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}

package com.github.cvazer.tryout.playgendary.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WorkPeriod {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private @JsonFormat(pattern = "HH:mm") LocalTime start;
    private @JsonFormat(pattern = "HH:mm") LocalTime end;
    private String name;

    public WorkPeriod() {}

    public WorkPeriod(LocalTime start, LocalTime end, String name) {
        this.start = start;
        this.end = end;
        this.name = name;
    }

    public void set(WorkPeriod period){
        if (period.start!=null){start = period.start;}
        if (period.end!=null){end = period.end;}
        if (period.name!=null){name = period.name;}
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

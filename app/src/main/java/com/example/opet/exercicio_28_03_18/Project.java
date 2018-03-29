package com.example.opet.exercicio_28_03_18;

import java.util.Date;

/**
 * Created by opet on 28/03/2018.
 */

public class Project {
    private String name;

    private Date start;
    private Date end;

    private Boolean ended;

    private Project(){};

    public Project(String name, Date start, Date end, Boolean ended) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.ended = ended;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Boolean getEnded() {
        return ended;
    }

    public void setEnded(Boolean ended) {
        this.ended = ended;
    }
}

package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @NotBlank
    @Size(max = 50)
    private String location;

    @OneToMany//(mappedBy = "Job") ***Not sure, need to check
    @JoinColumn
    private List<Job> jobs = new ArrayList<>();

    @NotBlank
    @Size (max = 50)
    public Employer(String location){
        this.location = location;
    }

    //added a no-arg constructor to instantiate an object
    public Employer(){}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

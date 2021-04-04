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

    //added field to store jobs
    @OneToMany(mappedBy = "employer")
    //@JoinColumn - added per instructions, but get error
    private final List<Job> jobs = new ArrayList<>();

    public Employer(@NotBlank @Size(max = 50) String location){
        this.location = location;
    }

    /*
    added a no-arg constructor to instantiate an object
    always needed within an Entity class
     */
    public Employer(){}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

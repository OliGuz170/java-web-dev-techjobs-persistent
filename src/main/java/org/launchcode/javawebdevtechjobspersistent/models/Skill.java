package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

    @Size(min = 1, max = 500)
    private String description;

    /* Added jobs field of List type */
    @ManyToMany(mappedBy="skills")
    private List<Job> jobs;
/*
Removed line 23 & added line 17 on 4/4
Removed line 25-27
 */

    //private List<Job> jobs = new ArrayList<>();

//    public Skill(@Size(max = 500) String description){
//        this.description = description;
//    }

    public Skill () {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
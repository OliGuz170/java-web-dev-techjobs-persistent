package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends AbstractEntity {

    /*removed the following:
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String employer;

    Changed employer type to Employer
    */

    @ManyToOne
    @NotNull
    private Employer employer;

    /*
    Updated Many-Many relationship w/ skill
    Changed skills type
     */
    @ManyToMany
    private List<Skill> skills = new ArrayList<>();

    public Job() {}

    /*
    Changed employer type to Employer (line 29 & getter/setter)
    Changed skills type & getter/setter
     */
    public Job(Employer anEmployer, List<Skill> someSkills, Employer employer) {
        super();
        this.employer = anEmployer;
        this.skills = someSkills;
    }

    // Getters and setters.

   public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}

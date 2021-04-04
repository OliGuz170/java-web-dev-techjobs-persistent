package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@MappedSuperclass //parent class
public abstract class AbstractEntity {

    //Since subclasses of AbstractEntity will be entities
    //added @Id & @GeneratedValue annotations to field id
    @Id
    @GeneratedValue
    private int id;

    @NotBlank //added to that use cannot leave field blank
    @Size(max = 100) //employer names might be > 50 char
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    /*
    Replaced 'that' w/ entity (18.2.1)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity entity = (AbstractEntity) o;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
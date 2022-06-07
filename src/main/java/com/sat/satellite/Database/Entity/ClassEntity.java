package com.sat.satellite.Database.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "demo_1_class", schema = "satellit_university")
public class ClassEntity {
    private Integer id;
    private String name;
    private String major;
    private Integer datecreated;

    public ClassEntity(){

    }

    public ClassEntity(Integer id, String name, String major, Integer datecreated) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.datecreated = datecreated;
    }

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "major")
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Basic
    @Column(name = "datecreated")
    public Integer getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Integer datecreated) {
        this.datecreated = datecreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassEntity that = (ClassEntity) o;
        return id.equals(that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(major, that.major) &&
                Objects.equals(datecreated, that.datecreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, major, datecreated);
    }
}

package com.haulmont.sample.petclinic.entity.vet;

import com.haulmont.cuba.security.entity.User;
import com.haulmont.sample.petclinic.entity.Person;

import javax.persistence.*;
import java.util.Set;

@Table(name = "PETCLINIC_VET")
@Entity(name = "petclinic_Vet")
public class Vet extends Person {
    private static final long serialVersionUID = 8571203926820669424L;

    @JoinTable(name = "PETCLINIC_VET_SPECIALTY_LINK",
            joinColumns = @JoinColumn(name = "VET_ID"),
            inverseJoinColumns = @JoinColumn(name = "SPECIALTY_ID"))
    @ManyToMany
    protected Set<Specialty> specialties;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    protected User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    public Set<Specialty> getSpecialties() {
        return specialties;
    }
}
package com.haulmont.sample.petclinic.service;

import com.haulmont.sample.petclinic.entity.visit.Visit;

public interface VisitService {
    String NAME = "petclinic_VisitService";

    void notifyTreatingVetAboutNewVisit(Visit visit);
}
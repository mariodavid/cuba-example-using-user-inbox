package com.haulmont.sample.petclinic.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.DatatypeFormatter;
import com.haulmont.sample.petclinic.entity.vet.Vet;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import de.diedavids.cuba.userinbox.service.MessageService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(VisitService.NAME)
public class VisitServiceBean implements VisitService {


    @Inject
    protected MessageService messageService;
    @Inject
    protected DataManager dataManager;

    @Inject
    protected DatatypeFormatter datatypeFormatter;

    @Override
    public void notifyTreatingVetAboutNewVisit(Visit visit) {

        Vet treatingVet = dataManager.reload(visit.getTreatingVet(), "vet-with-specialties-and-user");

        if (treatingVet.getUser() != null) {

            String formattedVisitDate = datatypeFormatter.formatDate(visit.getVisitDate());
            messageService.sendSystemMessage(
                    treatingVet.getUser(),
                    "Visit created",
                    "Visit for " + visit.getPet().getName() + " created for Date: " + formattedVisitDate,
                    visit
                    );
        }
    }
}
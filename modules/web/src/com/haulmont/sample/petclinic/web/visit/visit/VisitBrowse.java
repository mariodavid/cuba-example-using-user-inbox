package com.haulmont.sample.petclinic.web.visit.visit;

import com.haulmont.cuba.gui.components.ButtonsPanel;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import de.diedavids.cuba.userinbox.web.WithEntitySharingSupport;

import javax.inject.Inject;

@UiController("petclinic_Visit.browse")
@UiDescriptor("visit-browse.xml")
@LookupComponent("visitsTable")
@LoadDataBeforeShow
public class VisitBrowse extends StandardLookup<Visit> implements WithEntitySharingSupport {

    @Inject
    protected GroupTable<Visit> visitsTable;
    @Inject
    protected ButtonsPanel buttonsPanel;

    @Override
    public Table getListComponent() {
        return visitsTable;
    }

    @Override
    public ButtonsPanel getButtonsPanel() {
        return buttonsPanel;
    }
}
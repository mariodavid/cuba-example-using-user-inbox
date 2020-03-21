package com.haulmont.sample.petclinic.web.visit.visit;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.actions.list.CreateAction;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.gui.screen.LookupComponent;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import com.haulmont.sample.petclinic.service.VisitService;
import de.diedavids.cuba.userinbox.web.WithEntitySharingSupport;

import javax.inject.Inject;
import javax.inject.Named;

@UiController("petclinic_Visit.browse")
@UiDescriptor("visit-browse.xml")
@LookupComponent("visitsTable")
@LoadDataBeforeShow
public class VisitBrowse extends StandardLookup<Visit> implements WithEntitySharingSupport {

    @Inject
    protected GroupTable<Visit> visitsTable;
    @Inject
    protected ButtonsPanel buttonsPanel;
    @Inject
    protected ScreenBuilders screenBuilders;
    @Inject
    protected Notifications notifications;
    @Inject
    protected Metadata metadata;
    @Inject
    protected DataManager dataManager;
    @Inject
    protected VisitService visitService;
    @Inject
    protected Dialogs dialogs;
    @Inject
    protected MessageBundle messageBundle;

    @Override
    public Table getListComponent() {
        return visitsTable;
    }

    @Override
    public ButtonsPanel getButtonsPanel() {
        return buttonsPanel;
    }

    @Subscribe("visitsTable.create")
    protected void onVisitsTableCreate(Action.ActionPerformedEvent event) {

        Visit newVisit = metadata.create(Visit.class);



        Screen visitCreateScreen = screenBuilders
                .editor(visitsTable)
                .newEntity(newVisit)
                .withLaunchMode(OpenMode.DIALOG)
                .build();

        visitCreateScreen.addAfterCloseListener(afterCloseEvent -> {
            StandardCloseAction closeAction = (StandardCloseAction) afterCloseEvent.getCloseAction();
            if (closeAction.equals(StandardEditor.WINDOW_COMMIT_AND_CLOSE_ACTION)) {
                Visit newlyCreatedVisit = dataManager.reload(newVisit, "visit-with-pet-and-vet");


                dialogs.createOptionDialog()
                        .withType(Dialogs.MessageType.CONFIRMATION)
                        .withCaption(messageBundle.getMessage("notifyVetCaption"))
                        .withMessage(messageBundle.formatMessage("notifyVetMessage", newlyCreatedVisit.getTreatingVet().getName()))
                        .withActions(
                                new DialogAction(DialogAction.Type.YES).withHandler(e -> {
                                    visitService.notifyTreatingVetAboutNewVisit(newlyCreatedVisit);

                                    notifications
                                            .create(Notifications.NotificationType.TRAY)
                                            .withCaption(messageBundle.getMessage("notificationSend"))
                                            .show();
                                }),
                                new DialogAction(DialogAction.Type.CANCEL).withHandler(e -> {
                                    // CANCEL option selected
                                })
                        )
                        .show();

            }
        });

        visitCreateScreen.show();
    }



}
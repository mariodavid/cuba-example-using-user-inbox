package com.haulmont.sample.petclinic.web.screens.main;

import com.haulmont.cuba.gui.components.mainwindow.SideMenu;
import com.haulmont.cuba.gui.screen.Subscribe;
import de.diedavids.cuba.userinbox.web.screens.SideMainwindowWithMessages;

import javax.inject.Inject;
import java.util.Map;

public class ExtAppMainWindow extends SideMainwindowWithMessages {


    @Inject
    private SideMenu sideMenu;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        sideMenu.getMenuItemNN("messages")
                .setStyleName("messages-petclinic-style");


    }



}
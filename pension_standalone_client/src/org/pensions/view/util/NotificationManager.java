package org.pensions.view.util;

import org.controlsfx.control.Notifications;

/**
 * Created by Dinesh Liyanage on 5/24/2016.
 */
public class NotificationManager {

    public static void info(String title, String message) {
        Notifications.create()
                .title(title)
                .text(message)
                .showInformation();
    }

    public static void error(String title, String message) {
        Notifications.create()
                .title(title)
                .text(message)
                .showError();
    }
}

package com.neonatal.app.src;

import android.app.Application;

/**
 * Created by Maurice on 1/5/2018.
 */

public class NeonatalApp extends Application {

    private int currentUser;

    public int getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(int userId) {
        currentUser = userId;
    }
}

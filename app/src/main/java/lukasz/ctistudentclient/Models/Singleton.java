package lukasz.ctistudentclient.Models;

import android.app.Notification;

/**
 * Created by tukan on 16.04.2017.
 */

public class Singleton {
    private static Singleton mInstance = null;

    private UserModel userProfile;


    private NotificationModel userNotification;

    private Singleton(){
        userProfile = new UserModel();
        userNotification = new NotificationModel();
    }

    public static Singleton getInstance(){
        if(mInstance == null)
        {
            mInstance = new Singleton();
        }
        return mInstance;
    }

    public UserModel getUserProfile(){
        return this.userProfile;
    }

    public void setUserProfile(UserModel value){
        userProfile = value;
    }
    public NotificationModel getUserNotification() {
        return this.userNotification;
    }

    public void setUserNotification(NotificationModel userNotification) {
        this.userNotification = userNotification;
    }
}
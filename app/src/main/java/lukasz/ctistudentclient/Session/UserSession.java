package lukasz.ctistudentclient.Session;

import java.util.ArrayList;
import java.util.List;

import lukasz.ctistudentclient.Models.NotificationModel;
import lukasz.ctistudentclient.Models.UserModel;

/**
 * Created by tukan on 16.04.2017.
 */

public class UserSession {
    private static UserSession mInstance = null;

    private UserModel userProfile;
    private NotificationModel userNotification;
    private List<NotificationModel> userNotificationList;

    private UserSession(){
        userProfile = new UserModel();
        userNotification = new NotificationModel();
        userNotificationList = new ArrayList<NotificationModel>();
    }

    public static UserSession getInstance(){
        if(mInstance == null)
        {
            mInstance = new UserSession();
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

    public void clearUserNotification() {
        this.userNotification = new NotificationModel();
    }

    public List<NotificationModel> getUserNotificationList() {
        return userNotificationList;
    }

    public void setUserNotificationList(List<NotificationModel> userNotificationList) {
        this.userNotificationList = userNotificationList;
    }

}
package lukasz.ctistudentclient.Models;

/**
 * Created by tukan on 16.04.2017.
 */

public class Singleton {
    private static Singleton mInstance = null;

    private UserModel userProfile;

    private Singleton(){
        userProfile = new UserModel();
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
}
package lukasz.ctistudentclient.Services;

import lukasz.ctistudentclient.Models.NotificationModel;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by tukan on 08.01.2017.
 */

public interface NotificationService {

    @GET("/notification/") // deklarujemy endpoint oraz metodę
    void getNotification(Callback<NotificationModel> pResponse);

    @POST("/notification/") // deklarujemy endpoint, metodę oraz dane do wysłania
    void postNotification(@Body NotificationModel pBody, Callback<NotificationModel> pResponse);
}

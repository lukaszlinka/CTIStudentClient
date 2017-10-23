package lukasz.ctistudentclient.Services;

import lukasz.ctistudentclient.Models.UserModel;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by tukan on 08.01.2017.
 */

public interface UserService {

    @GET("/users/user/") // deklarujemy endpoint oraz metodę
    void getUser(Callback<UserModel> pResponse);

    @POST("/users/user/") // deklarujemy endpoint, metodę oraz dane do wysłania
    void postUser(@Body UserModel pBody, Callback<UserModel> pResponse);

    @POST("/users/login/")
    void postUserLogin(@Body String login, String password, Callback<UserModel> pResponse);

}

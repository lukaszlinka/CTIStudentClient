package lukasz.ctistudentclient.Services;

import lukasz.ctistudentclient.Models.UserModel;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by tukan on 08.01.2017.
 */

public interface UserService {

    String ENDPOINT = "https://api.github.com";

    @GET("/users/{user}")
    Call<UserModel> getUser(@Path("user") String user);

//    @GET("users/{user}/repos")
//    Call<List<UserModel>> getRepos(@Path("user") String user);



//    @FormUrlEncoded
//    @POST("/add")
//    void resgisterUser(@Body UserModel user, Callback<UserModel> cb);
//
//    @GET("/edit&id={id}")
//    Call<UserModel> getUser(@Query("id") String id);

}

package lukasz.ctistudentclient.Services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by tukan on 23.10.2017.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    public static final String TAG = "FCM";
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }
}

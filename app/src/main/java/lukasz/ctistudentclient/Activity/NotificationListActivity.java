package lukasz.ctistudentclient.Activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import lukasz.ctistudentclient.Adapters.NotificationDataTemplate;
import lukasz.ctistudentclient.Models.NotificationModel;
import lukasz.ctistudentclient.Models.Singleton;
import lukasz.ctistudentclient.R;

public class NotificationListActivity extends AppCompatActivity {
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);

        list = (ListView) findViewById(R.id.listView1);

        NotificationModel obj1 = new NotificationModel();
        obj1.setClassroom("123");
        obj1.setPriority(2);
        obj1.setScanCode("12321231");
        obj1.setPlaceDescription("Niedaleko bufetu przy dziekanacie");
        obj1.setDescription("Pęknięta rura i powolny wyciek wody");
        obj1.setLevel("3");
        if (Singleton.getInstance().getUserNotification().getImage() != null)
            obj1.setImage(Singleton.getInstance().getUserNotification().getImage());

//        NotificationModel obj2 = new NotificationModel();
//        obj2.setClassroom("123");
//        obj2.setPriority(2);
//        obj2.setScanCode("12321231");
//        obj2.setPlaceDescription("Niedaleko bufetu przy dziekanacie");
//        obj2.setDescription("Pęknięta rura i powolny wyciek wody");
//        obj2.setLevel("3");
//        obj2.setImage(obj1.getImage());
//
//        NotificationModel obj3 = new NotificationModel();
//        obj3.setClassroom("123");
//        obj3.setPriority(2);
//        obj3.setScanCode("12321231");
//        obj3.setPlaceDescription("Niedaleko bufetu przy dziekanacie");
//        obj3.setDescription("Pęknięta rura i powolny wyciek wody");
//        obj3.setLevel("3");
//        obj3.setImage(obj1.getImage());

        NotificationModel notificationList[] = {obj1, obj1, obj1, obj1};

        NotificationDataTemplate template = new NotificationDataTemplate(getApplicationContext(), notificationList);

        list.setAdapter(template);
    }
}

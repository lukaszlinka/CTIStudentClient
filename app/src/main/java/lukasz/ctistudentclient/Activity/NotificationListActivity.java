package lukasz.ctistudentclient.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import lukasz.ctistudentclient.Adapters.NotificationDataTemplate;
import lukasz.ctistudentclient.Models.NotificationModel;
import lukasz.ctistudentclient.Session.UserSession;
import lukasz.ctistudentclient.R;

public class NotificationListActivity extends AppCompatActivity {
    private ListView listView;
    private TextView textView;
    private NotificationModel notificationList[];
    private List<NotificationModel> userList;

    @Override
    protected void onResume() {
        if (notificationList != null && notificationList.length > 0) {
            textView.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
        }
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);

        listView = (ListView) findViewById(R.id.listView1);
        textView = (TextView) findViewById(R.id.textView);

        userList = UserSession.getInstance().getUserNotificationList();

        notificationList = new NotificationModel[userList.size()];
        userList.toArray(notificationList);

        if (notificationList != null && notificationList.length > 0) {

            textView.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
            NotificationDataTemplate template = new NotificationDataTemplate(getApplicationContext(), notificationList);

            listView.setAdapter(template);
        } else {
            listView.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
        }
    }
}

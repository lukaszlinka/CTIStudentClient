package lukasz.ctistudentclient.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import lukasz.ctistudentclient.Models.NotificationModel;
import lukasz.ctistudentclient.Session.UserSession;
import lukasz.ctistudentclient.R;


public class SummaryFragment extends Fragment {

    private ImageView image;
    private TextView description;
    private TextView rooms;
    private TextView placeDescription;
    private TextView level;
    private TextView scanCode;
    private TextView priority;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        SetData();
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        image = (ImageView) view.findViewById(R.id.summary_data_template_image);
        description = (TextView) view.findViewById(R.id.summary_data_template_description);
        rooms = (TextView) view.findViewById(R.id.summary_data_template_place_room);
        placeDescription = (TextView) view.findViewById(R.id.summary_data_template_place_description);
        level = (TextView) view.findViewById(R.id.summary_data_template_place_level);
        scanCode = (TextView) view.findViewById(R.id.summary_data_template_scan_code);
        priority = (TextView) view.findViewById(R.id.summary_data_template_priority);

        return view;
    }

    private void SetData(){
        NotificationModel notification = UserSession.getInstance().getUserNotification();
        if(notification!=null){
            if (notification.getImage() != null)
                image.setImageBitmap(notification.getImage());
            else
                image.setImageResource(R.drawable.ic_notification_list_photo);

            if (!notification.getDescription().matches(""))
                description.setText(notification.getDescription());
            else
                description.setText("Brak");

            if (!notification.getClassroom().matches(""))
                rooms.setText(notification.getClassroom());
            else
                rooms.setText("Brak");

            if (!notification.getPlaceDescription().matches(""))
                placeDescription.setText(notification.getPlaceDescription());
            else
                placeDescription.setText("Brak");

            if (!notification.getLevel().matches(""))
                level.setText(notification.getLevel());
            else
                level.setText("Brak");

            if (!notification.getScanCode().matches(""))
                scanCode.setText(notification.getScanCode());
            else
                scanCode.setText("Brak");

            priority.setText(notification.getPriority() + "");
        }
    }
}

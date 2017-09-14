package lukasz.ctistudentclient.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import lukasz.ctistudentclient.Models.NotificationModel;
import lukasz.ctistudentclient.Models.Singleton;
import lukasz.ctistudentclient.R;

public class SendPlaceFragment extends Fragment {

    EditText levelEditText, roomEditText, descriptionEditText;
    NotificationModel notification;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public EditText getLevelEditText() {
        return levelEditText;
    }

    public void setLevelEditText(EditText levelEditText) {
        this.levelEditText = levelEditText;
    }

    public EditText getRoomEditText() {
        return roomEditText;
    }

    public void setRoomEditText(EditText roomEditText) {
        this.roomEditText = roomEditText;
    }

    public EditText getDescriptionEditText() {
        return descriptionEditText;
    }

    public void setDescriptionEditText(EditText descriptionEditText) {
        this.descriptionEditText = descriptionEditText;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send_place, container, false);
        levelEditText = (EditText) view.findViewById(R.id.send_place_level_text);
        roomEditText = (EditText) view.findViewById(R.id.send_place_room_text);
        descriptionEditText = (EditText) view.findViewById(R.id.send_place_description_text);

        return view;
    }

    @Override
    public void onResume() {

        notification = Singleton.getInstance().getUserNotification();
        if(notification!=null)
        {
            levelEditText.setText(notification.getLevel());
            roomEditText.setText(notification.getClassroom());
            descriptionEditText.setText(notification.getPlaceDescription());
        }
        super.onResume();
    }
}

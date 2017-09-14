package lukasz.ctistudentclient.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import lukasz.ctistudentclient.Models.NotificationModel;
import lukasz.ctistudentclient.Models.Singleton;
import lukasz.ctistudentclient.R;

public class SendPriorityFragment extends Fragment {


    private RadioGroup radioGroup;
    private NotificationModel notification;

    public RadioGroup getRadioGroup() {
        return radioGroup;
    }

    public void setRadioGroup(RadioGroup radioGroup) {
        this.radioGroup = radioGroup;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send_priority, container, false);

        radioGroup = (RadioGroup) view.findViewById(R.id.fragment_send_priority_radioGroup);

        return view;
    }

    @Override
    public void onResume() {
        notification = Singleton.getInstance().getUserNotification();
        if (notification != null && radioGroup != null) {
            ((RadioButton)radioGroup.getChildAt(notification.getPriority())).setChecked(true);
            //radioGroup.check(notification.getPriority());
        }
            super.onResume();
    }
}

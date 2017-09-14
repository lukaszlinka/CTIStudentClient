package lukasz.ctistudentclient.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import lukasz.ctistudentclient.Models.NotificationModel;
import lukasz.ctistudentclient.R;


public class NotificationDataTemplate extends BaseAdapter {

    Context context;
    NotificationModel notificationList[];

    LayoutInflater inflter;

    public NotificationDataTemplate(Context context, NotificationModel[] notificationList) {
        this.context = context;
        this.notificationList = notificationList;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return notificationList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.notification_list_datatemplate, null);
        ImageView image = (ImageView) view.findViewById(R.id.notification_data_template_image);
        TextView description = (TextView) view.findViewById(R.id.notification_data_template_description);
        TextView rooms = (TextView) view.findViewById(R.id.notification_data_template_place_room);
        TextView placeDescription = (TextView) view.findViewById(R.id.notification_data_template_place_description);
        TextView level = (TextView) view.findViewById(R.id.notification_data_template_place_level);
        TextView scanCode = (TextView) view.findViewById(R.id.notification_data_template_scan_code);
        TextView priority = (TextView) view.findViewById(R.id.notification_data_template_priority);

        if (notificationList[i].getImage() != null)
            image.setImageBitmap(notificationList[i].getImage());
        else
            image.setImageResource(R.drawable.ic_notification_list_photo);

        if (!notificationList[i].getDescription().matches(""))
            description.setText(notificationList[i].getDescription());
        else
            description.setText("Brak");

        if (!notificationList[i].getClassroom().matches(""))
            rooms.setText(notificationList[i].getClassroom());
        else
            rooms.setText("Brak");

        if (!notificationList[i].getPlaceDescription().matches(""))
            placeDescription.setText(notificationList[i].getPlaceDescription());
        else
            placeDescription.setText("Brak");

        if (!notificationList[i].getLevel().matches(""))
            level.setText(notificationList[i].getLevel());
        else
            level.setText("Brak");

        if (!notificationList[i].getScanCode().matches(""))
            scanCode.setText(notificationList[i].getScanCode());
        else
            scanCode.setText("Brak");

        priority.setText(notificationList[i].getPriority() + "");

        return view;
    }
}

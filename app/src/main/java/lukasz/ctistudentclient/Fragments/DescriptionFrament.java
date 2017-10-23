package lukasz.ctistudentclient.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import lukasz.ctistudentclient.Activity.PhotoActivity;
import lukasz.ctistudentclient.Models.NotificationModel;
import lukasz.ctistudentclient.Session.UserSession;
import lukasz.ctistudentclient.R;


public class DescriptionFrament extends Fragment implements View.OnClickListener{

    private NotificationModel notification;
    private EditText editText;
    private Button bt;
    private View view;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    ImageView imageView;

    @Override
    public void onResume() {
        SetImage();
        super.onResume();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_description, container,false);

        editText = (EditText) view.findViewById(R.id.send_description_editText);
        bt = (Button)view.findViewById(R.id.send_description_photoButton);
        bt.setOnClickListener(this);
        imageView = (ImageView) view.findViewById(R.id.fragment_send_description_imageView);
        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_description_photoButton:
                Intent intent = new Intent(getActivity(), PhotoActivity.class);
                startActivity(intent);
                break;
        }
    }
    public EditText getEditText() {
        editText = (EditText) view.findViewById(R.id.send_description_editText);
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public void SetImage(){
        notification = UserSession.getInstance().getUserNotification();
        if(notification!=null && imageView!=null){
            if(notification.getImage()!=null)
            imageView.setImageBitmap(notification.getImage());
        }
    }
}

package lukasz.ctistudentclient.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;
import lukasz.ctistudentclient.Session.UserSession;
import lukasz.ctistudentclient.Models.UserModel;
import lukasz.ctistudentclient.Services.UserService;
import lukasz.ctistudentclient.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by tukan on 29.12.2016.
 */

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private EditText name, lastName, email, city, street, number;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView, login;
    private int year, month, day;
    private Button logOutbutton, saveButton;

    // adapter REST z Retrofita
    RestAdapter retrofit;
    // nasz interfejs
    UserService userService;

    @Override
    protected void onResume() {
        UserModel user = UserSession.getInstance().getUserProfile();
        if (user != null) {
            login.setText((user.getLogin()));
            name.setText(user.getFirstName());
            lastName.setText(user.getLastName());
            email.setText(user.getEmail());
            showDate(user.getBirthday().getYear(), user.getBirthday().getMonth(), user.getBirthday().getDay());
            city.setText(user.getCity());
            street.setText(user.getStreet());
            number.setText(user.getNumber());
        }
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logOutbutton = (Button) findViewById(R.id.profile_logoutBtn);
        logOutbutton.setOnClickListener(this);

        saveButton = (Button) findViewById(R.id.profile_saveBtn);
        saveButton.setOnClickListener(this);

        login = (TextView) findViewById(R.id.profile_login_textView);
        name = (EditText) findViewById(R.id.profile_firstNameET);
        lastName = (EditText) findViewById(R.id.profile_lastNameET);
        email = (EditText) findViewById(R.id.profile_emailET);
        dateView = (TextView) findViewById(R.id.profile_dateOfBirthET);
        city = (EditText) findViewById(R.id.profile_cityET);
        street = (EditText) findViewById(R.id.profile_streetET);
        number = (EditText) findViewById(R.id.profile_numberET);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

        // adapter
        retrofit = new RestAdapter.Builder()
                // adres API
                .setEndpoint("http://ctimobile.com/")
                // niech Retrofit loguje wszystko co robi
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        // klient
        userService = retrofit.create(UserService.class);

        try {
        userService.getUser(new Callback<UserModel>() {
            @Override
            public void success(UserModel userService, Response response) {
                UserSession.getInstance().setUserProfile(userService);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("ProfileActivity", error.getLocalizedMessage());
            }
        });
        } catch (Exception e) {
            Log.d("ProfileActivity", e.toString());
        }
    }

    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {
//            Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
//            startActivity(intent);
        } else if (id == R.id.nav_notification) {
            Intent intent = new Intent(ProfileActivity.this, Notificationactivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(ProfileActivity.this, NotificationListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(ProfileActivity.this, SendFeedbackActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_logoutBtn:

                this.finish();
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.profile_saveBtn:

                try {
                    // zdefiniujmy dane, które mają by wysłane
                    UserModel body = new UserModel();
                    body.setLogin(login.getText().toString());
                    body.setLastName(lastName.getText().toString());
                    body.setFirstName(name.getText().toString());
                    body.setEmail(email.getText().toString());
                    body.setCity(city .getText().toString());
                    body.setNumber(number.getText().toString());
                    body.setStreet(street .getText().toString());
                    body.setBirthday(new Date(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth()));

                    userService.postUser(body, new Callback<UserModel>() {
                        @Override
                        public void success(UserModel userService, Response response) {
                            Toast.makeText(getApplicationContext(), "Dane zostały zapisane", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Toast.makeText(getApplicationContext(), "Wystąpił problem", Toast.LENGTH_LONG).show();
                        }
                    });

                } catch (Exception e) {
                    Log.d("ProfileActivity", e.toString());
                }
                break;
        }
    }
}

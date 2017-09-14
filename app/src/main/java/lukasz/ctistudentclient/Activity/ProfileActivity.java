package lukasz.ctistudentclient.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;

import lukasz.ctistudentclient.Models.Singleton;
import lukasz.ctistudentclient.Models.UserModel;
import lukasz.ctistudentclient.Services.UserService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import lukasz.ctistudentclient.R;

/**
 * Created by tukan on 29.12.2016.
 */

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Callback<UserModel> {

    private EditText name, lastName, email, city, street, number;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    @Override
    protected void onResume() {
        UserModel user = Singleton.getInstance().getUserProfile();
        if (user != null) {
            name.setText(user.getFirstName());
            lastName.setText(user.getLastName());
            email.setText(user.getEmail());
            showDate(user.getBirthday().getYear(),user.getBirthday().getMonth(),user.getBirthday().getDay());
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
        showDate(year, month+1, day);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        UserService userService = retrofit.create(UserService.class);

        Call<UserModel> callUser = userService.getUser("vogella");
        //asynchronous call
        callUser.enqueue(this);

    }
    @SuppressWarnings("deprecation")
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
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_notification) {
            Intent intent = new Intent(ProfileActivity.this, Notificationactivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(ProfileActivity.this, QrCodeScannerActivity.class);
            startActivity(intent);
        }  else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResponse(Response<UserModel> response, Retrofit retrofit) {
        int code = response.code();
        if (code == 200) {
            UserModel user = response.body();
            Toast.makeText(this, "Got the user: " + user.getEmail(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Did not work: " + String.valueOf(code), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, "Nope", Toast.LENGTH_LONG).show();
    }
}

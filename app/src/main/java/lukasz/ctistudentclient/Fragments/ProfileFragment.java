package lukasz.ctistudentclient.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import lukasz.ctistudentclient.R;
import lukasz.ctistudentclient.Session.UserSession;

public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView tvFirstName, tvLastName, tvEmail, tvCity, tvStreet, tvNumber, tvBirthday;
    private Button btnLogout;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvFirstName = (TextView) view.findViewById(R.id.profile_fragment_firstname);
        tvLastName = (TextView) view.findViewById(R.id.profile_fragment_lastname);
        tvEmail = (TextView) view.findViewById(R.id.profile_fragment_email);
        tvBirthday = (TextView) view.findViewById(R.id.profile_fragment_birthday);
        tvCity = (TextView) view.findViewById(R.id.profile_fragment_city);
        tvStreet = (TextView) view.findViewById(R.id.profile_fragment_street);
        tvNumber = (TextView) view.findViewById(R.id.profile_fragment_number);

        btnLogout = (Button) view.findViewById((R.id.profile_logoutBtn));


        tvFirstName.setText("" + UserSession.Instance().getFirstName());
        tvLastName.setText("" + UserSession.Instance().getLastName());
        tvEmail.setText("" + UserSession.Instance().getEmail());
        tvBirthday.setText("" + UserSession.Instance().getBirthday());
        tvCity.setText("" + UserSession.Instance().getCity());
        tvStreet.setText("" + UserSession.Instance().getStreet());
        tvNumber.setText("" + UserSession.Instance().getNumber());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            UserSession.Instance().ClearUser();
            }
        });
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

package edu.gatech.seclass.jobcompare6300;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;


public class twoJobDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private DBHandler dbHandler;
    private Button gotoaddofferButton;
    private Button gotomainmenuButton;

    private EditText Currenttitle;
    private EditText Offertitle;
    private EditText Currentcompany;
    private EditText Offercompany;
    private EditText Currentlocation;
    private EditText Offerlocation;
    private EditText Currentsalary;
    private EditText Offersalary;
    private EditText Currentbonus;
    private EditText Offerbonus;
    private EditText CurrentleaveTime;
    private EditText OfferleaveTime;
    private EditText Currentstock;
    private EditText Offerstock;
    private EditText Currenthomefund;
    private EditText Offerhomefund;
    private EditText CurrentWellness;
    private EditText OfferWellness;




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public twoJobDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_two_job_detail, container, false);

        gotoaddofferButton = view.findViewById(R.id.gotoaddoffer);
        gotomainmenuButton = view.findViewById(R.id.gotomainmenu);

        Currenttitle=view.findViewById(R.id.CurrTitle);
        Offertitle=view.findViewById(R.id.OfferTitle);
        Currentcompany=view.findViewById(R.id.CurrentCompany);
        Offercompany=view.findViewById(R.id.OfferCompany);
        Currentlocation=view.findViewById(R.id.CurrentLocation);
        Offerlocation=view.findViewById(R.id.OfferLocation);
        Currentsalary=view.findViewById(R.id.CurrentSalary);
        Offersalary=view.findViewById(R.id.OfferSalary);
        Currentbonus=view.findViewById(R.id.CurrentBonus);
        Offerbonus=view.findViewById(R.id.OfferBonus);
        CurrentleaveTime=view.findViewById(R.id.CurrentLeaveTime);
        OfferleaveTime=view.findViewById(R.id.OfferLeaveTime);
        Currentstock=view.findViewById(R.id.CurrentStock);
        Offerstock=view.findViewById(R.id.OfferStock);
        Currenthomefund=view.findViewById(R.id.CurrentHomeFund);
        Offerhomefund=view.findViewById(R.id.OfferHomeFund);
        CurrentWellness=view.findViewById(R.id.CurrentWellnessFund);
        OfferWellness=view.findViewById(R.id.OfferWellnessFund);
        if (getArguments() != null)
        {
            Offertitle.setText(getArguments().getString("title"));
            Offercompany.setText(getArguments().getString("company"));
            Offerlocation.setText(getArguments().getString("location"));
            Offersalary.setText(getArguments().getString("salary"));
            Offerbonus.setText(getArguments().getString("bonus"));
            OfferleaveTime.setText(getArguments().getString("leavetime"));
            Offerstock.setText(getArguments().getString("stock"));
            Offerhomefund.setText(getArguments().getString("homefund"));
            OfferWellness.setText(getArguments().getString("wellnessfund"));
        }

        //Check if any job comes with "current" True; if so then read it and set fields,
        //and change button text
        dbHandler = new DBHandler(getActivity());
        List<Job> all_jobs=dbHandler.getAllJobs();
        for (int i = 0; i < all_jobs.size(); i++) {
            Job someJob=all_jobs.get(i);
            Log.d("current", someJob.currentjob);
            if (someJob.currentjob.equals("true")){
                Currenttitle.setText(someJob.title);
                Currentcompany.setText(someJob.company);
                Currentlocation.setText(someJob.location);
                Currentsalary.setText(String.valueOf(someJob.salary));
                Currentbonus.setText(String.valueOf(someJob.bonus));
                CurrentleaveTime.setText(String.valueOf(someJob.leavetime));
                Currentstock.setText(String.valueOf(someJob.stock));
                Currenthomefund.setText(String.valueOf(someJob.homefund));
                CurrentWellness.setText(String.valueOf(someJob.wellnessfund));
                break;
            }
        }

        gotoaddofferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.twoJobDetailFragment_to_JobOfferFragment);
            }
        } );

        gotomainmenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.twoJobDetailFragment_to_MainMenuFragment);
            }
        } );


        return view;
    }
}
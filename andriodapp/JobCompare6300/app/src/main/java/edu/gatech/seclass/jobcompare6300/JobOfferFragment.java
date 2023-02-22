package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JobOfferFragment extends Fragment {


    private Button saveJobOfferButton;
    private Button joboffertomainButton;
    private Button resetofferButton;
    private Button VSCurrentJobButton;

    private DBHandler dbHandler;
    private EditText offerTitle;
    private EditText offerCompany;
    private EditText offerLocation;
    private EditText offerSalary;
    private EditText offerBonus;
    private EditText offerLeaveTime;
    private EditText offerStock;
    private EditText offerHomeFund;
    private EditText offerWellnessFund;
    private boolean textError=true;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_joboffer, container, false);

        offerTitle = (EditText)view.findViewById(R.id.offerTitle);
        offerCompany = view.findViewById(R.id.offerCompany);
        offerLocation = view.findViewById(R.id.offerLocation);
        offerSalary = view.findViewById(R.id.offerSalary);
        offerBonus = view.findViewById(R.id.offerBonus);
        offerLeaveTime = view.findViewById(R.id.offerLeveTime);
        offerStock= view.findViewById(R.id.offerStock);
        offerHomeFund= view.findViewById(R.id.offerHomeFund);
        offerWellnessFund= view.findViewById(R.id.offerWellnessFund);

        //snackbar for pop-up message;
        Context context = getActivity();
        CharSequence offerSaved = "Offer Saved!";
        CharSequence offerReset = "Offer Reset!";
        int duration = Toast.LENGTH_SHORT;


        saveJobOfferButton = view.findViewById(R.id.saveJobOffer);
        saveJobOfferButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String title = offerTitle.getText().toString();
                    String company = offerCompany.getText().toString();
                    String location = offerLocation.getText().toString();
                    String salary = offerSalary.getText().toString();
                    String bonus = offerBonus.getText().toString();
                    String leavetime = offerLeaveTime.getText().toString();
                    String stock= offerStock.getText().toString();
                    String homefund= offerHomeFund.getText().toString();
                    String wellnessfund = offerWellnessFund.getText().toString();
                    String currentjob="false";
                    // empty input validation
                    isInputEmpty(title,company,location,salary,bonus,leavetime,stock,homefund,wellnessfund);
                    if(textError) {
                        dbHandler = new DBHandler(getActivity());
                        dbHandler.addNewJob(title, company, location, Integer.parseInt(salary), Integer.parseInt(bonus), Integer.parseInt(leavetime)
                                , Integer.parseInt(stock), Integer.parseInt(homefund), Integer.parseInt(wellnessfund), currentjob);
                        Toast.makeText(context, offerSaved, duration).show();
                    }
            }

        }) );


        joboffertomainButton= view.findViewById(R.id.joboffertomain);
        joboffertomainButton.setOnClickListener((new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigateToMainMenuFragment);

            }
        } ) );

        resetofferButton= view.findViewById(R.id.cancelJobOffer);
        resetofferButton.setOnClickListener((new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                offerTitle.setText("");
                offerCompany.setText("");
                offerLocation.setText("");
                offerSalary.setText("");
                offerBonus.setText("");
                offerLeaveTime.setText("");
                offerStock.setText("");
                offerHomeFund.setText("");
                offerWellnessFund.setText("");
                removeError();
                Toast.makeText(context, offerReset, duration).show();
            }
        } ) );

        VSCurrentJobButton = view.findViewById(R.id.VSCurrent);
        VSCurrentJobButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                    Bundle bundle = new Bundle();
                    bundle.putString("title", offerTitle.getText().toString());
                    bundle.putString("company", offerCompany.getText().toString());
                    bundle.putString("location", offerLocation.getText().toString());
                    bundle.putString("salary", offerSalary.getText().toString());
                    bundle.putString("bonus", offerBonus.getText().toString());
                    bundle.putString("leavetime", offerLeaveTime.getText().toString());
                    bundle.putString("stock", offerStock.getText().toString());
                    bundle.putString("homefund", offerHomeFund.getText().toString());
                    bundle.putString("wellnessfund", offerWellnessFund.getText().toString());
                    bundle.putString("currentjob", "false");
                // empty input validation
                isInputEmpty(offerTitle.getText().toString(), offerCompany.getText().toString(), offerLocation.getText().toString(), offerSalary.getText().toString(), offerBonus.getText().toString(), offerLeaveTime.getText().toString(), offerStock.getText().toString(), offerHomeFund.getText().toString(), offerWellnessFund.getText().toString());
                if (textError) {
                    Navigation.findNavController(view).navigate(R.id.navigateToVSCurrentFragment, bundle);
                }
            }
        } );


        return view;
    }

    // this method check empty input validation
    public void isInputEmpty(String title,String company,String location,String salary,String bonus,String leavetime,String stock,String homefund,String wellnessfund){
        if(title.length()==0){
            offerTitle.setError("Enter Title");
            textError=false;
        }
        if(company.length()==0){
            offerCompany.setError("Enter Company");
            textError=false;
        }
        if(location.length()==0){
            offerLocation.setError("Enter Location");
            textError=false;
        }
        if(salary.length()==0){
            offerSalary.setError("Enter Salary");
            textError=false;
        }
        if(bonus.length()==0){
            offerBonus.setError("Enter Bonus");
            textError=false;
        }
        if(leavetime.length()==0){
            offerLeaveTime.setError("Enter Leave Time");
            textError=false;
        }
        if(stock.length()==0){
            offerStock.setError("Enter Stock Amount");
            textError=false;
        }
        if(homefund.length()==0){
            offerHomeFund.setError("Enter Home Fund Amount");
            textError=false;
        }
        if(wellnessfund.length()==0){
            offerWellnessFund.setError("Enter Wellness Fund Amount");
            textError=false;
        }

    }
    // this method will remove all error for reset method
    public void removeError(){
        offerTitle.setError(null);
        offerCompany.setError(null);
        offerLocation.setError(null);
        offerSalary.setError(null);
        offerBonus.setError(null);
        offerLeaveTime.setError(null);
        offerStock.setError(null);
        offerHomeFund.setError(null);
        offerWellnessFund.setError(null);
        textError=true;

    }


    }

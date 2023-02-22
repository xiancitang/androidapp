package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class CurrentJobFragment extends Fragment {

    private DBHandler dbHandler;
    private EditText jobTitle;
    private EditText jobCompany;
    private EditText jobLocation;
    private EditText jobSalary;
    private EditText jobBonus;
    private EditText jobLeaveTime;
    private EditText jobStock;
    private EditText jobHomeFund;
    private EditText jobWellnessFund;
    private Button saveJobButton;
    private Button jobtomainButton;
    private Button resetjobButton;
    private boolean textError = true;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_job, container, false);

        //Link Fields
        jobTitle = (EditText)view.findViewById(R.id.jobTitle);
        jobCompany = view.findViewById(R.id.jobCompany);
        jobLocation = view.findViewById(R.id.jobLocation);
        jobSalary = view.findViewById(R.id.jobSalary);
        jobBonus = view.findViewById(R.id.jobBonus);
        jobLeaveTime = view.findViewById(R.id.jobLeveTime);
        jobStock= view.findViewById(R.id.jobStock);
        jobHomeFund= view.findViewById(R.id.jobHomeFund);
        jobWellnessFund= view.findViewById(R.id.jobWellnessFund);

        //Link buttons
        saveJobButton = view.findViewById(R.id.saveJob);
        jobtomainButton= view.findViewById(R.id.jobbackmain);
        resetjobButton= view.findViewById(R.id.cancelJob);
        //snackbar for pop-up message;
        Context context = getActivity();
        CharSequence jobSaved = "Job Saved!";
        CharSequence jobReset = "Job Reset!";
        int duration = Toast.LENGTH_SHORT;


        //Check if any job comes with "current" True; if so then read it and set fields,
        //and change button text
        dbHandler = new DBHandler(getActivity());
        List<Job> all_jobs=dbHandler.getAllJobs();
        for (int i = 0; i < all_jobs.size(); i++) {
            Job someJob=all_jobs.get(i);
            Log.d("current", someJob.currentjob);
            if (someJob.currentjob.equals("true")){
                saveJobButton.setText("Edit");
                jobTitle.setText(someJob.title);
                jobCompany.setText(someJob.company);
                jobLocation.setText(someJob.location);
                jobSalary.setText(String.valueOf(someJob.salary));
                jobBonus.setText(String.valueOf(someJob.bonus));
                jobLeaveTime.setText(String.valueOf(someJob.leavetime));
                jobStock.setText(String.valueOf(someJob.stock));
                jobHomeFund.setText(String.valueOf(someJob.homefund));
                jobWellnessFund.setText(String.valueOf(someJob.wellnessfund));
            }
        }


        saveJobButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = jobTitle.getText().toString();
                String company = jobCompany.getText().toString();
                String location = jobLocation.getText().toString();
                String salary = jobSalary.getText().toString();
                String bonus = jobBonus.getText().toString();
                String leavetime = jobLeaveTime.getText().toString();
                String stock= jobStock.getText().toString();
                String homefund= jobHomeFund.getText().toString();
                String wellnessfund = jobWellnessFund.getText().toString();
                String currentjob="true";
                CharSequence text = saveJobButton.getText();



                dbHandler = new DBHandler(getActivity());
                // if button is save, addNewJob method ; is button is edit , it is update method
                if ("Save".equals(text)) {
                    //empty input validation
                    isInputEmpty(title,company,location,salary,bonus,leavetime,stock,homefund,wellnessfund);

                    if(textError) {
                      
                        Log.d("save job", "save job ");

                        dbHandler.addNewJob(title, company, location, Integer.parseInt(salary), Integer.parseInt(bonus), Integer.parseInt(leavetime)
                                , Integer.parseInt(stock), Integer.parseInt(homefund), Integer.parseInt(wellnessfund), currentjob);
                        Toast.makeText(context, jobSaved, duration).show();
                    }

                }
                else{
                    Log.d("edit job", "edit job");
                    dbHandler.updateJob(title, company, location, Integer.parseInt(salary), Integer.parseInt(bonus), Integer.parseInt(leavetime)
                            , Integer.parseInt(stock), Integer.parseInt(homefund), Integer.parseInt(wellnessfund));
                    Toast.makeText(context, jobSaved, duration).show();
                }
            }

        }) );

        jobtomainButton.setOnClickListener((new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_currentJobFragment_to_MainMenuFragment);

            }
        } ) );


        resetjobButton.setOnClickListener((new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                jobTitle.setText("");
                jobCompany.setText("");
                jobLocation.setText("");
                jobSalary.setText("");
                jobBonus.setText("");
                jobLeaveTime.setText("");
                jobStock.setText("");
                jobHomeFund.setText("");
                jobWellnessFund.setText("");
                if(!textError){
                    removeError();
                }
                Toast.makeText(context, jobReset, duration).show();
            }
        } ) );

        return view;
    }

    // this method check empty input validation
    public void isInputEmpty(String title,String company,String location,String salary,String bonus,String leavetime,String stock,String homefund,String wellnessfund){
        if(title.length()==0){
            jobTitle.setError("Enter Title");
            textError=false;
        }
        if(company.length()==0){
            jobCompany.setError("Enter Company");
            textError=false;
        }
        if(location.length()==0){
            jobLocation.setError("Enter Location");
            textError=false;
        }
        if(salary.length()==0){
            jobSalary.setError("Enter Salary");
            textError=false;
        }
        if(bonus.length()==0){
            jobBonus.setError("Enter Bonus");
            textError=false;
        }
        if(leavetime.length()==0){
            jobLeaveTime.setError("Enter Leave Time");
            textError=false;
        }
        if(stock.length()==0){
            jobStock.setError("Enter Stock Amount");
            textError=false;
        }
        if(homefund.length()==0){
            jobHomeFund.setError("Enter Home Fund Amount");
            textError=false;
        }
        if(wellnessfund.length()==0){
            jobWellnessFund.setError("Enter Wellness Fund Amount");
            textError=false;
        }

    }
    // this method will remove all error for reset method
    public void removeError(){
            jobTitle.setError(null);
            jobCompany.setError(null);
            jobLocation.setError(null);
            jobSalary.setError(null);
            jobBonus.setError(null);
            jobLeaveTime.setError(null);
            jobStock.setError(null);
            jobHomeFund.setError(null);
            jobWellnessFund.setError(null);
            textError=true;

    }
}
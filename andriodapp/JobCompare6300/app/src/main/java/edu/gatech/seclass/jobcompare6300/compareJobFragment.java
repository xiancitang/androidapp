package edu.gatech.seclass.jobcompare6300;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class compareJobFragment extends Fragment {


    private DBHandler dbHandler;
    private Button twojobComB;
    private Button resetCheckbox;
    private Button comparebackmainButton;
    private EditText title1;
    private EditText title2;
    private EditText company1;
    private EditText company2;
    private EditText loc1;
    private EditText loc2;
    private EditText salary1;
    private EditText salary2;
    private EditText bonus1;
    private EditText bonus2;
    private EditText leave1;
    private EditText leave2;
    private EditText stock1;
    private EditText stock2;
    private EditText homefund1;
    private EditText homefund2;
    private EditText wellness1;
    private EditText wellness2;

    private int AYS_w;
    private int AYB_w;
    private int LT_w;
    private int CSO_w;
    private int HBP_w;
    private int WF_w;
    public static final String AYS_W = "AYS_w";
    public static final String AYB_W = "AYB_w";
    public static final String LT_W = "LT_w";
    public static final String CSO_W = "CSO_w";
    public static final String HBP_W = "HBP_w";
    public static final String WF_W = "WF_w";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            AYS_w = getArguments().getInt(AYS_W, 1);
            AYB_w = getArguments().getInt(AYB_W, 1);
            LT_w = getArguments().getInt(LT_W, 1);
            CSO_w = getArguments().getInt(CSO_W, 1);
            HBP_w = getArguments().getInt(HBP_W, 1);
            WF_w = getArguments().getInt(WF_W, 1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_compare_job, container, false);

        LinearLayout cbg =  view.findViewById(R.id.jobListArea);
        cbg.setOrientation(LinearLayout.VERTICAL);
        List<Job> all_jobs=sortJobs(AYS_w,AYB_w,LT_w,CSO_w,HBP_w,WF_w);
        int jobNum=all_jobs.size();



        List checkBoxId = new ArrayList();
        ArrayList CheckBox_jobId = new ArrayList();
        for (int i = 1; i <= jobNum ; i++) {
            CheckBox rbn = new CheckBox(getActivity());
//            int gene_id=View.generateViewId();
            int gene_id=i;
            checkBoxId.add(gene_id);

            CheckBox_jobId.add(all_jobs.get(i-1).id);
            rbn.setId(gene_id);

            String str=all_jobs.get(i-1).currentjob;
            if(all_jobs.get(i-1).currentjob.equals("true"))
            {
                str=String.format("%2d%16s%22s%28s%10s", i, all_jobs.get(i-1).title, all_jobs.get(i-1).company,all_jobs.get(i-1).jobscore,"*");
            }
            else
            {
                str=String.format("%2d%16s%22s%28s%10s", i, all_jobs.get(i-1).title, all_jobs.get(i-1).company,all_jobs.get(i-1).jobscore," ");
            }
            rbn.setText(str);
            cbg.addView(rbn);

        }
        title1=view.findViewById(R.id.title1);
        title2=view.findViewById(R.id.title2);
        company1=view.findViewById(R.id.company1);
        company2=view.findViewById(R.id.company2);
        loc1=view.findViewById(R.id.loc1);
        loc2=view.findViewById(R.id.loc2);
        salary1=view.findViewById(R.id.salary1);
        salary2=view.findViewById(R.id.salary2);
        bonus1=view.findViewById(R.id.bonus1);
        bonus2=view.findViewById(R.id.bonus2);
        leave1=view.findViewById(R.id.leave1);
        leave2=view.findViewById(R.id.leave2);
        stock1=view.findViewById(R.id.stock1);
        stock2=view.findViewById(R.id.stock2);
        homefund1=view.findViewById(R.id.homefund1);
        homefund2=view.findViewById(R.id.homefund2);
        wellness1=view.findViewById(R.id.wellness1);
        wellness2=view.findViewById(R.id.wellness2);

        twojobComB=view.findViewById(R.id.twoJobCompare);
        if(jobNum>=2){
            twojobComB.setEnabled(true);
        } else {
            twojobComB.setEnabled(false);
        }

        twojobComB.setOnClickListener((new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ArrayList SelectedJob_jobID = new ArrayList();
                Log.d("Inside Button", "Inside Button" + cbg.getChildCount());

                for(int i=0; i<cbg.getChildCount(); i++) {
                    LinearLayout nextChild = (LinearLayout) cbg.getChildAt(i);
                    CheckBox child  = (CheckBox) nextChild.getChildAt(0);

                    if(child instanceof CheckBox)
                    {
                        CheckBox check = (CheckBox) child;
                        if (check.isChecked()) {
                            int a= (int) check.getId()-1;
                            SelectedJob_jobID.add(CheckBox_jobId.get(a));
                        }
                    }

                }
                if(SelectedJob_jobID.size()!=2)
                {

                    Toast toast =Toast.makeText(getContext(),"Must select two jobs",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    Log.d("picked jobs", "two jobs" + SelectedJob_jobID);
                    int id1= Integer.parseInt((String)SelectedJob_jobID.get(0));
                    int id2= Integer.parseInt((String)SelectedJob_jobID.get(1));
                    Job first= dbHandler.getJob(id1);
                    Job second= dbHandler.getJob(id2);
                    title1.setText(first.title);
                    title2.setText(second.title);
                    company1.setText(first.company);
                    company2.setText(second.company);
                    loc1.setText(first.location);
                    loc2.setText(second.location);
                    salary1.setText(String.valueOf(first.salary));
                    salary2.setText(String.valueOf(second.salary));
                    bonus1.setText(String.valueOf(first.bonus));
                    bonus2.setText(String.valueOf(second.bonus));
                    leave1.setText(String.valueOf(first.leavetime));
                    leave2.setText(String.valueOf(second.leavetime));
                    stock1.setText(String.valueOf(first.stock));
                    stock2.setText(String.valueOf(second.stock));
                    homefund1.setText(String.valueOf(first.homefund));
                    homefund2.setText(String.valueOf(second.homefund));
                    wellness1.setText(String.valueOf(first.wellnessfund));
                    wellness2.setText(String.valueOf(second.wellnessfund));
                }
                }

        }));

        resetCheckbox=view.findViewById(R.id.resetCheckbox);
        resetCheckbox.setOnClickListener((new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Confirm you want to delete selected offers");
                builder.setTitle("Are you sure !?");
                builder.setCancelable(false);

                // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    String SelectedJob_jobID;
                    Log.d("Inside Button", "Inside Button" + cbg.getChildCount());

                    for(int i=0; i<cbg.getChildCount(); i++) {
                        LinearLayout nextChild = (LinearLayout) cbg.getChildAt(i);
                        CheckBox child  = (CheckBox) nextChild.getChildAt(0);

                        if(child instanceof CheckBox)
                        {
                            CheckBox check = (CheckBox) child;
                            if (check.isChecked()) {
                                int a= (int) check.getId()-1;
                                SelectedJob_jobID=CheckBox_jobId.get(a).toString();
                                dbHandler.deletejob(new String[] {SelectedJob_jobID});

                            }
                        }

                    }
                    Bundle bundle = new Bundle();
                    bundle.putInt(AYS_W, AYS_w);
                    bundle.putInt(AYB_W, AYB_w);
                    bundle.putInt(LT_W, LT_w);
                    bundle.putInt(CSO_W, CSO_w);
                    bundle.putInt(HBP_W, HBP_w);
                    bundle.putInt(WF_W, WF_w);
                    Navigation.findNavController(view).navigate(R.id.action_compareJobFragment_self,bundle);
                });
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        }));


        comparebackmainButton=view.findViewById(R.id.comparebacktomain);
        comparebackmainButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_compareJobFragment_to_MainMenuFragment);
            }

        }));

        return view;
    }



//Return a list of jobs sorted by job score from high to low, descending order
    public List<Job> sortJobs(int AYS_w,int AYB_w,int LT_w,int CSO_w,int HBP_w,int WF_w){

        dbHandler = new DBHandler(getActivity());
        List<Job> all_jobs=dbHandler.getAllJobs();
        int jobNum=all_jobs.size();
        int Weightsum=AYS_w+AYB_w+LT_w+CSO_w+HBP_w+WF_w;
        for (int i = 0; i < jobNum ; i++) {
            int salary=all_jobs.get(i).salary;
            int bonus=all_jobs.get(i).bonus;
            int leavetime=all_jobs.get(i).leavetime;
            int stock=all_jobs.get(i).stock;
            int homefund=all_jobs.get(i).homefund;
            int wellnessfund=all_jobs.get(i).wellnessfund;
            Log.d("pass1", String.valueOf(AYS_w));
            Log.d("pass2", String.valueOf(AYB_w));
            Log.d("pass3", String.valueOf(LT_w));
            Log.d("pass4", String.valueOf(CSO_w));
            Log.d("pass5", String.valueOf(HBP_w));
            Log.d("pass6", String.valueOf(WF_w));
//            weights converted to fractions, so the sum of all weight is 1
            float jobscore=(float)AYS_w/Weightsum*salary+(float)AYB_w/Weightsum*bonus+
                    (float)LT_w/Weightsum*(leavetime*salary/260)+(float)CSO_w/Weightsum*(stock/2)+
                    (float)HBP_w/Weightsum*homefund+(float)WF_w/Weightsum*wellnessfund;

            BigDecimal j = new BigDecimal(jobscore);
            float rounded = j.setScale(2, RoundingMode.DOWN).floatValue();
            all_jobs.get(i).setJobscore(rounded);
        }
        Collections.sort(all_jobs, Comparator.comparing(Job::getJobscore).reversed());
    return all_jobs;
     }


}


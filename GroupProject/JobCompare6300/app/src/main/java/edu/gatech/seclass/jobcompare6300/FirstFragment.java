package edu.gatech.seclass.jobcompare6300;

import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public class FirstFragment extends Fragment {


    private DBHandler dbHandler;
    private Map<String, Integer> map;
    private Button gotoCurrentJobButton;
    private Button gotoJobOfferButton;
    private Button gotoAdjustWeightButton;
    private Button gotoCompareJobButton;
    private SeekBar salaryWeight;
    private SeekBar bonusWeight;
    private SeekBar leavetimeWeight;
    private SeekBar stockWeight;
    private SeekBar homefundWeight;
    private SeekBar wellnessWeight;
    private EditText jobNumber;


    public static final String AYS_W = "AYS_w";
    public static final String AYB_W = "AYB_w";
    public static final String LT_W = "LT_w";
    public static final String CSO_W = "CSO_w";
    public static final String HBP_W = "HBP_w";
    public static final String WF_W = "WF_w";






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);

        //offer/job quantity reading
        dbHandler = new DBHandler(getActivity());
        int jobNum=dbHandler.numberOfRows();
        jobNumber= (EditText) view.findViewById(R.id.jobNumber);
        jobNumber.setText(String.valueOf(jobNum));

        // Set current job text : if current job is available ,Add current job ,otherwise update current job
        gotoCurrentJobButton = view.findViewById(R.id.goCurrentJob);
        dbHandler = new DBHandler(getActivity());
        List<Job> all_jobs=dbHandler.getAllJobs();
        gotoCurrentJobButton.setText("Add Current Job");
        for (int i = 0; i <all_jobs.size(); i++) {
            Job someJob=all_jobs.get(i);
            if (someJob.currentjob.equals("true")){
                gotoCurrentJobButton.setText("Update Current Job");
            }
        }

        //seekbar setting

        salaryWeight=view.findViewById(R.id.seekBarSalaryW);
        bonusWeight=view.findViewById(R.id.seekBarBonusW);
        leavetimeWeight=view.findViewById(R.id.seekBarLeavetimeW);
        stockWeight=view.findViewById(R.id.seekBarStockW);
        homefundWeight=view.findViewById(R.id.seekBarHomefundW);
        wellnessWeight=view.findViewById(R.id.seekBarWellnessfundW);
        map = new HashMap<String, Integer>();

        if(!dbHandler.weighttableisEmpty())
        {
            ArrayList<Integer> WeightFromDB=dbHandler.getweight();
            Integer a=WeightFromDB.get(0);

            map.put("salaryWeight",WeightFromDB.get(0));
            map.put("bonusWeight",WeightFromDB.get(1));
            map.put("leavetimeWeight",WeightFromDB.get(2));
            map.put("stockWeight",WeightFromDB.get(3));
            map.put("homefundWeight",WeightFromDB.get(4));
            map.put("wellnessWeight",WeightFromDB.get(5));
            salaryWeight.setProgress(WeightFromDB.get(0));
            bonusWeight.setProgress(WeightFromDB.get(1));
            leavetimeWeight.setProgress(WeightFromDB.get(2));
            stockWeight.setProgress(WeightFromDB.get(3));
            homefundWeight.setProgress(WeightFromDB.get(4));
            wellnessWeight.setProgress(WeightFromDB.get(5));
        }
        else {
            map.put("salaryWeight",salaryWeight.getProgress());
            map.put("bonusWeight",bonusWeight.getProgress());
            map.put("leavetimeWeight",leavetimeWeight.getProgress());
            map.put("stockWeight",stockWeight.getProgress());
            map.put("homefundWeight",homefundWeight.getProgress());
            map.put("wellnessWeight",wellnessWeight.getProgress());
            dbHandler.addweights(salaryWeight.getProgress(),bonusWeight.getProgress(),leavetimeWeight.getProgress(),
                    stockWeight.getProgress(),homefundWeight.getProgress(),wellnessWeight.getProgress());
        }

        salaryWeight.setOnSeekBarChangeListener(new myseekbarListener());
        bonusWeight.setOnSeekBarChangeListener(new myseekbarListener());
        leavetimeWeight.setOnSeekBarChangeListener(new myseekbarListener());
        stockWeight.setOnSeekBarChangeListener(new myseekbarListener());
        homefundWeight.setOnSeekBarChangeListener(new myseekbarListener());
        wellnessWeight.setOnSeekBarChangeListener(new myseekbarListener());


        // Current job

        gotoCurrentJobButton = view.findViewById(R.id.goCurrentJob);
        gotoCurrentJobButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_MainMenuFragment_to_currentJobFragment);
            }
        } ) );

        // Job offer
        gotoJobOfferButton = view.findViewById(R.id.enterJobOffer);
        gotoJobOfferButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigateToJobOfferFragment);
            }
        } ) );

        // Compare Jobs
        gotoCompareJobButton = view.findViewById(R.id.compareJob);

        if(jobNum >= 2){
            gotoCompareJobButton.setEnabled(true);
        }
        else {
            gotoCompareJobButton.setEnabled(false);
        }

        gotoCompareJobButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt(AYS_W, map.get("salaryWeight"));
                bundle.putInt(AYB_W, map.get("bonusWeight"));
                bundle.putInt(LT_W, map.get("leavetimeWeight"));
                bundle.putInt(CSO_W, map.get("stockWeight"));
                bundle.putInt(HBP_W, map.get("homefundWeight"));
                bundle.putInt(WF_W, map.get("wellnessWeight"));
                Navigation.findNavController(view).navigate(R.id.action_MainMenuFragment_to_compareJobFragment,bundle);
            }
        } ) );


        return view;
    }

    private class myseekbarListener implements SeekBar.OnSeekBarChangeListener {

        public void onProgressChanged(SeekBar seekBar, int progressValue,
                                      boolean fromUser) {
            switch (seekBar.getId()) {
                case R.id.seekBarSalaryW:
                    map.put("salaryWeight",progressValue);
                    Log.d("cici_salary", String.valueOf(map.get("salaryWeight")));
                    break;
                case R.id.seekBarBonusW:
                    map.put("bonusWeight",progressValue);
                    Log.d("cici_bonus", String.valueOf(map.get("bonusWeight")));
                    break;
                case R.id.seekBarLeavetimeW:
                    map.put("leavetimeWeight",progressValue);
                    Log.d("cici_leavetime", String.valueOf(map.get("leavetimeWeight")));
                    break;
                case R.id.seekBarStockW:
                    map.put("stockWeight",progressValue);
                    Log.d("cici_stock", String.valueOf(map.get("stockWeight")));
                    break;
                case R.id.seekBarHomefundW:
                    map.put("homefundWeight",progressValue);
                    Log.d("cici_homefund", String.valueOf(map.get("homefundWeight")));
                    break;
                case R.id.seekBarWellnessfundW:
                    map.put("wellnessWeight",progressValue);
                    Log.d("cici_wellness", String.valueOf(map.get("wellnessWeight")));
                    break;
            }
            dbHandler.updateWeight(map.get("salaryWeight"),map.get("bonusWeight"),map.get("leavetimeWeight"),
                    map.get("stockWeight"), map.get("homefundWeight"),map.get("wellnessWeight"));
        }
        public void onStartTrackingTouch(SeekBar seekBar) {}

        public void onStopTrackingTouch(SeekBar seekBar) {}

    }

}

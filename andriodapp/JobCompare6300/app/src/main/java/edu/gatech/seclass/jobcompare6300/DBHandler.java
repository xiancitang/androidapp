package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "compareoffer";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "jobs";
    private static final String WeightTABLE_NAME = "weights";
    private static final String ID_COL = "id";
    private static final String TITLE_COL = "title";
    private static final String COMPANY_COL = "company";
    private static final String LOCATION_COL = "location";
    private static final String SALARY_COL = "salary";
    private static final String BONUS_COL = "bonus";
    private static final String LEAVE_COL = "leavetime";
    private static final String STOCK_COL = "stockoptions";
    private static final String HOMEFUND_COL = "homefund";
    private static final String WELLNESSFUND_COL = "wellnessfund";
    private static final String CURRENT_COL = "currentjob";
    public static final String AYS_WCOL = "AYS_w";
    public static final String AYB_WCOL = "AYB_w";
    public static final String LT_WCOL = "LT_w";
    public static final String CSO_WCOL = "CSO_w";
    public static final String HBP_WCOL = "HBP_w";
    public static final String WF_WCOL = "WF_w";




    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE_COL + " TEXT,"
                + COMPANY_COL + " TEXT,"
                + LOCATION_COL + " TEXT,"
                + SALARY_COL + " INTEGER,"
                + BONUS_COL + " INTEGER,"
                + LEAVE_COL + " INTEGER,"
                + STOCK_COL + " INTEGER,"
                + HOMEFUND_COL + " INTEGER,"
                + WELLNESSFUND_COL + " INTEGER,"
                + CURRENT_COL + " TEXT)";;

        db.execSQL(query);

        query = "CREATE TABLE " + WeightTABLE_NAME + " ("
                + AYS_WCOL + " INTEGER,"
                + AYB_WCOL + " INTEGER,"
                + LT_WCOL + " INTEGER,"
                + CSO_WCOL + " INTEGER,"
                + HBP_WCOL + " INTEGER,"
                + WF_WCOL + " INTEGER)";;
        db.execSQL(query);
    }

    public void addNewJob(String title, String company,String location,  Integer salary, Integer bonus,Integer leavetime,Integer stock, Integer homefund, Integer wellnessfund,String current) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TITLE_COL, title);
        values.put(COMPANY_COL, company);
        values.put(LOCATION_COL, location);
        values.put(SALARY_COL, salary);
        values.put(BONUS_COL, bonus);
        values.put(LEAVE_COL, leavetime);
        values.put(STOCK_COL, stock);
        values.put(HOMEFUND_COL, homefund);
        values.put(WELLNESSFUND_COL, wellnessfund);
        values.put(CURRENT_COL, current);


        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public void addweights(Integer AYS_W, Integer AYB_W,Integer LT_W,  Integer CSO_W, Integer HBP_W,Integer WF_W) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AYS_WCOL, AYS_W);
        values.put(AYB_WCOL, AYB_W);
        values.put(LT_WCOL, LT_W);
        values.put(CSO_WCOL, CSO_W);
        values.put(HBP_WCOL, HBP_W);
        values.put(WF_WCOL, WF_W);

        db.insert(WeightTABLE_NAME, null, values);
        db.close();
    }



    // Get all jobs in the database
    public List<Job> getAllJobs() {

        String JOBS_SELECT_QUERY = "SELECT * FROM jobs";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(JOBS_SELECT_QUERY, null);

        List<Job> all_jobs = new ArrayList<>();
        while(cursor.moveToNext()) {
            Job newJob = new Job();
            newJob.id= cursor.getString(cursor.getColumnIndexOrThrow(ID_COL));
            newJob.title= cursor.getString(cursor.getColumnIndexOrThrow(TITLE_COL));
            newJob.company= cursor.getString(cursor.getColumnIndexOrThrow(COMPANY_COL));
            newJob.location= cursor.getString(cursor.getColumnIndexOrThrow(LOCATION_COL));
            newJob.salary= Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(SALARY_COL)));
            newJob.bonus= Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(BONUS_COL)));
            newJob.leavetime= Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(LEAVE_COL)));
            newJob.stock= Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(STOCK_COL)));
            newJob.homefund= Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(HOMEFUND_COL)));
            newJob.wellnessfund= Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(WELLNESSFUND_COL)));
            newJob.currentjob= cursor.getString(cursor.getColumnIndexOrThrow(CURRENT_COL));
            all_jobs.add(newJob);
        }
        cursor.close();

        return all_jobs;
    }

    public ArrayList<Integer> getweight() {

        String WEIGHTS_SELECT_QUERY = "SELECT * FROM weights";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(WEIGHTS_SELECT_QUERY, null);

        ArrayList <Integer> all_weights = new ArrayList<>();
        while(cursor.moveToNext()) {

            all_weights.add(cursor.getInt(cursor.getColumnIndexOrThrow(AYS_WCOL)));
            all_weights.add(cursor.getInt(cursor.getColumnIndexOrThrow(AYB_WCOL)));
            all_weights.add(cursor.getInt(cursor.getColumnIndexOrThrow(LT_WCOL)));
            all_weights.add(cursor.getInt(cursor.getColumnIndexOrThrow(CSO_WCOL)));
            all_weights.add(cursor.getInt(cursor.getColumnIndexOrThrow(HBP_WCOL)));
            all_weights.add(cursor.getInt(cursor.getColumnIndexOrThrow(WF_WCOL)));

        }
        cursor.close();
        return all_weights;
    }



    public Job getJob(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=  db.rawQuery( "select * from jobs where id="+id+"", null );

        Job newJob = new Job();
        while(cursor.moveToNext()) {

            newJob.title= cursor.getString(cursor.getColumnIndexOrThrow(TITLE_COL));
            newJob.company= cursor.getString(cursor.getColumnIndexOrThrow(COMPANY_COL));
            newJob.location= cursor.getString(cursor.getColumnIndexOrThrow(LOCATION_COL));
            newJob.salary= Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(SALARY_COL)));
            newJob.bonus= Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(BONUS_COL)));
            newJob.leavetime= Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(LEAVE_COL)));
            newJob.stock= Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(STOCK_COL)));
            newJob.homefund= Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(HOMEFUND_COL)));
            newJob.wellnessfund= Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(WELLNESSFUND_COL)));
            newJob.currentjob= cursor.getString(cursor.getColumnIndexOrThrow(CURRENT_COL));
        }
        cursor.close();
        return newJob;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean weighttableisEmpty(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, WeightTABLE_NAME);
        if(numRows==0) {
            return true;
        }
        else{
            return false;
        }
    }

    public void updateJob(String title, String company,String location,  Integer salary, Integer bonus,Integer leavetime,Integer stock, Integer homefund, Integer wellnessfund) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE_COL, title);
        values.put(COMPANY_COL, company);
        values.put(LOCATION_COL, location);
        values.put(SALARY_COL, salary);
        values.put(BONUS_COL, bonus);
        values.put(LEAVE_COL, leavetime);
        values.put(STOCK_COL, stock);
        values.put(HOMEFUND_COL, homefund);
        values.put(WELLNESSFUND_COL, wellnessfund);

        db.update("jobs", values, CURRENT_COL+ " = ?",
                new String[] { "true" });
    }

    public void updateWeight(Integer AYS_W, Integer AYB_W,Integer LT_W,  Integer CSO_W, Integer HBP_W,Integer WF_W) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AYS_WCOL, AYS_W);
        values.put(AYB_WCOL, AYB_W);
        values.put(LT_WCOL, LT_W);
        values.put(CSO_WCOL, CSO_W);
        values.put(HBP_WCOL, HBP_W);
        values.put(WF_WCOL, WF_W);

        String where = "rowid=(SELECT MIN(rowid) FROM " + WeightTABLE_NAME + ")";
        db.update("weights",values,where,null);

    }

    public void deletejob(String[] job_ID) {


        SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME, ID_COL+"=?", job_ID);
        db.close();
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WeightTABLE_NAME);
        onCreate(db);
    }
}

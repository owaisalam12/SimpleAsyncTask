package com.example.oalam.simpleasynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.TextView;

import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void,Integer,String> {
    TextView mTextView;
    ProgressDialog progressDialog;
    Context ctx;
    @Override
    protected void onPreExecute() {
       //
        progressDialog=new ProgressDialog(ctx);
        progressDialog.setTitle("Napping..");
        progressDialog.setMessage("Please Wait..");
        progressDialog.setMax(10);
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
//        progressDialog.setButton(progressDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.cancel();
//            }
//        });
    progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... voids) {
// Generate a random number between 0 and 10
        Random random=new Random();
        int n= random.nextInt(11);

        // Make the task take long enough that we have
        // time to rotate the phone while it is running
        int s = n * 200;
       //
        // Sleep for the random amount of time
        try {
            Thread.sleep(s);
            publishProgress(s);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Return a String result
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onPostExecute(String s) {
        mTextView.setText(s);
    }

    public SimpleAsyncTask(TextView tv) {
    mTextView=tv;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

        progressDialog.setProgress(values[0]);
    }
}

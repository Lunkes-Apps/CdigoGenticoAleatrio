package com.lunkes.codigogeneticoaleatorio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private Button btn;
    private Button btn2;

    private static final int[] codes = new int[]{
            R.drawable.jli_u,
            R.drawable.jli_c,
            R.drawable.jli_a,
            R.drawable.jli_g
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        btn = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);

        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);

        boolean run = true;
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();


    }

    private class RandonGeneticCodes extends AsyncTask<String, Integer, String> {
        private static final String TAG = "RandonGeneticCodes";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            btn.setEnabled(false);
            btn2.setEnabled(false);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: parameter is " + s);
            btn.setEnabled(true);
            btn2.setEnabled(true);
        }

        @Override
        protected String doInBackground(String... strings) {

            Log.d(TAG, "doInBackground: start with " + strings[0]);
            int stage = 0;
            boolean run = true;
            long start = System.currentTimeMillis();
            long end = System.currentTimeMillis();
            while (run) {
                try {
                    Thread.sleep(75);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(stage);
                if (end - start >= 1000) {
                    start = System.currentTimeMillis();
                    stage++;
                }

                if (stage == 4) {
                    run = false;
                }
                end = System.currentTimeMillis();
//                Log.d("time",String.valueOf(end - start));
            }


            return "doInBackground completed.";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            switch (values[0]) {
                case 0:
                    changeImg(img1, codes[randonNumber()]);
                    break;
                case 1:
                    changeImg(img2, codes[randonNumber()]);
                    break;
                case 2:
                    changeImg(img3, codes[randonNumber()]);
                    break;

                default:
                    break;
            }

        }


    }

    private void changeImg(ImageView img, int drawIndex) {
        img.setImageDrawable(getResources().getDrawable(drawIndex));
    }

    private int randonNumber() {
        return 0 + (int) (Math.random() * (4));
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button:
                RandonGeneticCodes randonGeneticCodes = new RandonGeneticCodes();
                randonGeneticCodes.execute("execute randon codes");
                break;
            case R.id.button2:
                changeImg(img1, R.drawable.jli_16);
                changeImg(img2, R.drawable.jli_16);
                changeImg(img3, R.drawable.jli_16);
        }


    }
}

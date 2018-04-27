package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.android.example.jokepresentationlib.JokeDisplayActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.AsyncResponse {

    private String joke;

    private RelativeLayout mainPanel;
    private ProgressBar mProgressBar;

    private EndpointsAsyncTask.AsyncResponse asyncResponse = null;

    private InterstitialAd mInterstitialAd;

    private boolean openDetailActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPanel = (RelativeLayout) findViewById(R.id.main_panel);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        asyncResponse = this;

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());

                if(jokeRetrieved()) {
                    openDetailActivity();
                } else {
                    openDetailActivity = true;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void tellJoke(View view) {

        joke = null;
        openDetailActivity = false;

        new EndpointsAsyncTask(mainPanel, mProgressBar, this).execute();

        showInterstitialAd();

    }

    @Override
    public void asyncTaskFinished(String result) {
        this.joke = result;
        if(openDetailActivity) {
            openDetailActivity();
        }
    }

    private void showInterstitialAd() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    private void openDetailActivity(){
        Intent intent = new Intent(this, JokeDisplayActivity.class);
        intent.putExtra("joke", joke);

        startActivity(intent);
    }

    private boolean jokeRetrieved() {
        return joke != null;
    }

}

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


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.AsyncResponse {

    private String joke;

    private RelativeLayout mainPanel;
    private ProgressBar mProgressBar;

    private EndpointsAsyncTask.AsyncResponse asyncResponse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPanel = (RelativeLayout) findViewById(R.id.main_panel);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        asyncResponse = this;

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

        new EndpointsAsyncTask(mainPanel, mProgressBar, this).execute();

    }

    @Override
    public void asyncTaskFinished(String result) {
        this.joke = result;
        openDetailActivity();
    }

    private void openDetailActivity(){
        Intent intent = new Intent(this, JokeDisplayActivity.class);
        intent.putExtra("joke", joke);

        startActivity(intent);
    }
}

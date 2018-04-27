package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Context , Void, String> {

    private static final String FAILED_TO_CONNECT = "Failed to connect to local server.";

    public interface AsyncResponse {
        void asyncTaskFinished(String ressult);
    }

    private static MyApi myApiService = null;

    private RelativeLayout mainPanel;
    private ProgressBar mProgressBar;

    private AsyncResponse asyncResponse = null;

    public EndpointsAsyncTask() {
        // No Arguments.
    }

    public EndpointsAsyncTask(RelativeLayout mainPanel, ProgressBar progressBar, AsyncResponse asyncResponse ) {
        this.mainPanel = mainPanel;
        this.mProgressBar = progressBar;
        this.asyncResponse = asyncResponse;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if((mProgressBar != null) && (mainPanel != null)) {
            mProgressBar.setVisibility(View.VISIBLE);
            mainPanel.setVisibility(View.GONE);
        }

    }

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.showJoke().execute().getData();
        } catch (IOException e) {
            return FAILED_TO_CONNECT;
        }
    }

    @Override
    protected void onPostExecute(String result) {

        if((mProgressBar != null) && (mainPanel != null)) {
            mProgressBar.setVisibility(View.GONE);
            mainPanel.setVisibility(View.VISIBLE);
        }

        if (asyncResponse != null) {
            asyncResponse.asyncTaskFinished(result);
        }

    }

}

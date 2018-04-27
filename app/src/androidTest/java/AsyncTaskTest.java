import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;


@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {

    private static final String FAILED_TO_CONNECT = "Failed to connect to local server.";

    @Test
    public void test() {

        String result = null;

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.execute();
        try {
            result = endpointsAsyncTask.get();
            Log.d(" hfjj ", "Retrieved a non-empty string successfully: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(result);
        assertNotEquals(FAILED_TO_CONNECT, result);
    }

}

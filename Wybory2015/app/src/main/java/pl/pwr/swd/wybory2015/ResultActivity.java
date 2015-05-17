package pl.pwr.swd.wybory2015;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Syron on 2015-05-17.
 */
public class ResultActivity extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_result);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

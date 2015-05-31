package pl.pwr.swd.wybory2015;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Syron on 2015-05-17.
 */
public class ResultActivity extends Activity {

    TextView mUiDescr;
    ImageView mUiPicture;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        Candidate[] temp = (Candidate[])intent.getSerializableExtra(QuestionsActivity.intentExtras);
        //Candidate[] temp = QuestionsActivity.result;
        mUiDescr = (TextView)findViewById(R.id.result_description);
        mUiPicture = (ImageView)findViewById(R.id.result_image);
        if(temp.length == 0){
                mUiDescr.setText("Niestety, żaden z kandydatów nie pasuje twoim preferencjom.");
            mUiPicture.setImageResource(R.drawable.no_results);
        }else if(temp.length > 1){
            String kandydaci ="";
            for(int i = 0; i< temp.length ; i++){
                kandydaci += "\n" + temp[i].fullName;
            }
            mUiDescr.setText("Twoim preferencjom odpowiadają:"+kandydaci);
            mUiPicture.setImageResource(R.drawable.multiple_results);
        }else{
            mUiDescr.setText("Gratulacje! Idealnym kandydatem dla Ciebie jest \n"+temp[0].fullName);
            mUiPicture.setImageResource(temp[0].imageRes);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

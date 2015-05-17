package pl.pwr.swd.wybory2015;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


public class QuestionsActivity extends Activity {
    TextView mUiYes;
    TextView mUiNo;
    TextView mUiSkip;
    ProgressBar mUiProgress;
    TextView mUiQuestion;
    int currentIndex;
    Question[] questionDB = new Question[]{new Question("Tak czy nie"), new Question("Moze byc na cztery?"), new Question("Czy Piotrek Fracek to spoko ziomek"), new Question("Jak bardzo lubisz SWD")};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentIndex = 0;

        setContentView(R.layout.activity_questions);
        mUiYes = (TextView)findViewById(R.id.questions_yes);
        mUiNo = (TextView)findViewById(R.id.questions_no);
        mUiSkip = (TextView)findViewById(R.id.questions_skip);
        mUiQuestion= (TextView)findViewById(R.id.questions_question);
        mUiProgress = (ProgressBar)findViewById(R.id.questions_progressbar);
        mUiProgress.setMax(questionDB.length - 1);
        //pierwsze pytanie
        mUiProgress.setProgress(currentIndex);
        mUiQuestion.setText(questionDB[currentIndex].description);
        initListeners();
    }

    public void initListeners(){
        mUiYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion(QuestionAnswer.yes);
            }
        });
        mUiNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion(QuestionAnswer.no);
            }
        });
        mUiSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion(QuestionAnswer.skip);

            }
        });

    }

    public void nextQuestion(QuestionAnswer state){
        questionDB[currentIndex].setState(state);
        if (isLast()) {
            Intent intent = new Intent(QuestionsActivity.this, ResultActivity.class);
            startActivity(intent);
            finish();
        }else{
            currentIndex++;
            mUiProgress.setProgress(currentIndex);
            mUiQuestion.setText(questionDB[currentIndex].description);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public boolean isLast(){
        return currentIndex == (questionDB.length - 1);
    }
}

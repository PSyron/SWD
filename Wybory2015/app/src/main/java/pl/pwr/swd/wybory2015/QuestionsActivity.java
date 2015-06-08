package pl.pwr.swd.wybory2015;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class QuestionsActivity extends Activity {
    TextView mUiYes;
    TextView mUiNo;
    TextView mUiSkip;
    ProgressBar mUiProgress;
    TextView mUiQuestion;
    int currentIndex;
    Question[] questionDB = new Question[]{new Question("Tak czy nie"), new Question("Moze byc na cztery?"), new Question("Czy Piotrek Fracek to spoko ziomek"), new Question("Jak bardzo lubisz SWD")};
    Candidate mKorwin = new Candidate("Janusz Korwin-Mikke", new QuestionAnswer[]{QuestionAnswer.no, QuestionAnswer.yes, QuestionAnswer.skip, QuestionAnswer.skip}, R.drawable.korwin);
    Candidate mKukiz = new Candidate("Paweł Kukiz", new QuestionAnswer[]{QuestionAnswer.yes, QuestionAnswer.yes, QuestionAnswer.skip, QuestionAnswer.skip}, R.drawable.skukiz);
    Candidate[] candidates = new Candidate[]{mKorwin,mKukiz};


   public static Candidate[] result;

    public static final String intentExtras = "resultArray";


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
            intent.putExtra(intentExtras, algorytm());

            startActivity(intent);
            finish();
        }else{
            currentIndex++;
            mUiProgress.setProgress(currentIndex);
            mUiQuestion.setText(questionDB[currentIndex].description);
        }
    }

    public Candidate[] algorytm(){
         List<Candidate> result2 = new ArrayList<Candidate>();
        for(Candidate temp : candidates){
            boolean isFitting = true;
            for(int i = 0 ; i< questionDB.length ; i++){
                if(temp.answears[i] != questionDB[i].state  && questionDB[i].state != QuestionAnswer.skip){
                    isFitting = false;
                }
            }
            if(isFitting)
                result2.add(temp);
        }
        Candidate[] temp2 = new Candidate[result2.size()];
        for(int j = 0 ; j< temp2.length ; j++){
            temp2[j] = result2.get(j);
        }
        return temp2;
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

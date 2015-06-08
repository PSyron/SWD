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
    Question[] questionDB = new Question[]{
      new Question("Czy należy zlikwidować senat?"),
      new Question("Czy Polska powinna zacieśniać integrację gospodarczą z państwami UE?"),
      new Question("Czy powinniśmy w większym stopniu wspierać państwa uboższe?"),
      new Question("Czy jesteś za zasadniczą służbą wojskową?"),
      new Question("Czy potrzebujemy stałej obecności sił NATO na terenie kraju?"),
      new Question("Czy In Vitro powinno być zakazane?"),
      new Question("Czy należy przywrócić wiek emerytalny sprzed reformy z roku 2011?"),
      new Question("Czy Polska powinna wejść do strefy EURO?"),
      new Question("Czy jesteś za dotowaniem odnawialnych źródeł energii ze środków publicznych?"),
      new Question("Czy powinny zostać zalegalizowane związki partnerskie jedno oraz dwupłciowe?")
    };

    Candidate mKomorowski = new Candidate(
      "Bronisław Komorowski",
      new QuestionAnswer[]{
        QuestionAnswer.yes,
        QuestionAnswer.yes,
        QuestionAnswer.yes,
        QuestionAnswer.no,
        QuestionAnswer.yes,
        QuestionAnswer.no,
        QuestionAnswer.no,
        QuestionAnswer.yes,
        QuestionAnswer.no,
        QuestionAnswer.no
      }, R.drawable.komorowski);

    Candidate mKorwin = new Candidate(
      "Janusz Korwin-Mikke",
      new QuestionAnswer[]{
        QuestionAnswer.no,
        QuestionAnswer.no,
        QuestionAnswer.no,
        QuestionAnswer.no,
        QuestionAnswer.no,
        QuestionAnswer.no,
        QuestionAnswer.yes,
        QuestionAnswer.no,
        QuestionAnswer.no,
        QuestionAnswer.no,
      }, R.drawable.korwin);

      Candidate mDuda = new Candidate(
        "Andrzej Duda",
        new QuestionAnswer[]{
          QuestionAnswer.no,
          QuestionAnswer.no,
          QuestionAnswer.yes,
          QuestionAnswer.no,
          QuestionAnswer.yes,
          QuestionAnswer.yes,
          QuestionAnswer.yes,
          QuestionAnswer.no,
          QuestionAnswer.yes,
          QuestionAnswer.no
        }, R.drawable.duda);

    Candidate mKukiz = new Candidate(
      "Paweł Kukiz",
      new QuestionAnswer[]{
        QuestionAnswer.yes,
        QuestionAnswer.yes,
        QuestionAnswer.no,
        QuestionAnswer.no,
        QuestionAnswer.no,
        QuestionAnswer.no,
        QuestionAnswer.yes,
        QuestionAnswer.no,
        QuestionAnswer.no,
        QuestionAnswer.yes
      }, R.drawable.kukiz);

      Candidate mBraun = new Candidate(
        "Grzegorz Braun",
        new QuestionAnswer[]{
          QuestionAnswer.no,
          QuestionAnswer.no,
          QuestionAnswer.no,
          QuestionAnswer.yes,
          QuestionAnswer.no,
          QuestionAnswer.yes,
          QuestionAnswer.yes,
          QuestionAnswer.no,
          QuestionAnswer.no,
          QuestionAnswer.no
        }, R.drawable.braun);

    Candidate[] candidates = new Candidate[]{mKomorowski,mKorwin,mDuda,mKukiz,mBraun};


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
        //Lista do tablicy
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

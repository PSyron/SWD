package pl.pwr.swd.wybory2015;

/**
 * Created by Syron on 2015-05-17.
 */
public class Question {

    public String getDescription() {
        return description;
    }

    public QuestionAnswer getState() {
        return state;
    }

    public void setState(QuestionAnswer state) {
        this.state = state;
    }

    QuestionAnswer state;
    String description;

    public Question(String desc){
        description = desc;
        state = QuestionAnswer.skip;
    }
}

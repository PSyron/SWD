package pl.pwr.swd.wybory2015;

import java.io.Serializable;

/**
 * Created by Syron on 2015-05-31.
 */
public class Candidate implements Serializable{
    String fullName;
    QuestionAnswer[] answears;
    int imageRes;

    public Candidate(String name , QuestionAnswer[] array, int res){
        fullName = name;
        answears = array;
        imageRes = res;
    }
}

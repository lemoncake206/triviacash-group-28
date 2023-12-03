package test;

import data_access.SelectModeDataAccessObject;
import entity.Question;
import org.junit.Before;
import use_case.select_mode.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SelectModeDAOTest {

    ArrayList<Question> actualQuestions;

    @Before
    public void init(){
        String category = "Books";
        String difficultyLevel = "Hard";
        int numOfQuestions = 5;
        SelectModeDataObjectInterface selectModeDataAccessObject = new SelectModeDataAccessObject();
        this.actualQuestions =
                selectModeDataAccessObject.getQuestions(category, difficultyLevel, numOfQuestions);
    }

    @org.junit.Test
    public void getCategory(){
        for (Question q: actualQuestions){
            assertEquals("Entertainment: Books", q.getCategory());
        }
        assertEquals(5, actualQuestions.size());
    }

    @org.junit.Test
    public void getDifficultyLevel(){
        for (Question q: actualQuestions){
            assertEquals("Hard", q.getDifficultyLevel());
        }
    }

    @org.junit.Test
    public void sizeTest(){
        for (Question q: actualQuestions){
            assertEquals(4, q.getAnswerPackage().getPossibleAnswers().size());
        }
        assertEquals(5, actualQuestions.size());

    }
}

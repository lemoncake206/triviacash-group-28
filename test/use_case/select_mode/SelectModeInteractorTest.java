package use_case.select_mode;

import data_access.InMemorySelectModeAccessObject;
import entity.CommonQuestionStorage;
import entity.Question;
import entity.QuestionStorage;

import java.util.ArrayList;


import static org.junit.Assert.*;

/// Test case for SelectModeInteractor

public class SelectModeInteractorTest {

    @org.junit.Test
    public void successTest(){
        SelectModeInputData inputData = new SelectModeInputData("Animals", "Easy", "2");
        SelectModeDataObjectInterface selectModeDatabase = new InMemorySelectModeAccessObject();
        QuestionStorage questionStorage = new CommonQuestionStorage();

        SelectModeOutputBoundary successPresenter = new SelectModeOutputBoundary() {
            @Override
            public void prepareSelectModeSuccessView(SelectModeOutputData selectModeOutputData) {
                ArrayList<Question> actualQuestions = selectModeOutputData.getOutputQuestions();
                for (Question q: actualQuestions){
                    assertEquals("Dog or Cat?", q.getContent());
                    assertEquals("Animals", q.getCategory());
                    assertEquals("Easy", q.getDifficultyLevel());
                }
                assertEquals(2, actualQuestions.size());
            }

            @Override
            public void prepareSelectModeFailView(String error) {
                fail("Select mode use case failure is unexpected");
            }
        };
        SelectModeInputBoundary selectModeInputInteractor = new SelectModeInteractor(selectModeDatabase, successPresenter, questionStorage);
        selectModeInputInteractor.execute(inputData);
    }
}
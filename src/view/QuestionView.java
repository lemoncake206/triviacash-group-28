package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adaptors.question.CreateGameOverController;
import interface_adaptors.question.QuestionController;
import interface_adaptors.question.QuestionState;
import interface_adaptors.question.QuestionViewModel;

public class QuestionView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "question";

    private final QuestionViewModel questionViewModel;
    private final QuestionController questionController;
    private final CreateGameOverController createGameOverController;

    private final JLabel question;
    private final JButton answer1;
    private final JButton answer2;
    private final JButton answer3;
    private final JButton answer4;

    public QuestionView(QuestionViewModel questionViewModel, QuestionController controller,
                        CreateGameOverController createGameOverController) {

        this.questionController = controller;
        this.questionViewModel = questionViewModel;
        this.createGameOverController = createGameOverController;

        questionViewModel.addPropertyChangeListener(this);

        this.question = new JLabel(questionViewModel.QUESTION_TITLE_LABEL);
        question.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();

        answer1 = new JButton(questionViewModel.FIRST_ANSWER_BUTTON_LABEL);
        buttons.add(answer1);
        answer2 = new JButton(questionViewModel.SECOND_ANSWER_BUTTON_LABEL);
        buttons.add(answer2);
        answer3 = new JButton(questionViewModel.THIRD_ANSWER_BUTTON_LABEL);
        buttons.add(answer3);
        answer4 = new JButton(questionViewModel.FOURTH_ANSWER_BUTTON_LABEL);
        buttons.add(answer4);

        answer1.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        QuestionState currentState = questionViewModel.getState();
                        questionController.execute(answer1.getText(), currentState.getQuestionNum());
                        rightOrWrong();
                    }
                }
        );

        answer2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        QuestionState currentState = questionViewModel.getState();
                        questionController.execute(answer2.getText(), currentState.getQuestionNum());
                        rightOrWrong();
                    }
                }
        );

        answer3.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        QuestionState currentState = questionViewModel.getState();
                        questionController.execute(answer3.getText(), currentState.getQuestionNum());
                        rightOrWrong();
                    }
                }
        );

        answer4.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        QuestionState currentState = questionViewModel.getState();
                        questionController.execute(answer4.getText(), currentState.getQuestionNum());
                        rightOrWrong();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(question);
        this.add(buttons);
    }

    public void updateView() {
        question.setText(questionViewModel.QUESTION_TITLE_LABEL);
        answer1.setText(questionViewModel.FIRST_ANSWER_BUTTON_LABEL);
        answer2.setText(questionViewModel.SECOND_ANSWER_BUTTON_LABEL);
        answer3.setText(questionViewModel.THIRD_ANSWER_BUTTON_LABEL);
        answer4.setText(questionViewModel.FOURTH_ANSWER_BUTTON_LABEL);
    }

    public void actionPerformed(ActionEvent evt) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) { updateView(); }

    public void rightOrWrong() {
        QuestionState currentState = questionViewModel.getState();
        currentState.setQuestionNum(currentState.getQuestionNum() + 1);
        if (currentState.getQuestionCorrect()) {
            JOptionPane.showMessageDialog(this, "That answer is correct!");
            currentState.setQuestionsCorrect(currentState.getQuestionsCorrect() + 1);
        } else {
            JOptionPane.showMessageDialog(this, "That answer is incorrect!");
        }
        //updateView();
        currentState.setQuestionCorrect(null);
        if (currentState.getTotalQuestions() == currentState.getQuestionNum()) {
            gameOver();
        }
    }

    public void gameOver() {
        QuestionState inputState = questionViewModel.getState();
        QuestionState newState = new QuestionState();
        questionViewModel.setState(newState);
        questionViewModel.resetViewModel();
        createGameOverController.execute(inputState);
    }
}
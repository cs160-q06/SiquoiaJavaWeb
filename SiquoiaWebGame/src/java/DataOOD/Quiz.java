/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataOOD;

import java.util.List;
import java.util.Random;

/**
 *
 * @author mr.nam
 */
public class Quiz {
    private  List<Question> questionList;
    private int currentQuestionIndex;
    public Quiz(List<Question> questionList) {
        this.questionList = questionList;
        currentQuestionIndex = 0;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public boolean hasNext() {
        return currentQuestionIndex<questionList.size()-1;
    }

    public Question getCurrentQuestion() {
        return questionList.get(currentQuestionIndex);
    }
    public Question getCurrentQuestionRandomShuffle() {
        Question question = questionList.get(currentQuestionIndex);
        String[] set= {question.getCorrectAnswer(), question.getAnswer1()
                , question.getAnswer2() ,question.getAnswer3()};
        shuffleArray(set);
        question = new Question(question.getId(), question.getTopic()
                ,question.getQuestion(),set[0] ,set[1], set[2],set[3], question.getRanking());
        return question;
    }
    public void next() {
        currentQuestionIndex++;
    }

    
    public boolean isCurrentCorrect(String answer) {
        return getCurrentQuestion().getCorrectAnswer().toLowerCase().equals(answer.toLowerCase());
    }
    
    // Implementing Fisher–Yates shuffle. From http://stackoverflow.com/

    private void shuffleArray(String [] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    

    
    
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataOOD;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author mr.nam
 */
public class Quiz {
    private  List<Question> questionList;
    private int currentQuestionIndex;
    private List<Question> correctQuestionList;
    public Quiz(List<Question> questionList) {
        this.correctQuestionList = questionList;
        this.questionList = this.suffleAllAnswer(questionList);
        currentQuestionIndex = 0;
    }

    public int getCurrentNumber() {
        return currentQuestionIndex + 1;
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
        Question next = new Question(question.getId(), question.getTopicID()
                ,question.getQuestion(),set[0] ,set[1], set[2],set[3], question.getRanking(),question.getMedia());
        return next;
    }
    public void next() {
        currentQuestionIndex++;
    }

    
    public boolean isCurrentCorrect(String answer) {
        return correctQuestionList.get(currentQuestionIndex).getCorrectAnswer().toLowerCase().equals(answer.toLowerCase());
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

    private List<Question> suffleAllAnswer(List<Question> questionList) {
        List<Question> list = new ArrayList<>();
        for(Question q : questionList)
        {
            String[] set= {q.getCorrectAnswer(), q.getAnswer1()
                , q.getAnswer2() ,q.getAnswer3()};
        shuffleArray(set);
        Question next = new Question(q.getId(), q.getTopicID()
                ,q.getQuestion(),set[0] ,set[1], set[2],set[3], q.getRanking(), q.getMedia());
        list.add(next);
        }
        return list;
    }

    

    
    
    
    
}

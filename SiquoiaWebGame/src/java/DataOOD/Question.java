/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataOOD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mr.nam
 */
public class Question {

    

    

    private int id;
    private Topic topic;
    private String correctAnswer;
    private String answer1;
    private String answer2;
    private String answer3;
    private int ranking;
    private String question;
    private int topicID;
    private int media;

    public Question(int id, Topic topic, String question, String correctAnswer, String answer1, String answer2, String answer3, int ranking) {
        this.id = id;
        this.topic = topic;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.ranking = ranking;
    }

    public Question(int id, int topicID, String question, String correctAnswer, String answer1, String answer2, String answer3, int ranking, int media) {
        this.id = id;
        this.topicID = topicID;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.ranking = ranking;
        this.media = media;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public int getRanking() {
        return ranking;
    }

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }

    public void incrementRanking() {
        ranking++;
    }

    public static List<Question> doQueryGetAll(Connection conn) throws SQLException {
        String query = "SELECT * from QUESTION;";
        return doQuery(conn, query);
    }
    public static List<Question> doQueryByTopic(Connection conn, Topic t) throws SQLException {
        List<Topic> topicList = Topic.getTopicAndAllSubTopicByID(conn,t.getId());
        List<Question> list = new ArrayList<>();
        for(Topic n : topicList)
        {
            String query = "SELECT * from QUESTION where TOPIC_ID = " + n.getId();
            list.addAll(doQuery(conn,query));
        }
        return list;
    }
    public static List<Question> doQuery(Connection conn, String query) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<Question> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("ID");
            int topicID = rs.getInt("TOPIC_ID");
            String question = rs.getString("DESCRIPTION");
            String correctAnswer = rs.getString("ANSWER_C");
            String answer1 = rs.getString("ANSWER_W1");
            String answer2 = rs.getString("ANSWER_W2");
            String answer3 = rs.getString("ANSWER_W3");
            int ranking = rs.getInt("RANKING");
            int media = rs.getInt("MULTIMEDIA_ID");
            if(rs.wasNull()) media = -1;

            Question i = new Question(id, topicID, question, correctAnswer, answer1, answer2, answer3, ranking, media);
            list.add(i);
        }
        return list;
    }
    @Override
    public String toString() {
        String s = "Question #" + id + " (" + topicID+ "): ";
        s += question + "\n\tc: " + correctAnswer + "\n\ta1: " + answer1
                + "\n\ta2: " + answer2 + "\n\ta3: " + answer3;
        return s; //To change body of generated methods, choose Tools | Templates.
    }
    
}

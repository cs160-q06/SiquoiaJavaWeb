/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
public class QuizHistory {

    private int id;
    private int userID;
    private int topicID;
    private String questionList;
    private int current_questionID;
    private int point;

    public QuizHistory(int id, int userID, int topicID, String questionList,
            int current_questionID, int point) {
        this.id = id;
        this.userID = userID;
        this.topicID = topicID;
        this.questionList = questionList;
        this.current_questionID = current_questionID;
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public int getTopicID() {
        return topicID;
    }

    public String getQuestionList() {
        return questionList;
    }

    public int getCurrent_questionID() {
        return current_questionID;
    }

    public int getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return "[id: " + id + "|" + userID + "|" + topicID + "|[" + questionList
                + "]|currrent: " + current_questionID + "|point: " + point + "]";
    }

    /**
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    public static List<QuizHistory> doQueryGetAll(Connection conn) throws SQLException {
        String query = "Select * from QUIZ_HISTORY";
        return doQuery(conn, query);
    }

    /**
     *
     * @param conn
     * @param query
     * @return
     * @throws SQLException
     */
    private static List<QuizHistory> doQuery(Connection conn, String query) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<QuizHistory> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("ID");
            int topicID = rs.getInt("TOPIC_ID");
            String questionList = rs.getString("QUESTIONLIST");
            int current_questionID = rs.getInt("CURRENT_QUESTIONID");
            int point = rs.getInt("POINT");
            QuizHistory i = new QuizHistory(id, topicID, topicID, questionList, current_questionID, point);
            list.add(i);
        }
        return list;
    }

    public static boolean doQueryInsert(Connection conn, QuizHistory q) throws SQLException {
        String query = "INSERT INTO QUIZ_HISTORY\n"
                + "(User_ID,\n"
                + "Topic_ID,\n"
                + "QuestionList,\n"
                + "Current_QuestionID,\n"
                + "Point)\n"
                + "VALUES\n"
                + "(\n"
                + q.getUserID() + ",\n"
                + q.getTopicID() + ",\n"
                + "'" + q.getQuestionList() + "',\n"
                + q.getCurrent_questionID() + ",\n"
                + q.getPoint() + "\n"
                + ");";
        return doQueryExecuteUpdate(conn, query);
    }

    private static boolean doQueryExecuteUpdate(Connection conn, String query) throws SQLException {

        Statement stmt = conn.createStatement();
        System.out.println("Insert/Update into QuizHistory table");
        stmt.executeUpdate(query);
        stmt.close();

        return true;
    }
}

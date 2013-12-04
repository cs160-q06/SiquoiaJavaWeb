/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Controller.Controller;
import DataOOD.Question;
import DataOOD.Topic;
import DataOOD.User;
import Database.MySqlController;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mr.nam
 */
public class Test_DB {

    public static void main(String[] args) {
        MySqlController ctrl = new MySqlController();
        try {
            Connection conn = ctrl.connect();
            //test_Topic(conn);
            //test_Question(conn);
            test_User(conn);            
            //test_QuestionOfTopic(conn);
            //testLog_In(conn);
            //test_addUser(conn);
            //test_Quiz_QuestionHas1C3W(conn);
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void test_Question(Connection conn) throws SQLException {
        System.out.println("Question Table:");

        List<Question> list = Question.doQueryGetAll(conn);
        for (Question q : list) {
            System.out.println(q.toString());
        }
        System.out.println("Total: " + list.size());
    }

    private static void test_Topic(Connection conn) throws SQLException {
        System.out.println("Topic Table:");
        List<Topic> list = Topic.doQueryGetAll(conn);
        for (Topic q : list) {
            System.out.println(q.toString());
        }
        System.out.println("Total: " + list.size());

    }

    private static void test_User(Connection conn) throws SQLException {
        System.out.println("User User:");
        List<User> list = User.doQueryGetAll(conn);
        for (User q : list) {
            System.out.println(q.toString());
        }
        System.out.println("Total: " + list.size());

    }

    private static void test_QuestionOfTopic(Connection conn) throws SQLException {
        List<Topic> listTopic;
        System.out.println("\nAll topic: ");
        listTopic = Topic.getTopicAndAllSubTopicByID(conn, 0);
        System.out.println(Arrays.toString(listTopic.toArray()));
        System.out.println(listTopic.size() - 1 + " subTopic under " + listTopic.get(0).getDescription());
        System.out.println("\nAll topic under id 1: ");
        listTopic = Topic.getTopicAndAllSubTopicByID(conn, 1);
        System.out.println(Arrays.toString(listTopic.toArray()));
        System.out.println(listTopic.size() - 1 + " subTopic under " + listTopic.get(0).getDescription());

        System.out.println("\nAll topic under id 2: ");
        listTopic = Topic.getTopicAndAllSubTopicByID(conn, 2);
        System.out.println(Arrays.toString(listTopic.toArray()));
        System.out.println(listTopic.size() - 1 + " subTopic under " + listTopic.get(0).getDescription());

        System.out.println("\nAll topic under id 3: ");
        listTopic = Topic.getTopicAndAllSubTopicByID(conn, 3);
        System.out.println(Arrays.toString(listTopic.toArray()));
        System.out.println(listTopic.size() - 1 + " subTopic under " + listTopic.get(0).getDescription());

        System.out.println("\nQuestion per topic");
        listTopic = Topic.doQueryGetAll(conn);
        int total = 0;
        for (Topic t : listTopic) {
            System.out.println(t.toString());
            List<Question> listQuestion = Question.doQueryByTopic(conn, t);
            System.out.println("\tThe topic has " + listQuestion.size()
                    + (listQuestion.size() > 1 ? " questions" : " question") + "\n");
        }

    }

    private static void testLog_In(Connection conn) throws SQLException {
        String user1, pass1, user2, pass2;
        user1 = "admin";
        pass1 = user1;
        user2 = "user1";
        pass2 = user2;
        printLogin(conn,user1,pass1);
        
        printLogin(conn, user2, pass2);

    }

    private static void printLogin(Connection conn, String name, String pass) throws SQLException {
        System.out.println("Try to log in with ["+name+","+pass+"]");
        User user = Controller.login(conn,name, pass);
        
        if (user != null) {
            System.out.println("Log in sucessfully!");
            System.out.println("Hello " + user.getName());
            System.out.println(user.toString());
        }
        else
            System.out.println("Log in failed!");
    }

    private static void test_addUser(Connection conn) throws SQLException {
        String user1, pass1, user2, pass2;
        user1 = "user3";
        pass1 = user1;
        user2 = "user1";
        pass2 = user2;
        Controller.registerUser(conn,user1,pass1);
        Controller.registerUser(conn,user2,pass2);
        
    }

    private static void test_Quiz_QuestionHas1C3W(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

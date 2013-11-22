/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import DataOOD.Question;
import DataOOD.Topic;
import DataOOD.User;
import Database.MySqlController;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
            test_Question(conn);
            //test_User(conn);
            
            
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
        System.out.println("User Table:");
        List<User> list = User.doQueryGetAll(conn);
        for (User q : list) {
            System.out.println(q.toString());
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Controller.Controller;
import DataOOD.Node;
import DataOOD.Question;
import DataOOD.Quiz;
import DataOOD.Topic;
import DataOOD.User;
import Database.MySqlController;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mr.nam
 */
public class Test_App {

    public static void main(String[] args) {
        MySqlController ctrl = new MySqlController();
        try {
            Connection conn = ctrl.connect();
            //test_SelectTopicAndSubTopic(conn);
            //test_ShoppingStore(conn);
            //testGenerateNodeTopic(conn);
            //test_generateQuiz();
            //test_login();
            test_getMediaByID();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void test_SelectTopicAndSubTopic(Connection conn) throws SQLException {
        String message = "-------Command-------\n"
                + "(n) next to subtopic\n"
                + "(s) start the quiz\n"
                + "(e) exit\n"
                + "--------------------\n";
        System.out.println(message);
        List<Topic> list = Topic.getSubTopicByID(conn, 0);
        Scanner sc;
        int n = -1;

        while (true) {
            for (int i = 1; i <= list.size(); i++) {
                System.out.println("(" + i + ")" + list.get(i - 1).getDescription());
            }
            System.out.print(">");
            //read command
            sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                if (n > list.size()) {
                    System.out.println(">Wrong choice");
                    System.out.println(message);
                    n = -1;
                } else {
                    System.out.println(">" + list.get(n - 1).getDescription() + " is selected");
                }
            } else {
                String s = sc.nextLine().trim();
                if (s.equalsIgnoreCase("n") && n > 0) {
                    System.out.println(">Next to subtopic of " + list.get(n - 1).getDescription() + "\n");
                    List<Topic> tmp = Topic.getSubTopicByID(conn, list.get(n - 1).getId());
                    if (tmp.isEmpty()) {
                        System.out.println(list.get(n - 1).getDescription() + " has no subtopic\n");
                    } else {
                        list = tmp;
                    }
                    n=-1;
                } else if (s.equalsIgnoreCase("s")) {
                    if (n > 0) {
                        System.out.println(">Start quiz of " + list.get(n - 1).getDescription());
                        break;

                    } else {
                        System.out.println(">Error: you need to choose a topic");
                    }
                } else if (s.equalsIgnoreCase("e")) {
                    System.out.println(">Exit");
                    break;
                } else {
                    System.out.println(">Wrong choice");

                    System.out.println(message);
                }
            }
        }
    }

    private static void test_ShoppingStore(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.s
    }

    private static void testGenerateNodeTopic(Connection conn) {
            Node<Topic> root = Controller.generateNodeTopic();
            System.out.println(root.toString());
    }

    private static void test_generateQuiz() {
        Node<Topic> root = Controller.generateNodeTopic();
        Quiz q = Controller.generateQuizFromTopic(root.getChildren().get(2).getData());
        q.next();
        System.out.println(q.getCurrentQuestion());
        System.out.println(q.getCurrentQuestionRandomShuffle());
        q.next();
        System.out.println(q.getCurrentQuestion());
        System.out.println(q.getCurrentQuestionRandomShuffle());
    }

    private static void test_login() {
        System.out.println(Controller.isLogin("user1", "user1",111));
    }

    private static void test_getMediaByID() {
        System.out.println(Controller.getMediaByID(1).toString());
    }
}

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
            test_SelectTopicAndSubTopic(conn);
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
}

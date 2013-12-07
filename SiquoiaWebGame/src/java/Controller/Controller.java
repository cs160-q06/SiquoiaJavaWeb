package Controller;

import DataOOD.Media;
import DataOOD.Node;
import DataOOD.Question;
import DataOOD.Quiz;
import DataOOD.Topic;
import DataOOD.User;
import Database.MySqlController;
import Miscellanea.EnumString;
import Miscellanea.EnumValue;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mr.nam
 */
public class Controller {

    private static Node root;
    private static ArrayList<Question> questionList;
    private static EnumString menuLevel;
    private static Connection conn = new MySqlController().connect();;
    private static List<User> loginUserList = new ArrayList<User>();
    private static List<Integer> ipList = new ArrayList<Integer>();
    
    public static List<Topic> getSubTopicByName(String name) {
        List<Topic> list = new ArrayList<>();
        try {
            list = Topic.getSubTopicByName(conn, name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    
    public static Topic getTopicParentByName(String name) {
        try {
            return Topic.getParentByName(conn,name);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Node<Topic> generateNodeTopic() {
        int countTopic = 0;
        Node<Topic> root = new Node(countTopic, new Topic(countTopic, "root"));
        try {
            List<Topic> list = Topic.doQueryGetAll(conn);
            for(Topic t : list)
            {
                Node<Topic> node = root.getChildNodeByID(t.getParent());
                node.addChild(new Node<Topic>(t.getId(),t));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return root;
    }

    public static Topic getTopicByName(String demo) {
        try {
            return Topic.doQuerySearchByName(conn, demo);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Media getMediaByID(int id) {
        try {
            return Media.doQueryByID(conn,id);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Controller() {
        this.conn = new MySqlController().connect();
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException, NamingException, SQLException {
        test();

    }
   
    public static Quiz generateQuizFromTopic(Topic topic)
    {
            List<Question> list = new ArrayList<>();
            List<Question> sublist = new ArrayList<>();
        try {
            list = Question.doQueryByTopic(conn, topic);
            while(sublist.size()<EnumValue.PACKET_QUESTION_NUMBER.getValue()
                    && !list.isEmpty())
            {
                int random = (int) (Math.random()*list.size());
                sublist.add(list.get(random));
                list.remove(random);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
            return new Quiz(sublist);
        
    }
    public static List<Topic> getSubTopicByID(int id) {
        List<Topic> list = new ArrayList<>();
        try {
            list = Topic.getSubTopicByID(conn, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

   
    public static boolean isLogin(String username, String pass, int ip) {
        List<User> list = new ArrayList<>();
        try {
            list = User.doQueryGetAll(conn);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(User u : list)
            if(u.getName().equals(username) && u.getPassword().equals(pass))
            {
                Controller.loginUserList.add(u);
                Controller.ipList.add(ip);
                return true;
            }
        return false;
    }

    public static User login(Connection conn, String name, String pass) throws SQLException {
        List<User> list = User.doQueryGetAll(conn);
        User user = null;
        for (User i : list) {
            if (i.getName().equals(name) && i.getPassword().equals(pass)) {
                user = i;
            }
        }
        return user;
    }

    public static boolean registerUser(Connection conn, String name, String pass) throws SQLException {
        if (User.doQueryAddUser(conn, name, pass)) {
            System.out.println("user " + name + " is successfully registered");
            return true;
        } else {
            System.out.println("user " + name + " is already registered");
            return true;
        }
    }

    private static void test() throws NamingException, SQLException {
       
        System.out.println(Controller.isLogin("user1", "user1", 111));
    }
}

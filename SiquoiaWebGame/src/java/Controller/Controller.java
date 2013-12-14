package Controller;

import DataOOD.Media;
import DataOOD.Node;
import DataOOD.PointHistory;
import DataOOD.Question;
import DataOOD.Quiz;
import DataOOD.Topic;
import DataOOD.User;
import Database.MySqlController;
import Miscellanea.EnumString;
import Miscellanea.EnumValue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Controller controls all request from web pages
 *
 * @author mr.nam
 */
public class Controller {

    private static Connection conn = new MySqlController().connect();
    private static List<User> loginUserList = new ArrayList<User>();
    private static List<Integer> ipList = new ArrayList<Integer>();

    /**
     * get children topics of a topic by its name
     *
     * @param name
     * @return
     */
    public static List<Topic> getSubTopicByName(String name) {
        List<Topic> list = new ArrayList<>();
        try {
            list = Topic.getSubTopicByName(conn, name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    /**
     * get parent topic of a topic by its name
     *
     * @param name
     * @return
     */
    public static Topic getTopicParentByName(String name) {
        try {
            return Topic.getParentByName(conn, name);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * generate a tree of Topic's Node
     *
     * @return
     */
    public static Node<Topic> generateNodeTopic() {
        int countTopic = 0;
        Node<Topic> root = new Node(countTopic, new Topic(countTopic, "root"));
        try {
            List<Topic> list = Topic.doQueryGetAll(conn);
            for (Topic t : list) {
                Node<Topic> node = root.getChildNodeByID(t.getParent());
                node.addChild(new Node<Topic>(t.getId(), t));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return root;
    }

    /**
     * get topic by its name
     *
     * @param demo
     * @return
     */
    public static Topic getTopicByName(String demo) {
        try {
            return Topic.doQuerySearchByName(conn, demo);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * get media by its ID
     *
     * @param id
     * @return
     */
    public static Media getMediaByID(int id) {
        try {
            return Media.doQueryByID(conn, id);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * generate the Quiz by topic
     *
     * @param topic
     * @return
     */
    public static Quiz generateQuizFromTopic(Topic topic) {
        List<Question> list = new ArrayList<>();
        List<Question> sublist = new ArrayList<>();
        try {
            list = Question.doQueryByTopic(conn, topic);
            while (sublist.size() < EnumValue.PACKET_QUESTION_NUMBER.getValue()
                    && !list.isEmpty()) {
                int random = (int) (Math.random() * list.size());
                sublist.add(list.get(random));
                list.remove(random);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Quiz(sublist);

    }

    /**
     * get children topics from a topic by its id
     *
     * @param id
     * @return
     */
    public static List<Topic> getSubTopicByID(int id) {
        List<Topic> list = new ArrayList<>();
        try {
            list = Topic.getSubTopicByID(conn, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    /**
     * return true if the username and password is in database
     *
     * @param username
     * @param pass
     * @param ip
     * @return
     */
    public static boolean isLogin(String username, String pass, int ip) {
        List<User> list = new ArrayList<>();
        try {
            list = User.doQueryGetAll(conn);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (User u : list) {
            if (u.getName().equals(username) && u.getPassword().equals(User.hashMD5(pass))) {
                Controller.loginUserList.add(u);
                Controller.ipList.add(ip);
                return true;
            }
        }
        return false;
    }

    /**
     * get user by name and password
     *
     * @param conn
     * @param name
     * @param pass
     * @return
     * @throws SQLException
     */
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

    /**
     * add user to database
     *
     * @param name
     * @param pass
     * @return
     * @throws SQLException
     */
    public static boolean registerUser(String name, String pass) throws SQLException {
        if (User.doQueryAddUser(conn, name, User.hashMD5(pass))) {
            System.out.println("user " + name + " is successfully registered");
            return true;
        } else {
            System.out.println("user " + name + " is already registered");
            return false;
        }
    }

    /**
     *
     * @return
     */
    public static User getLoginUser() {
        if (loginUserList.isEmpty()) {
            return null;
        }
        return loginUserList.get(0);
    }
/**
 * 
 * @param i
 * @return 
 */
    public static List<PointHistory> getPurchaseHistoryByUserID(int i) {
        List<PointHistory> list = new ArrayList<>();
        try {
            list = PointHistory.doQueryByID(conn, i);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
/**
 * 
 * @param name
 * @return 
 */
    public static boolean logOut(String name) {
        for (User u : loginUserList) {
            if (u.getName().equalsIgnoreCase(name)) {
                loginUserList.remove(u);
                return true;
            }
        }
        return false;
    }
/**
 * 
 * @param demo
 * @param dateTime 
 */
    public static void purchase(String demo, String dateTime) {
        User user = loginUserList.get(0);
        int price = EnumValue.PACKET_PRICE_BY_POINT.getValue();
        if (user.getPoint() > price) {
                        
            Topic topic = getTopicByName(demo);
            //insert purchasehistory by query
            PointHistory p = new PointHistory(0, dateTime, 0
                    , price, user.getId(), topic.getId());
            try {
                PointHistory.doQueryUpdatePurchase(conn, p);
            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            //update point of user by query
            user.setPoint(user.getPoint()-price);
            try {
                User.doQueryUpdateUser(conn,user);
            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

package Controller;

import DataOOD.Node;
import DataOOD.Question;
import DataOOD.Quiz;
import DataOOD.Topic;
import DataOOD.User;
import Database.MySqlController;
import Miscellanea.EnumString;
import Miscellanea.EnumValue;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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


    public static List<Topic> getSubTopicByName(String name) {
        List<Topic> list = new ArrayList<>();
        try {
            list = Topic.getSubTopicByName(conn, name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

   
    public static Topic getTopicParentByName(String name) throws SQLException {
        return Topic.getParentByName(conn,name);
    }

    public Controller() {
        this.conn = new MySqlController().connect();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, NamingException, SQLException {
        test();

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

    private static int addTopic(Node<Topic> currentNode, Node<Topic> previousNode, String line, int count) {
        Node root = previousNode;
        Scanner sc = new Scanner(line);
        sc.useDelimiter(">");

        while (sc.hasNext()) {
            String s = sc.next();
            List<Node<Topic>> children = currentNode.getChildren();
            boolean isNew = true;
            if (children != null) {
                for (Node<Topic> child : children) {
                    if (child.getData().getDescription().equals(s)) {
                        previousNode = child;
                        currentNode = child;
                        isNew = false;
                        //count = currentNode.getData().getId();
                        break;
                    }

                }
            }
            if (children == null || isNew) {
                count++;
                Topic topic = new Topic(count, s);
                previousNode = currentNode;
                currentNode = new Node(count, topic);
                currentNode.setParent(previousNode);
            }
        }
        // System.out.println(currentNode.toString());

        return count;
    }

    private static void loadData(String dataset) throws FileNotFoundException {
        System.out.println("--------------------------------");
        System.out.println("Data is loading from " + dataset);

        File file = new File(dataset);
        Scanner scLine = new Scanner(file);
        questionList = new ArrayList();
        int countTopic = 0;
        int countQuestion = 0;
        root = new Node(countTopic, new Topic(countTopic, "root"));
        Node<Topic> currentNode;
        Node<Topic> previousNode;
        boolean isTopic = true;
        while (scLine.hasNextLine()) {
            String line = scLine.nextLine().trim();
            if (isTopic) {
                currentNode = root;
                previousNode = root;
                countTopic = addTopic(currentNode, previousNode, line, countTopic);
                isTopic = false;
                //System.out.println(currentNode.toString());
            } else if (line.isEmpty()) {
                if (scLine.hasNextLine()) {
                    line = scLine.nextLine();
                    if (line.isEmpty()) {
                        isTopic = true;
                    } else {
                        countQuestion++;
                        String q = line;
                        String c = scLine.nextLine().trim();
                        String a1 = scLine.nextLine().trim();
                        String a2 = scLine.nextLine().trim();
                        String a3 = scLine.nextLine().trim();
                        Node<Topic> node = root.getChildByID(countTopic);
                        Question question = new Question(countQuestion, node.getData(), q, c, a1, a2, a3, 0);
                        questionList.add(question);
                    }
                }
            }

        }
        System.out.println("Data is loaded from " + dataset);
        System.out.println("--------------------------------");

    }

    private static void printQuestionList(ArrayList<Question> questionList) {
        for (Question q : questionList) {
            System.out.println(q.toString());
        }
    }

    private static void printMenu(String t) {
        String s = "";
        if (t.equals("main")) {
            s += "---Menu---\n";
            s += EnumString.LOGIN.getValue() + " (" + EnumString.LOGIN_C.getValue() + ")\n";
            s += EnumString.TUTORIAL.getValue() + " (" + EnumString.TUTORIAL_C.getValue() + ")\n";
            s += EnumString.OPTION.getValue() + " (" + EnumString.OPTION_C.getValue() + ")\n";
            s += EnumString.EXIT.getValue() + " (" + EnumString.EXIT_C.getValue() + ")\n";

        } else if (t.equals(EnumString.LOGIN_C.getValue())) {
            s += "---Menu > " + EnumString.LOGIN.getValue() + "---\n";
            s += EnumString.BACK.getValue() + " (" + EnumString.BACK_C.getValue() + ")\n";

            menuLevel = EnumString.LOGIN;

        } else if (t.equals(EnumString.TUTORIAL_C.getValue())) {
            s += "---Menu > " + EnumString.TUTORIAL.getValue() + "---\n";
            s += EnumString.START.getValue() + " (" + EnumString.START_C.getValue() + ")\n";

            s += EnumString.BACK.getValue() + " (" + EnumString.BACK_C.getValue() + ")\n";

            menuLevel = EnumString.TUTORIAL;

        } else if (t.equals(EnumString.OPTION_C.getValue())) {
            s += "---Menu > " + EnumString.OPTION.getValue() + "---\n";
            s += EnumString.BACK.getValue() + " (" + EnumString.BACK_C.getValue() + ")\n";

            menuLevel = EnumString.OPTION;
        } else { // wrong input, print the menu again
            if (menuLevel == EnumString.MAIN) {
                printMenu("main");
            } else {
                printMenu(menuLevel.getValue().substring(0, 1).toLowerCase());
            }
        }
        System.out.println(s);

    }

    private static void backMainMenu() {
        menuLevel = EnumString.MAIN;
        printMenu("");

    }

    private static void tutorial(Scanner scLine) {
        System.out.println("---Start Tutorial Quiz---");
        List<Question> list = new ArrayList();
        String topic = EnumString.TUTORIAL_TOPIC.getValue().toLowerCase();
        for (Question question : questionList) {
            if (question.getTopic().getDescription().toLowerCase().equals(topic)) {
                list.add(question);
            }
            if (list.size() == EnumValue.TUTORIAL_QUESTION_NUMBER.getValue()) {
                break;
            }
        }
        startQuiz(list, scLine);
    }

    private static void startQuiz(List<Question> questionList, Scanner scLine) {
        int count = 0;
        Quiz quiz = new Quiz(questionList);
        while (quiz.hasNext()) {
            count++;
            String answer = "";
            quiz.next();
            //Question question = quiz.getCurrentQuestion();
            Question question = quiz.getCurrentQuestionRandomShuffle();
            printQuestion(question, count);
            while (true) {
                System.out.print("Your choice: ");
                scLine = new Scanner(System.in);
                answer = scLine.nextLine().toLowerCase().trim();

                if (answer.equals(EnumString.A.getValue())) {
                    answer = question.getCorrectAnswer();
                    break;
                } else if (answer.equals(EnumString.B.getValue())) {
                    answer = question.getAnswer1();

                    break;
                } else if (answer.equals(EnumString.C.getValue())) {
                    answer = question.getAnswer2();

                    break;
                } else if (answer.equals(EnumString.D.getValue())) {
                    answer = question.getAnswer3();

                    break;
                } else {
                    System.out.println("WRONG INPUT!");
                }
            }

            if (quiz.isCurrentCorrect(answer)) {
                System.out.println("Your answer for #" + count + " is correct!\n");
            } else {
                System.out.println("Your answer for #" + count + " is incorrect!\n");

                count = (count > 0 ? count - 1 : count);
                System.out.println("You correctly answer total " + count
                        + (count < 2 ? "q uestion" : " questions"));
                break;
            }

        }
    }

    private static void printQuestion(Question question, int count) {
        String s = "Question #" + count + ": ";
        s += question.getQuestion() + "\n\t(" + EnumString.A.getValue() + ") " + question.getCorrectAnswer()
                + "\n\t(" + EnumString.B.getValue() + ") " + question.getAnswer1()
                + "\n\t(" + EnumString.C.getValue() + ") " + question.getAnswer2()
                + "\n\t(" + EnumString.D.getValue() + ") " + question.getAnswer3();
        System.out.println(s + "\n");
    }

    static void shuffleArray(String[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    private static void test_Create_SQLFile(String dataset1txt) throws FileNotFoundException, IOException {
        loadData(dataset1txt);
        File file = new File("SQLouput.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter output = new BufferedWriter(fw);
        String s = "";
        String topic;
        int id;
        //DMV
        topic = "DMV";
        id = 8;
        for (Question q : questionList) {
            //System.out.println(q.getDescription().getDescription());
            if (q.getTopic().getDescription().equals(topic)) {
                s += (s.isEmpty() ? "Select " : " UNION ALL Select ");
                s += id + ",'" + q.getQuestion().replaceAll("'", "''") + "'" + ",'"
                        + q.getCorrectAnswer().replaceAll("'", "''") + "'" + ",'"
                        + q.getAnswer1().replaceAll("'", "''") + "'" + ",'"
                        + q.getAnswer2().replaceAll("'", "''") + "'" + ",'"
                        + q.getAnswer3().replaceAll("'", "''") + "'" + ","
                        + 0
                        + "\n";

            }
        }
        //Capitals
        topic = "Capitals";
        id = 6;
        for (Question q : questionList) {
            //System.out.println(q.getDescription().getDescription());
            if (q.getTopic().getDescription().equals(topic)) {
                s += (s.isEmpty() ? "Select " : " UNION ALL Select ");
                s += id + ",'" + q.getQuestion().replaceAll("'", "''") + "'" + ",'"
                        + q.getCorrectAnswer().replaceAll("'", "''") + "'" + ",'"
                        + q.getAnswer1().replaceAll("'", "''") + "'" + ",'"
                        + q.getAnswer2().replaceAll("'", "''") + "'" + ",'"
                        + q.getAnswer3().replaceAll("'", "''") + "'" + ","
                        + 0
                        + "\n";

            }
        }
        //Arithmetic
        topic = "Arithmetic";
        id = 10;
        for (Question q : questionList) {
            //System.out.println(q.getDescription().getDescription());
            if (q.getTopic().getDescription().equals(topic)) {
                s += (s.isEmpty() ? "Select " : " UNION ALL Select ");
                s += id + ",'" + q.getQuestion().replaceAll("'", "''") + "'" + ",'"
                        + q.getCorrectAnswer().replaceAll("'", "''") + "'" + ",'"
                        + q.getAnswer1().replaceAll("'", "''") + "'" + ",'"
                        + q.getAnswer2().replaceAll("'", "''") + "'" + ",'"
                        + q.getAnswer3().replaceAll("'", "''") + "'" + ","
                        + 0
                        + "\n";

            }
        }
        //Algebra
        topic = "Algebra";
        id = 11;
        for (Question q : questionList) {
            //System.out.println(q.getDescription().getDescription());
            if (q.getTopic().getDescription().equals(topic)) {
                s += (s.isEmpty() ? "Select " : " UNION ALL Select ");
                s += id + ",'" + q.getQuestion().replaceAll("'", "''") + "'" + ",'"
                        + q.getCorrectAnswer().replaceAll("'", "''") + "'" + ",'"
                        + q.getAnswer1().replaceAll("'", "''") + "'" + ",'"
                        + q.getAnswer2().replaceAll("'", "''") + "'" + ",'"
                        + q.getAnswer3().replaceAll("'", "''") + "'" + ","
                        + 0
                        + "\n";

            }
        }
        //Botany
        topic = "Botany";
        id = 13;
        for (Question q : questionList) {
            //System.out.println(q.getDescription().getDescription());
            if (q.getTopic().getDescription().equals(topic)) {
                s += (s.isEmpty() ? "Select " : " UNION ALL Select ");
                s += id + ",'" + q.getQuestion().replaceAll("'", "''") + "'" + ",'"
                        + q.getCorrectAnswer().replaceAll("'", "''") + "'" + ",'"
                        + q.getAnswer1().replaceAll("'", "''") + "'" + ",'"
                        + q.getAnswer2().replaceAll("'", "''") + "'" + ",'"
                        + q.getAnswer3().replaceAll("'", "''") + "'" + ","
                        + 0
                        + "\n";

            }
        }
        s += ";";

        output.write(s);
        output.close();

    }

    public static boolean isLogin(String user1, String pass1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
       
        Topic  parent = Controller.getTopicParentByName("Math");;
        System.out.println(parent.toString());
        parent = Controller.getTopicParentByName("Science");;
        System.out.println(parent.toString());
    }
}

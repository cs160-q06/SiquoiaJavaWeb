
import DataOOD.Node;
import DataOOD.Question;
import DataOOD.Topic;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mr.nam
 */
public class Controller {

    public static void main(String[] args) throws FileNotFoundException {
        
        //test1();
        test2();
    }
    
    private static void addTopic(Node currentNode, Node previousNode, String line, int count) {
        Node root = previousNode;
        Scanner sc = new Scanner(line);
                sc.useDelimiter(">");
                while(sc.hasNext())
                {
                    count++;
                    String s = sc.next();
                    Topic topic = new Topic(count,s);
                    previousNode = currentNode;
                    currentNode = new Node(previousNode, null, topic);
                    previousNode.addChild(currentNode);
                }
                System.out.println(currentNode.toString());
    }

    private static void test1() throws FileNotFoundException {
       File file = new File("dataset1.txt");
        Scanner scLine = new Scanner(file);
        List<Question> questionList = new ArrayList();
        int count = 0;
        Node root = new Node(null, null, null);
        Node currentNode = root;
        Node previousNode = root;
        while (scLine.hasNextLine()) {
            String line = scLine.nextLine().trim();

            if (line.isEmpty()) {
                if (scLine.hasNextLine()) {
                    line = scLine.nextLine().trim();
                    if (line.isEmpty()) {
                        if (scLine.hasNextLine()) {
                            line = scLine.nextLine().trim();
                            if (!line.isEmpty()) {
                                //after 2 empty lines, it is a topic line
                            }
                        }
                    }
                    else
                    {
                        
                    }
                }
            }
            else
            {
                addTopic(currentNode, previousNode, line, count);                
            }
        }
    }

    private static void test2() throws FileNotFoundException {
        File file = new File("dataset2.txt");
        Scanner scLine = new Scanner(file);
        List<Question> questionList = new ArrayList();
        int count = 0;
        Node root = new Node(null, null, null);
        Node currentNode = root;
        Node previousNode = root;
        while (scLine.hasNextLine()) {
             String line = scLine.nextLine().trim();

            if (!line.isEmpty()) {
                addTopic(currentNode, previousNode, line, count);    
            }
        }
        System.out.println("root: " + root.toString());
    }
}

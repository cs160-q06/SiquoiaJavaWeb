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
public class Topic {

    private int id;
    private String description;
    private int parent;
    private int level;

    public Topic(int id, String topic) {
        this.id = id;
        this.description = topic;
    }

    public Topic(int id, String topic, int parent) {
        this.id = id;
        this.description = topic;
        this.parent = parent;
    }

    public Topic(int id, String description, int parent, int level) {
        this.id = id;
        this.description = description;
        this.parent = parent;
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String topic) {
        this.description = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "[(id : " + id + "),(topic : " + description
                + "),(parent : " + parent + "),(level : " + level + ")]";
    }

    String toSimpleString() {
        return description;
    }

    public static List<Topic> doQueryGetAll(Connection conn) throws SQLException {
        String query = "SELECT * from TOPIC;";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<Topic> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("ID");
            String descr = rs.getString("DESCRIPTION");
            int parent_id = rs.getInt("PARENT_ID");
            if (rs.wasNull()) {
                parent_id = 0;
            }
            int level = rs.getInt("LEVEL");

            Topic i = new Topic(id, descr, parent_id, level);
            list.add(i);
        }
        return list;
    }

    public static Node<Topic> createRootNode(Connection conn) throws SQLException {
        List<Topic> list = doQueryGetAll(conn);
        Node<Topic> root = new Node<Topic>(0, new Topic(0, "root", 0, 0));
        try {
            for (Topic t : list) {
                int parent_id = t.getParent();
                Node<Topic> node = root.getChildByID(parent_id);
                node.addChild(new Node<Topic>(t.getId(), t));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return root;

    }

    public static List<Topic> getTopicAndAllSubTopicByID(Connection conn, int id) throws SQLException {
        Node<Topic> root = createRootNode(conn);
        root = root.getChildByID(id);
        List<Topic> list = root.getFatherAndAllChildren();
        return list;

    }

    public static List<Topic> getSubTopicByID(Connection conn, int id) throws SQLException {
        Node<Topic> root = createRootNode(conn);
        root = root.getChildByID(id);
        List<Topic> list = root.getChildrenOfFather();
        return list;
    }
}

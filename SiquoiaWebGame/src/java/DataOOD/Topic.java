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
    private String topic;
    private int parent;
    private int level;

    public Topic(int id, String topic) {
        this.id = id;
        this.topic = topic;
    }

    public Topic(int id, String topic, int parent) {
        this.id = id;
        this.topic = topic;
        this.parent = parent;
    }

    public Topic(int id, String topic, int parent, int level) {
        this.id = id;
        this.topic = topic;
        this.parent = parent;
        this.level = level;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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
        return "[[id:" + id + "],[topic:" + topic + "]]" + parent + " " +level;
    }

    String toSimpleString() {
        return topic;
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
            if(rs.wasNull()) parent_id=-1;
            int level = rs.getInt("LEVEL");

            Topic i = new Topic(id, descr, parent_id, level);
            list.add(i);
        }
        return list;
    }
}

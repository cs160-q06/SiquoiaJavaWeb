/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataOOD;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mr.nam
 */
public class Node {

    private Node parent;
    private List<Node> children;
    private Topic topic;

    public Node(Node parent, List<Node> children, Topic topic) {
        this.parent = parent;
        this.children = children;
        this.topic = topic;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Node{children=" + children + ", topic=" + topic + '}';
    }

    public void addChild(Node child) {
        if (children == null) {
            this.children = new ArrayList<>();
        } else {
            children.add(child);
        }
    }
}

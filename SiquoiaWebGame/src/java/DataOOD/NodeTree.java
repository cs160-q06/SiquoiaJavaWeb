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
public class NodeTree<Topic> {

    private Node<Topic> root;

    public NodeTree(Topic rootData) {
        root = new Node<Topic>();
        root.data = rootData;
        root.children = new ArrayList<Node<Topic>>();
    }

    public static class Node<Topic> {

        private Topic data;
        private Node<Topic> parent;
        private List<Node<Topic>> children;
    }
}

package de.entjic.builder.inventory.dialog;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node parent;
    private final List<Node> children =  new ArrayList<>();


    public void addChild(Node child) {
        child.setParent(this);
        children.add(child);
    }

    public void addChildren(List<Node> children) {
        children.forEach(child -> child.setParent(this));
        this.children.addAll(children);
    }

    private void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public Node getParent() {
        return parent;
    }

    public Node getRoot() {
        if (parent == null) {
            return this;
        } else {
            return parent.getRoot();
        }
    }
}

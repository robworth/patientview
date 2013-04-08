package com.worthsoln.patientview.model;

import com.worthsoln.patientview.model.enums.NodeError;
import org.w3c.dom.Node;

public class CorruptNode implements Comparable<CorruptNode> {

    Node node;
    NodeError error;

    public CorruptNode(Node node, NodeError error) {
        this.node = node;
        this.error = error;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public NodeError getError() {
        return error;
    }

    public void setError(NodeError error) {
        this.error = error;
    }

    /**
     * compare based on the order in which the values are declared
     *
     * @param corruptNode
     * @return
     */
    @Override
    public int compareTo(CorruptNode corruptNode) {
        return error.compareTo(corruptNode.getError());
    }
}

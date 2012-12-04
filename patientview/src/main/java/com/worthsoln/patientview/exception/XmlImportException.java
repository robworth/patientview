package com.worthsoln.patientview.exception;

import com.worthsoln.patientview.model.CorruptNode;

import java.util.ArrayList;
import java.util.List;

public class XmlImportException extends Exception {

    List<CorruptNode> nodeList;

    public XmlImportException() {
        this.nodeList = new ArrayList<CorruptNode>();
    }

    public List<CorruptNode> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<CorruptNode> nodeList) {
        this.nodeList = nodeList;
    }
}

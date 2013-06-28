/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.patientview.model;

import org.patientview.patientview.model.enums.NodeError;
import org.w3c.dom.Node;

public class CorruptNode implements Comparable<CorruptNode> {

    private Node node;
    private NodeError error;

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

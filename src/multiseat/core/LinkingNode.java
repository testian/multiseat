/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multiseat.core;

import java.util.Set;

/**
 * Nodes that can link to other nodes
 * @author testi
 */
public interface LinkingNode<T extends TargetNode> extends INode {

    public boolean linkTo(T targetNode);
    public boolean unlinkTo(T targetNode);
    public Set<T> links();
}

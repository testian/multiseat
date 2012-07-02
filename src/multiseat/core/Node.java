/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multiseat.core;

/**
 * interface for multi seat graph nodes
 * @author testi
 */
public abstract class Node implements INode {
    @Override
    public final boolean equals(Object o) {
    return (o instanceof INode && ((INode)o).id().equals(id()));
    }

    @Override
    public final int hashCode() {
    return id().hashCode();
    }


    public int compareTo(INode o) {

        int test1 = (this.id().length() - o.id().length());
        if (test1 != 0) return test1;
        return this.id().compareTo(o.id());
    }
    @Override
    public String toString() {
    return this.id() + ": " + this.name();
    }

}

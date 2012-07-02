/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multiseat.xsystem;

import java.util.TreeSet;
import java.util.Set;
import multiseat.core.Node;
import multiseat.core.OutputDevice;
import multiseat.core.Session;

/**
 *
 * @author testi
 */
public class XSession extends Session {

    public boolean linkTo(OutputDevice targetNode) {
        return false;
    }

    public boolean unlinkTo(OutputDevice targetNode) {
        return false;
    }

    public Set<OutputDevice> links() {
        TreeSet<OutputDevice> os = new TreeSet<OutputDevice>();
        os.add(new XOutputDevice());
        return os;
    }

    public String id() {
        return System.getenv("DISPLAY");
    }

    public String name() {
        return System.getenv("DISPLAY");
    }

    public boolean delete() {
        return false;
    }


    public int compareTo(Node o) {

        int test1 = (this.id().length() - o.id().length());
        if (test1 != 0) return test1;
        return this.id().compareTo(o.id());
    }

}

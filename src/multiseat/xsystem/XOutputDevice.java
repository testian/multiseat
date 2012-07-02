/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multiseat.xsystem;

import multiseat.core.Node;
import multiseat.core.OutputDevice;

/**
 *
 * @author testi
 */
public class XOutputDevice extends OutputDevice {

    public String id() {
        return "FAKEMONITOR";
    }

    public String name() {
        return "FAKEMONITOR";
    }

    public int compareTo(Node o) {

        int test1 = (this.id().length() - o.id().length());
        if (test1 != 0) return test1;
        return this.id().compareTo(o.id());
    }

}

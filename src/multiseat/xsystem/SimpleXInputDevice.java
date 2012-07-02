/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multiseat.xsystem;

import java.util.Set;
import java.util.TreeSet;
import multiseat.core.InputDevice;
import multiseat.core.Node;
import multiseat.core.Pointer;
import multiseat.core.SubType;

/**
 *
 * @author testi
 */
public class SimpleXInputDevice extends InputDevice {
private InputDevice device;
private XSystem origSystem;

    public SimpleXInputDevice(XSystem origSystem, InputDevice device) {
        this.device = device;
        this.origSystem = origSystem;
    }

    public boolean unlinkTo(Pointer targetNode) {
        //return device.unlinkTo(targetNode);



        if (this.type() == SubType.KEYBOARD) {
        return device.unlinkTo(otherClone(targetNode));
        } else {
        
        if (!device.unlinkTo(targetNode))
            return device.unlinkTo(otherClone(targetNode));
            return true;
        }

        /*if (!device.unlinkTo(targetNode)) {
        return device.unlinkTo(otherClone(targetNode));
        }
        return true;*/


    }

    public SubType type() {
        return device.type();
    }

    public String name() {
        return device.name();
    }

    public Set<Pointer> links() {
        if (this.type() != SubType.KEYBOARD) {
        return device.links();
        }
 else {
        TreeSet<Pointer> allLinks = new TreeSet<Pointer>();
        for (Pointer p : device.links()) {
        allLinks.add(otherClone(p,true));
        
        }
        return allLinks;
 
 }
    }

    public boolean linkTo(Pointer targetNode) {
        if (this.type() == SubType.KEYBOARD) {
        return device.linkTo(otherClone(targetNode));
        } else {
            if (!device.linkTo(targetNode))
            return device.linkTo(otherClone(targetNode));
            return true;
        }
    }

    public String id() {
        return device.id();
    }

    public int compareTo(Node o) {
        return device.compareTo(o);
    }

    private XPointer otherClone(Pointer node) {
    return otherClone(node,false);
    }
    
    private XPointer otherClone(Pointer node, boolean downwards) {
    int dif = downwards ? -1 : 1;
    return new XPointer((Integer.parseInt(node.id()) + dif) + "", node.name(), origSystem);
    }

}

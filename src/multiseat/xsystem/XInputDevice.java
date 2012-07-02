/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multiseat.xsystem;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.List;
import java.util.Set;
import multiseat.core.InputDevice;
import multiseat.core.MultiSeatListener;
import multiseat.core.Node;
import multiseat.core.Pointer;
import multiseat.core.SubType;

/**
 *
 * @author testi
 */
public class XInputDevice extends InputDevice/*, Comparable<XInputDevice>*/ {

    private String id;
    private String name;
    private XSystem system;
    private SubType type;

    public XInputDevice(String id, String name, XSystem system, SubType type) {
        this.id = id;
        this.name = name;
        this.system = system;
        this.type = type;
    }


    public boolean linkTo(Pointer targetNode) {
        if (XInputWrapper.reattach(id, targetNode.id())) {
        for (MultiSeatListener l : system.listeners) {
        //l.onInputDeviceEvent(system, this, EventType.MODIFY);
            l.onUpdate(system);
        }
        return true;
        }
    else {
    return false;
    }
    }

    public boolean unlinkTo(Pointer targetNode) {
    Set<Pointer> links = links();
    if (links.isEmpty() || !links.iterator().next().id().equals(targetNode.id())) return false;
    if (XInputWrapper.detach(id)) {
    for (MultiSeatListener l : system.listeners) {
        //l.onInputDeviceEvent(system, this, EventType.MODIFY);
        l.onUpdate(system);
        }
    return true;
    } else {return false;}
    }

    public Set<Pointer> links() {
        List<XInputWrapper.Entry> l = XInputWrapper.devices();
        HashMap<String, XInputWrapper.Entry> m = new HashMap<String, XInputWrapper.Entry>();
        for (XInputWrapper.Entry e : l) {
        m.put(e.id + "", e);
        }

        Set<Pointer> ps = new TreeSet<Pointer>();
       XInputWrapper.Entry me = m.get(this.id());
       if (me == null) return ps;

       XInputWrapper.Entry master = m.get(me.master + "");
       if (master == null) return ps;

       ps.add(new XPointer(master.id + "",master.name,system));
       return ps;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public int compareTo(Node o) {

        int test1 = (this.id().length() - o.id().length());
        if (test1 != 0) return test1;
        return this.id().compareTo(o.id());
    }


    public SubType type() {
        return type;
    }



    

}

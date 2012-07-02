/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multiseat.xsystem;

import java.util.TreeSet;
import java.util.Set;
import multiseat.core.MultiSeatListener;
import multiseat.core.Node;
import multiseat.core.Pointer;
import multiseat.core.Session;

/**
 *
 * @author testi
 */
public class XPointer extends Pointer {

    private String id,name;
    private XSystem system;

    public XPointer(String id, String name, XSystem system) {
        this.id = id;
        this.name = name;
        this.system = system;
    }


    public boolean linkTo(Session targetNode) {
        return false;
    }

    public boolean unlinkTo(Session targetNode) {
        return false;
    }

    public Set<Session> links() {
        TreeSet<Session> sessions = new TreeSet<Session>();
        sessions.add(new XSession());
        return sessions;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public boolean delete() {
        if (XInputWrapper.removeMaster(id + "")) {
        for (MultiSeatListener l : system.listeners) {
        //l.onPointerEvent(system, this, EventType.REMOVE); //TODO It also needs to notice about the remove of the paired other node
        l.onUpdate(system);
        }
        return true;
        }
        return false;

    }



}

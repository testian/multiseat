/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multiseat.xsystem;


import java.util.HashSet;
import java.util.TreeSet;
import java.util.List;
import java.util.Set;
import multiseat.core.InputDevice;
import multiseat.core.MultiSeatListener;
import multiseat.core.MultiSeatSystem;
import multiseat.core.OutputDevice;
import multiseat.core.Pointer;
import multiseat.core.Session;

/**
 *
 * @author testi
 */
public class XSystem implements MultiSeatSystem {

    Set<MultiSeatListener> listeners;
    private int counter;
    public XSystem() {
    listeners = new HashSet<MultiSeatListener>();
    counter = 0;
    }

    public void addListener(MultiSeatListener listener) {
        listeners.add(listener);
    }

    public Pointer createPointer() {
        XInputWrapper.Entry[] e = XInputWrapper.createMaster("Extra " + ++counter);
        if (e == null) return null;

        XPointer pointer = new XPointer(e[0].id + "",e[0].name,this);
        for (MultiSeatListener l : listeners) {
        //l.onPointerEvent(this, pointer, EventType.ADD);
        l.onUpdate(this);
        }
        /*if (e[1] != null) {
        XPointer keyboard = new XPointer(e[1].id + "",e[1].name,this);
        for (MultiSeatListener l : listeners) {
        //l.onPointerEvent(this, keyboard, EventType.ADD);
        l.onUpdate(this);
        }
        }*/
        return pointer;

    }

    public Session createSession() {
        return null;
    }

    public Set<InputDevice> inputDevices() {
        List<XInputWrapper.Entry> dlist = XInputWrapper.devices();
        TreeSet<InputDevice> s = new TreeSet<InputDevice>();

        for (XInputWrapper.Entry e : dlist) {
        if (!e.isMaster) {
        s.add(new XInputDevice(e.id + "",e.name,this,e.type));
        }
        }
        return s;
    }

    public Set<OutputDevice> outputDevices() {
        TreeSet<OutputDevice> os = new TreeSet<OutputDevice>();
        os.add(new XOutputDevice());
        return os;
    }

    public Set<Pointer> pointers() {
                List<XInputWrapper.Entry> dlist = XInputWrapper.devices();
        TreeSet<Pointer> s = new TreeSet<Pointer>();

        for (XInputWrapper.Entry e : dlist) {
        if (e.isMaster) {
        s.add(new XPointer(e.id + "",e.name,this));
        }
        }
        return s;
    }

    public void removeListener(MultiSeatListener listener) {
        listeners.remove(listener);
    }

    public Set<Session> sessions() {
        TreeSet<Session> sessions = new TreeSet<Session>();
        sessions.add(new XSession());
        return sessions;
    }

    public void refresh() {
        XInputWrapper.clearCache();
        for (MultiSeatListener l : this.listeners) {

            l.onUpdate(this);
        }
    }



}

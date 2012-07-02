/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multiseat.xsystem;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
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
public class SimpleXSystem implements MultiSeatSystem, MultiSeatListener {
private XSystem system;
private HashSet<MultiSeatListener> listeners;

    public SimpleXSystem() {
        this.system = new XSystem();
        listeners = new HashSet<MultiSeatListener>();
        system.addListener(this);

    }

    public Set<Session> sessions() {
        return system.sessions();
    }

    public void removeListener(MultiSeatListener listener) {
        system.removeListener(listener);
    }

    public Set<Pointer> pointers() {
        Set<Pointer> pointers = new TreeSet<Pointer>();
        
        
        for (Pointer p : system.pointers()) {
        if (p.name().endsWith("pointer"))
            pointers.add(p);
        }

        return pointers;
    }

    public Set<OutputDevice> outputDevices() {
        return system.outputDevices();
    }

    public Set<InputDevice> inputDevices() {
            Set<InputDevice> inputDevices = new TreeSet<InputDevice>();


        for (InputDevice p : system.inputDevices()) {
        if (!p.name().endsWith("XTEST pointer") && !p.name().endsWith("XTEST keyboard"))
            inputDevices.add(new SimpleXInputDevice(system,p));
        }
        
        
        return inputDevices;
    }

    public Session createSession() {
        return system.createSession();
    }

    public Pointer createPointer() {
        return system.createPointer();
    }

    public void addListener(MultiSeatListener listener) {
        listeners.add(listener);
    }

    public void onUpdate(MultiSeatSystem system) {
        for (MultiSeatListener l : listeners) {
        l.onUpdate(this);
        }
    }

    public void refresh() {
        system.refresh();
    }

    




}

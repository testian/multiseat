/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiseat.ui;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import multiseat.core.InputDevice;
import multiseat.core.MultiSeatListener;
import multiseat.core.MultiSeatSystem;
import multiseat.core.Pointer;

/**
 *
 * @author testi
 */
public class InputDevicesModel implements ListModel, MultiSeatListener {

    private MultiSeatSystem system;
    private Pointer currentPointer;
    private Set<InputDevice> devices;
    private InputDevice[] dArray;
    private boolean inverted;
    private Set<ListDataListener> listeners;

    public InputDevicesModel(MultiSeatSystem system, boolean inverted) {
        this.listeners = new HashSet<ListDataListener>();
        this.inverted = inverted;
        this.system = system;
        currentPointer = null;
        dArray = new InputDevice[0];
        onUpdate(system);
        system.addListener(this);
    }

    public void setCurrentPointer(Pointer currentPointer) {
        if (this.currentPointer == null || !this.currentPointer.id().equals(currentPointer.id())) {
            this.currentPointer = currentPointer;
            onUpdate(system);
        }

    }

    final public void onUpdate(MultiSeatSystem system) {
    Set<InputDevice> dev = system.inputDevices();
    if (currentPointer != null) {
    devices = new TreeSet<InputDevice>();
    for (InputDevice i : dev) {
    boolean ofCurrent = false;
    for (Pointer p : i.links()) {
    if (currentPointer.id().equals(p.id())) {
    ofCurrent = true;
    break;
    }
    }
    
    if (ofCurrent != inverted) {
        devices.add(i);
    }
    }
    } else {
    if (inverted) {devices = new TreeSet<InputDevice>();} else {
        devices = dev;
    }
    }
    dArray = devices.toArray(new InputDevice[0]);
    notifyListeners();


    }

    public void dispose() {
        system.removeListener(this);
    }
    private void notifyListeners() {
    for (ListDataListener ll : listeners) {
    ll.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, devices.size()-1));
    }
    }

    public void addListDataListener(ListDataListener ll) {
        listeners.add(ll);
    }

    public Object getElementAt(int i) {
        return dArray[i];
    }

    public int getSize() {
        return dArray.length;
    }

    public void removeListDataListener(ListDataListener ll) {
        listeners.remove(ll);
    }
}

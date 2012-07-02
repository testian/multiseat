/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multiseat.core;

import java.util.Set;

/**
 * Represents the whole system.
 * @author testi
 */
public interface MultiSeatSystem {
public Set<InputDevice> inputDevices();
public Set<Pointer> pointers();
public Set<Session> sessions();
public Set<OutputDevice> outputDevices();
public Pointer createPointer();
public Session createSession();
public void addListener(MultiSeatListener listener);
public void removeListener(MultiSeatListener listener);
public void refresh();
}

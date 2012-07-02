/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multiseat.core;

/**
 * Represents input devices such as keyboards, mouses, touchpads, gamepads, webcams etc.
 * @author testi
 */
public abstract class InputDevice extends Node implements LinkingNode<Pointer> {
    abstract public SubType type();

    @Override
    public String toString() {
    return this.id() + ": [" + type().toString().substring(0,1) +  "] " + this.name();
    }
}

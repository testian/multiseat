/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multiseat.core;

/**
 *
 * @author testi
 */
public enum EventType {
ADD, //Emitted when a node is added
REMOVE, //Emitted when a node is removed
MODIFY //Emitted when either a node has changed internals (e.g. for a monitor how to split two sessions linking to it) or when there were made changes using linkTo or unlinkTo
}

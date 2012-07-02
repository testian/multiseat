/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multiseat.xsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import multiseat.core.SubType;

/**
 *
 * @author testi
 */
public class XInputWrapper {

    private static List<Entry> dCache = null;



    public static void clearCache() {
    dCache = null;
    }


    public static boolean reattach(String slave, String master) {
    try {
        Process xi = Runtime.getRuntime().exec(new String[] {"xinput", "--reattach",slave, master});
        report(xi);
        if (xi.waitFor() != 0) {return false;}
        dCache = null;
        return true;
        } catch(Exception ex) {return false;}
    }

    public static boolean detach(String slave) {
    try {
        Process xi = Runtime.getRuntime().exec(new String[] {"xinput", "--float",slave});
        report(xi);
        if (xi.waitFor() != 0) {return false;}
        dCache = null;
        return true;
        } catch(Exception ex) {return false;}
    }


    public static boolean removeMaster(String id) {
        try {
        Process xi = Runtime.getRuntime().exec(new String[] {"xinput", "--remove-master",id});
        report(xi);
        if (xi.waitFor() != 0) {return false;}
        dCache = null;
        return true;
        } catch(Exception ex) {return false;}
    }
    private static void report(Process p) throws IOException {
    wStream(p.getInputStream());
    wStream(p.getErrorStream());
    }
    private static void wStream(InputStream i) throws IOException {
    BufferedReader b = new BufferedReader(new InputStreamReader(i));
    String line = null;
    while ((line = b.readLine()) != null) {
    System.out.println(line);
    }
    }
    public static Entry[] createMaster(String name) {
    try {
    
    Process xi = Runtime.getRuntime().exec(new String[] {"xinput", "--create-master",name});
    report(xi);
    if (xi.waitFor() != 0) {
    return null;
    }
    dCache = null;
    Entry[] el = new Entry[] {null,null};
    for (Entry e : devices()) {
    if (e.isMaster && e.name.equals(name + " keyboard") || e.name.equals(name + " pointer")) {
    if (el[0] == null)
    el[0] = e;
    else
    el[1] = e;
    }
    }
    if (el[0] == null) return null;
    return el;
    } catch (Exception ex) {return null;}
    }
    public static List<Entry> devices() {
    if (dCache != null) return dCache;
    List<Entry> deviceList = new LinkedList<Entry>();
    try {
    Process xi = Runtime.getRuntime().exec("xinput --list");
    BufferedReader br = new BufferedReader(new InputStreamReader(xi.getInputStream()));
    String line = null;
    int currentMaster = -1;
    while ((line = br.readLine()) != null) {
    System.out.println(line);
    String[] split = line.split("\t");
    if (split.length < 3) continue;
    int id = Integer.parseInt(split[1].substring(3));
    String name = split[0];
    boolean isMaster = split[2].trim().startsWith("[master");
    int master=-1;
    if (Character.isLetterOrDigit(name.charAt(2))) {
    name = name.substring(2);
    currentMaster = -1;
    }else {
    name = name.substring(6);
    }
    if (isMaster) {
    currentMaster = id;
    } else {
    master = currentMaster;
    }
    SubType type = split[2].contains("keyboard") ? SubType.KEYBOARD : ( split[2].contains("pointer") ? SubType.MOUSE : SubType.UNKNOWN);
    name = name.trim();
    //System.out.println("ID: " + id + ", Name: " + name + ", isMaster: " + isMaster + ", master: " + master);
    Entry e = new Entry();
    e.id = id;
    e.isMaster = isMaster;
    e.master = master;
    e.name = name;
    e.type = type;
    deviceList.add(e);
    /*for (String s : split) {
    System.out.println(s);
    }*/
    }
    } catch (IOException ex){System.err.println(ex);}
    dCache = deviceList;
    return deviceList;
    }
    public static class Entry {
    public String name;
    public int id;
    public int master;
    public boolean isMaster;
    public SubType type;
    }
    

}

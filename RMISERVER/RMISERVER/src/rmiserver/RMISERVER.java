/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiserver;

import java.rmi.AlreadyBoundException;
import java.rmi.RMISecurityManager;
import rmiserver.VRSinterface;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JOptionPane;

/**
 *
 * @author ibrahim
 */
public class RMISERVER {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.setProperty("java.security.policy","file:./policy.policy");
        //System.setSecurityManager(new SecurityManager());
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            VRSimplementation vr = new VRSimplementation();
            reg.bind("RMISERVER", vr);
            System.out.print("The server is up");
        } catch (RemoteException | AlreadyBoundException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}

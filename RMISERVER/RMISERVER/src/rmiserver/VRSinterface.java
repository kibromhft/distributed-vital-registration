/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiserver;

import java.rmi.*;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author ibrahim
 */
public interface VRSinterface extends Remote {

    public boolean changepassword(String username, String newpassword) throws RemoteException;

    public boolean new_user(String username, String newpassword) throws RemoteException;

    public List birth_search(String search_string) throws RemoteException;

    public List death_search(String search_string) throws RemoteException;

    public List marriage_search(String search_string) throws RemoteException;

    public List divorce_search(String search_string) throws RemoteException;

    public List adoption_search(String search_string) throws RemoteException;

    public List wife_search(String search_string) throws RemoteException;

    public List husband_search(String search_string) throws RemoteException;

    public String birth_id() throws RemoteException;

    public String death_id() throws RemoteException;

    public String divorce_id() throws RemoteException;

    public int marriage_id() throws RemoteException;

    public String adoption_id() throws RemoteException;

}

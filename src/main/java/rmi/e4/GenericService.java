package rmi.e4;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by marlanti on 8/28/17.
 */
public interface GenericService extends Remote {
    String echo(String message) throws RemoteException;

    String toUpper(String message) throws RemoteException;

    void addVisit() throws RemoteException;

    int getVisitCount() throws RemoteException;

    boolean isServiceQueueEmpty() throws RemoteException;

    void addToServiceQueue(String name) throws RemoteException;

    String getFirstInServiceQueue() throws RemoteException;
}

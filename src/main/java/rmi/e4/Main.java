package rmi.e4;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by marlanti on 8/28/17.
 */
public class Main {
    public static void main(final String[] args) throws  Exception {
        /*File | Project Structure | Artifacts then you should press alt+insert or click the plus icon and create new artifact choose --> jar --> From modules with dependencies.

                Next goto Build | Build artifacts --> choose your artifact.
*/
        final GenericService gs = new GenericServiceImpl();
        //UnicastRemoteObject estás levantando un servicio remoto a partir del export y usando el obketo.
        //Lo que te hace es wrappear
        //En el port puedo definir en que puerto empiezo a escuchar.
        final Remote remote = UnicastRemoteObject.exportObject(gs, 0);

        //con esto llamas al servidor de nombres
        final Registry registry = LocateRegistry.getRegistry();
        registry.rebind("service",remote);
        System.out.println("Service bound");
        //FIXME así solo no me va a correr porque no cree el servidor de nombres.

        /*final Registry registry = LocateRegistry.getRegistry("", 1099);
        registry.rebind("service",remote);
        final Service handle = (Service) registry.lookup("service");*/

        System.out.println("Service bound");
    }
}

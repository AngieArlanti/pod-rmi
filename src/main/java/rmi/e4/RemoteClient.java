package rmi.e4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.rmi.Naming;

/**
 * Created by marlanti on 8/28/17.
 */
public class RemoteClient {
    private static Logger logger = LoggerFactory.getLogger(RemoteClient.class);

    public static void main(final String[] args) throws  Exception {
        final GenericService service = (GenericService) Naming.lookup("//localhost:1099/service");

        for (int i = 0; i< 4 ; i++){
            service.addVisit();
        }

        logger.info("visits {}", service.getVisitCount());
    }

}


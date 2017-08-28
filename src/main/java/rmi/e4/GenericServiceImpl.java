package rmi.e4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by marlanti on 8/28/17.
 */
public class GenericServiceImpl implements GenericService {
    private int visit = 0;
    Queue<String> list = new LinkedList<>();

    @Override
    public String echo(String message) {
        return message;
    }

    @Override
    public String toUpper(String message) {
        if (message != null)
            return message.toUpperCase();
        return null;
    }

    @Override
    public synchronized void addVisit() {
        visit++;
    }

    @Override
    public synchronized int getVisitCount() {
        return visit;
    }

    @Override
    public boolean isServiceQueueEmpty() {
        return list.isEmpty();
    }

    @Override
    public void addToServiceQueue(String name) {
        list.add(name);
    }

    @Override
    public String getFirstInServiceQueue() {
        if (!list.isEmpty())
            return list.poll();
        throw new IllegalStateException();
    }
}

package com.kamshanski.utils.dataflow;

import java.util.HashSet;

public class Notifier {
    protected final HashSet<Listener> listeners = new HashSet<>();
    // TODO: сделать слабые ссылки
    public boolean listen(Listener listener) {
        if (listener == null) {
            return false;
        }

        listeners.add(listener);

        return true;
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public void notifyListeners() {
        for (Listener l : listeners) {
            l.run();
        }
    }

    @Override
    public String toString() {
        return "Notifier[" + listeners.size() + ']';
    }
}

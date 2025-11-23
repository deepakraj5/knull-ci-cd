package org.knullci.knull.application.interfaces;

public interface CommandHandler<T, V> {
    V handle(T command);
}

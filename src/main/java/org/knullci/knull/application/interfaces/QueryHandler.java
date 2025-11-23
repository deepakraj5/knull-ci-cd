package org.knullci.knull.application.interfaces;

public interface QueryHandler<T, V> {
    V handle(T query);
}

package com.service.microservice.data;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import static org.springframework.transaction.support.TransactionSynchronizationManager.isActualTransactionActive;
import static org.springframework.transaction.support.TransactionSynchronizationManager.isCurrentTransactionReadOnly;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        if (isActualTransactionActive() && isCurrentTransactionReadOnly()) {
            return DatabaseType.READ;
        }

        return DatabaseType.WRITE;
    }
    public static enum DatabaseType {
        READ,
        WRITE,
    }
}

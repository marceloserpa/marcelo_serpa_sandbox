package com.marceloserpa.multitenantoutbox.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TenantRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        var tenantId = TenantContextHolder.getTenantId();

        if(tenantId == null) {
            return TenantDatabase.DATABASE01; // default db
        }

        return TenantDistribution.lookupDatabase(tenantId)
                .orElseThrow(() -> new RuntimeException("not found database for this tenantId."));
    }
}

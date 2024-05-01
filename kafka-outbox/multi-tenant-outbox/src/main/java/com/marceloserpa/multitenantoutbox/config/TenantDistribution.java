package com.marceloserpa.multitenantoutbox.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TenantDistribution {

    private static Map<Long, TenantDatabase> distribution;

    static {
        distribution = new HashMap<>();
        distribution.put(2L, TenantDatabase.DATABASE01);
        distribution.put(1L, TenantDatabase.DATABASE02);
        distribution.put(3L, TenantDatabase.DATABASE02);
    }

    public static Optional<TenantDatabase> lookupDatabase(Long tenantId) {
        return distribution.containsKey(tenantId)
                ? Optional.ofNullable(distribution.get(tenantId))
                : Optional.empty();
    }
}

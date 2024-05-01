package com.marceloserpa.multitenantoutbox.config;

public class TenantContextHolder {

    private static final ThreadLocal<TenantDatabase> contextHolder =
            new ThreadLocal<TenantDatabase>();

    public static void setDatabase(TenantDatabase tenantDatabase) {
        contextHolder.set(tenantDatabase);
    }

    public static TenantDatabase getTenantDatabase() {
        return (TenantDatabase) contextHolder.get();
    }

    public static void clearDatabase() {
        contextHolder.remove();
    }

}

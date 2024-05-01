package com.marceloserpa.multitenantoutbox.config;

public class TenantContextHolder {

    private static final ThreadLocal<TenantDatabase> contextHolder =
            new ThreadLocal<TenantDatabase>();

    private static final ThreadLocal<Long> tenantIdHolder =
            new ThreadLocal<Long>();

    public static void setDatabase(TenantDatabase tenantDatabase) {
        contextHolder.set(tenantDatabase);
    }

    public static void setTenantId(Long tenantId) {
        tenantIdHolder.set(tenantId);
    }

    public static TenantDatabase getTenantDatabase() {
        return (TenantDatabase) contextHolder.get();
    }

    public static Long getTenantId(){
        return (Long) tenantIdHolder.get();
    }


    public static void clearDatabase() {
        contextHolder.remove();
        tenantIdHolder.remove();
    }

}

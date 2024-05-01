package com.marceloserpa.multitenantoutbox.config;

public class TenantContextHolder {

    private static final ThreadLocal<Long> tenantIdHolder =
            new ThreadLocal<Long>();

    public static void setTenantId(Long tenantId) {
        tenantIdHolder.set(tenantId);
    }

    public static Long getTenantId(){
        return (Long) tenantIdHolder.get();
    }


    public static void clearDatabase() {
        tenantIdHolder.remove();
    }

}

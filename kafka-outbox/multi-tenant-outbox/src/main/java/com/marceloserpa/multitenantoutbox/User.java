package com.marceloserpa.multitenantoutbox;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public record User(@Column("tenant_id") Long tenantId,
        @Column("id") Long userId,
        String username) {
}

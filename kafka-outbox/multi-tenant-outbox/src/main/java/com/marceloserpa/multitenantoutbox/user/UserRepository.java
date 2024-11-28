package com.marceloserpa.multitenantoutbox.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Iterable<User> findByTenantId(Long tenantId);
}

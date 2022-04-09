package com.marceloserpa.aggregatefun;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Long> {

    @Query("select count(*) from order_item")
    int countItems();

}

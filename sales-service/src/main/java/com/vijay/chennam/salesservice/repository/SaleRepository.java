package com.vijay.chennam.salesservice.repository;

import com.vijay.chennam.salesservice.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}

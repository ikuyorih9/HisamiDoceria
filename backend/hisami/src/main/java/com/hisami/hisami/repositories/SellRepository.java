package com.hisami.hisami.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hisami.hisami.entities.Id.SellId;
import com.hisami.hisami.entities.Sell;

public interface SellRepository extends JpaRepository<Sell, SellId> {
    @Query("""
                select s
                from Sell s
                where s.product.barcode = :barcode
            """)
    List<Sell> findAllByProductBarcode(@Param("barcode") String barcode);

}

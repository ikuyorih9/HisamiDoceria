package com.hisami.hisami.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hisami.hisami.entities.ProductContainRaw;
import com.hisami.hisami.entities.ProductContainRawId;

@Repository
public interface ProductContainRawRepository extends JpaRepository<ProductContainRaw, ProductContainRawId> {
    @Query(value = """
            SELECT *
            FROM product_contain_raw
            WHERE raw_name = :rawName
            """, nativeQuery = true)
    public List<ProductContainRaw> findAllByRaw(@Param("rawName") String rawName);

    @Query(value = """
            SELECT pcr.*
            FROM product_contain_raw pcr
            JOIN product p ON p.id = pcr.product_id
            WHERE p.barcode = :barcode
            """, nativeQuery = true)
    List<ProductContainRaw> findAllByProductBarcode(@Param("barcode") String barcode);

}

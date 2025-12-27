package com.hisami.hisami.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hisami.hisami.entities.ProductContainRaw;
import com.hisami.hisami.entities.ProductContainRawId;

@Repository
public interface ProductContainRawRepository extends JpaRepository<ProductContainRaw, ProductContainRawId> {

}

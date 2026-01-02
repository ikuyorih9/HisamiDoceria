package com.hisami.hisami.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hisami.hisami.entities.Id.SellId;
import com.hisami.hisami.entities.Sell;

public interface SellRepository extends JpaRepository<Sell, SellId> {

}

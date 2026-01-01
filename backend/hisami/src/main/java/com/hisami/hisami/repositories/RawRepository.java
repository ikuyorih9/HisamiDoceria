package com.hisami.hisami.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hisami.hisami.entities.Raw;

@Repository
public interface RawRepository extends JpaRepository<Raw, String> {

}

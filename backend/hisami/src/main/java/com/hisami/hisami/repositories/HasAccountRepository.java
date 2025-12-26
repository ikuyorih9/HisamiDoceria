package com.hisami.hisami.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hisami.hisami.entities.HasAccount;
import com.hisami.hisami.entities.HasAccountId;

@Repository
public interface HasAccountRepository extends JpaRepository<HasAccount, HasAccountId> {

}

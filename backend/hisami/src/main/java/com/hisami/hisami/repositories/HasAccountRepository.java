package com.hisami.hisami.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hisami.hisami.entities.EmployeeAccount;
import com.hisami.hisami.entities.HasAccount;
import com.hisami.hisami.entities.HasAccountId;

@Repository
public interface HasAccountRepository extends JpaRepository<HasAccount, HasAccountId> {
  List<HasAccount> findByAccount(EmployeeAccount account);

  @Query("""
        select ha
        from HasAccount ha
        join fetch ha.employee e
        where ha.account.username = :username
      """)
  Optional<HasAccount> findAllByAccountUsernameWithEmployee(@Param("username") String username);

  List<HasAccount> findByAccountUsername(String username);
}

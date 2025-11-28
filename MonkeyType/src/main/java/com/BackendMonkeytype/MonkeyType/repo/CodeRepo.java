package com.BackendMonkeytype.MonkeyType.repo;

import com.BackendMonkeytype.MonkeyType.model.Code;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepo extends JpaRepository<Code, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Code c WHERE c.email = :email")
    void deleteAllByEmail(@Param("email") String email);

    Code findByEmail(String email);
}

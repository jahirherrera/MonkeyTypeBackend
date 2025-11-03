package com.BackendMonkeytype.MonkeyType.repo;

import com.BackendMonkeytype.MonkeyType.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}

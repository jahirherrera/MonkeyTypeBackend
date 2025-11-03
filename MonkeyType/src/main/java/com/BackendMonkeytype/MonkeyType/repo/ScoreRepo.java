package com.BackendMonkeytype.MonkeyType.repo;

import com.BackendMonkeytype.MonkeyType.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepo extends JpaRepository<Score, Integer> {


}

package com.carbon.assessment.repository;

import com.carbon.assessment.entity.OverdueRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateRepo extends JpaRepository<OverdueRates, Long> {
    Optional<OverdueRates> findByRoomType(String roomType);
}

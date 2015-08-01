package com.socialcoding.domain.repositories.reliability;

import com.socialcoding.domain.models.Reliability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReliabilityRepository extends JpaRepository<Reliability, Long>, ReliabilityRepositoryCustom {
}

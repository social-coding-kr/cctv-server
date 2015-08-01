package com.socialcoding.domain.repositories;

import com.socialcoding.domain.models.Cctv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CctvRepository extends JpaRepository<Cctv, Long> {
	@Query("SELECT c FROM Cctv c LEFT JOIN FETCH c.comments WHERE c.cctvId = :cctvId")
	Cctv findOneWithComments(@Param("cctvId") long cctvId);
}

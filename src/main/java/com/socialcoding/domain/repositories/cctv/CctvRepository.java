package com.socialcoding.domain.repositories.cctv;

import com.socialcoding.domain.models.Cctv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CctvRepository extends JpaRepository<Cctv, Long>, CctvRepositoryCustom {
}

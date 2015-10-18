package com.socialcoding.api.cctv.repository;

import com.socialcoding.api.cctv.model.Cctv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CctvRepository extends JpaRepository<Cctv, Long>, CctvRepositoryCustom {
}

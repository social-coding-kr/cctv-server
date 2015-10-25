package com.socialcoding.api.cctv.repository;

import com.socialcoding.api.cctv.model.PrivateCctv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateCctvRepository extends JpaRepository<PrivateCctv, Long> {
}

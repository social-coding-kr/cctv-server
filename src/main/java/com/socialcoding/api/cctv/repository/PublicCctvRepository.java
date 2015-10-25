package com.socialcoding.api.cctv.repository;

import com.socialcoding.api.cctv.model.PublicCctv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicCctvRepository extends JpaRepository<PublicCctv, Long> {
}

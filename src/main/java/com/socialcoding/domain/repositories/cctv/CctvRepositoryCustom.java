package com.socialcoding.domain.repositories.cctv;

import com.socialcoding.domain.models.Cctv;
import com.socialcoding.domain.services.cctv.Position;

import java.util.List;

public interface CctvRepositoryCustom {
    List<Cctv> findAllBetweenPosition(Position lower, Position upper);
}

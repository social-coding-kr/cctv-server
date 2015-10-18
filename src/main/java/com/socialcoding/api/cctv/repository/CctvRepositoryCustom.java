package com.socialcoding.api.cctv.repository;

import com.socialcoding.api.cctv.model.Cctv;
import com.socialcoding.api.cctv.model.Position;

import java.util.List;

public interface CctvRepositoryCustom {
    List<Cctv> findAllBetweenPosition(Position lower, Position upper);
}

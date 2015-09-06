package com.socialcoding.domain.repositories.cctv;

import com.socialcoding.domain.models.Cctv;
import com.socialcoding.domain.models.QCctv;
import com.socialcoding.domain.services.cctv.Position;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.List;

public class CctvRepositoryImpl extends QueryDslRepositorySupport implements CctvRepositoryCustom {
    private QCctv cctv = QCctv.cctv;

    public CctvRepositoryImpl() {
        super(Cctv.class);
    }

    @Override
    public List<Cctv> findAllBetweenPosition(Position lower, Position upper) {
        return from(cctv) //
                .where(cctv.latitude.goe(lower.getLatitude()).and(cctv.latitude.loe(upper.getLatitude()))) //
                .where(cctv.longitude.goe(lower.getLongitude()).and(cctv.longitude.loe(upper.getLongitude()))) //
                .list(cctv);
    }
}

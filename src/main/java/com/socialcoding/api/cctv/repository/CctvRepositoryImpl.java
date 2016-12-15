package com.socialcoding.api.cctv.repository;

import com.socialcoding.api.cctv.model.Cctv;
import com.socialcoding.api.cctv.model.Position;
import com.socialcoding.api.cctv.model.QCctv;
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

	@Override
	public long countAllBetweenPosition(Position lower, Position upper) {
		return from(cctv) //
			.where(cctv.latitude.goe(lower.getLatitude()).and(cctv.latitude.loe(upper.getLatitude()))) //
			.where(cctv.longitude.goe(lower.getLongitude()).and(cctv.longitude.loe(upper.getLongitude())))
			.count();
	}
}

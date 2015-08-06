package com.socialcoding.domain.services.reliability;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalReliability {
	private long correctReliability;
	private long incorrectReliability;

	private TotalReliability() {
	}

	public static TotalReliability of(long correctPoint, long incorrectPoint) {
		TotalReliability totalReliability = new TotalReliability();
		totalReliability.setCorrectReliability(correctPoint);
		totalReliability.setIncorrectReliability(incorrectPoint);
		return totalReliability;
	}
}

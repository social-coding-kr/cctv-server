package com.socialcoding.domain.services;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReliablePoint {
	private long correctPoint;
	private long incorrectPoint;

	private ReliablePoint() {
	}

	public static ReliablePoint of(long correctPoint, long incorrectPoint) {
		ReliablePoint reliablePoint = new ReliablePoint();
		reliablePoint.setCorrectPoint(correctPoint);
		reliablePoint.setIncorrectPoint(incorrectPoint);
		return reliablePoint;
	}
}

package com.socialcoding.api.cctv.dto.response;

import com.socialcoding.api.base.ResponseStatus;
import com.socialcoding.api.base.dto.AbstractResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapCctvCountResult extends AbstractResponse {

	private long count;

	public static MapCctvCountResult success(long count) {
		MapCctvCountResult result = new MapCctvCountResult();
		result.setStatus(ResponseStatus.SUCCESS);
		result.setCount(count);
		return result;
	}
}

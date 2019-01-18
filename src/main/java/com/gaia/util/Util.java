package com.gaia.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Util {

	public static <T> List<T> getPageResult(List<T> list, Integer page, Integer pageSize) {
		if (list == null)
			return Collections.emptyList();

		if (page == null || pageSize == null || page <= 0 || pageSize <= 0)
			return list;

		List<T> data = new ArrayList<T>(list);
		int start = (page - 1) * pageSize;

		if (data.size() < start) {
			return Collections.emptyList();
		}

		return data.subList(start, Math.min(start + pageSize, list.size()));
	}

}

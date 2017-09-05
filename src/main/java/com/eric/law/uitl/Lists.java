package com.eric.law.uitl;

import java.util.ArrayList;
import java.util.List;

public class Lists {
	public static <T> List<T> newArrayList(T... ts) {
		List<T> tList = new ArrayList<T>();
		for (T t : ts) {
			tList.add(t);
		}
		return tList;

	}
}

package com.eric.law.service;

import java.util.List;

import com.eric.law.reference.Keywords;
import com.eric.law.uitl.Lists;

public class RequestService extends AbstractSectionService<String> {

	@Override
	public List<String> processData(List<String> data) {
		Integer requestLine = this.getRequestLine(data);
		if (null == requestLine) {
			System.out.println("Unable to read the request");
			return Lists.newArrayList();
		}
		String request = data.get(requestLine);
		List<String> requestEnd = Keywords.get(Keywords.REQUEST_END);
		String key = search(requestEnd, request);
		if (null != key) {
			request = request.split(key)[0];
		}

		List<String> requestBegin = Keywords.get(Keywords.REQUEST);
		key = search(requestBegin, request);
		if (null != key) {
			request = request.split(key)[1];
		}
		List<String> requests = Lists.newArrayList(request.split(";"));
		System.out.println("Request section:");
		requests.forEach(e->System.out.println(e));
		return requests;
	}

	private String search(List<String> requestEnd, String request) {
		for (String key : requestEnd) {
			if (request.contains(key)) {
				return key;
			}
		}
		return null;
	}
}

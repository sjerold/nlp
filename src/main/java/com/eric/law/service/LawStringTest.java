package com.eric.law.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import com.eric.law.uitl.Lists;
import com.eric.law.uitl.StringUtils;

public class LawStringTest {

	private List<AbstractSectionService> serviceChain = Lists.newArrayList();
	{
		serviceChain.add(new CaseService());
		serviceChain.add(new RelationshipService());
		serviceChain.add(new RequestService());
		serviceChain.add(new JudgementService());
	}

	private List<String> read(String fileName) {
		FileReader fReader = null;
		BufferedReader bReader = null;
		List<String> result = Lists.newArrayList();
		try {
			fReader = new FileReader(fileName);
			bReader = new BufferedReader(fReader);
			String record = null;
//			while (StringUtils.isNotBlank(record = bReader.readLine())) {
			while ((record=bReader.readLine())!=null) {
				String convertedRecord = record.replace("：", ":").replace("，", ",").replace("；", ";").replace("（", "(").replace("）", ")");
				if (checkEffectiveRecord(convertedRecord))
					result.add(convertedRecord);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			closeReader(bReader);
			closeReader(fReader);
		}
		return result;
	}

	private boolean checkEffectiveRecord(String s) {
		if (StringUtils.isBlank(s))
			return false;
		if (s.contains("<") && s.contains(">") && (s.contains("b") || s.contains("t") || s.contains("d") || s.contains("p") || s.contains("l")))
//		if (s.contains("br") || s.contains("div") || s.contains("tr") || s.contains("td") || s.contains("p") || s.contains("span") || s.contains("title"))
			return false;
		return true;
	}
	
	private static void closeReader(Reader reader) {
		if (reader != null) {
			try {
				reader.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void process(String path) {
		List<String> result = read(path);
		for (AbstractSectionService service : serviceChain) {
			service.processData(result);
			System.out.println("");
		}
	}

	public static void main(String[] args) {
		String path = "/Users/wangrongrong/Desktop/4.txt";
		LawStringTest t = new LawStringTest();
		t.process(path);
	}
}

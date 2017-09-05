package com.eric.law.reference;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eric.law.domain.relation.EntityType;
import com.eric.law.uitl.Lists;

public class Keywords {
	public static final String REQUEST = "request";
	public static final String REQUEST_END = "request_end";
	public static final String JUDGEMENT = "judgement";
	public static final String JUDGEMENT_END = "judgement_end";
	
	private static Map<String,List<String>> key = new HashMap<String,List<String>>();
	static {
		key.put(REQUEST, Lists.newArrayList("提出诉讼请求:","诉讼请求:","起诉请求:","上诉请求:","诉讼请求"));
		key.put(REQUEST_END, Lists.newArrayList("事实及理由:","事实和理由:"));
		key.put(JUDGEMENT, Lists.newArrayList("判决如下:","裁定如下:"));
		key.put(JUDGEMENT_END, Lists.newArrayList("如不服本判决","本判决为终审判决","如不服"));
		
		key.put(EntityType.DEFENDANT.getValue(), Lists.newArrayList("被告:","被上诉人(原审原告):","被上诉人(原审被告):","被上诉人(原审第三人):"));
		key.put(EntityType.PLAINTIFF.getValue(), Lists.newArrayList("原告:","上诉人(原审原告):","上诉人(原审被告):","上诉人(原审第三人):"));
		key.put(EntityType.AGENT.getValue(), Lists.newArrayList("委托诉讼代理人:","委托代理人:"));
		key.put(EntityType.REPRESENTATIVE.getValue(), Lists.newArrayList("法定代表人:","代表人:"));
		
	}
	public static final Map<String,List<String>> KEYWORD = Collections.unmodifiableMap(key);
	
	public static final List<String> get(String key){
		return KEYWORD.get(key);
	}
}

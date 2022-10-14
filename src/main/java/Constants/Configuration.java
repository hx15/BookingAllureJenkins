package Constants;

import java.util.HashMap;

public class Configuration {
	public static HashMap<String, String> monthToNumberHashMap;
	static {
		monthToNumberHashMap = new HashMap<String, String>();
		monthToNumberHashMap.put("December" ,	 "12");
		monthToNumberHashMap.put("November" ,	 "11");
		monthToNumberHashMap.put("October"  , 	 "10");
		monthToNumberHashMap.put("September",	 "09");
		monthToNumberHashMap.put("August"   , 	 "08");
		monthToNumberHashMap.put("July"		, 	 "07");
		monthToNumberHashMap.put("June"		, 	 "06");
		monthToNumberHashMap.put("May"		, 	 "05");
		monthToNumberHashMap.put("April"	, 	 "04");
		monthToNumberHashMap.put("March"	, 	 "03");
		monthToNumberHashMap.put("Feburary" , 	 "02");
		monthToNumberHashMap.put("January"	, 	 "01");
	}
	
}

package csvtests;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CsvFileReader2 {
	
private static final String CSVFILEPATH = "./Files/upgrade.csv";


	public static void main(String[] args) throws Exception {
	
		System.out.println(loadDataFromCsv());
		
		
		//ArrayList<Map<String,String>> list=loadDataFromCsv();
		
		
	/*	int i;
	    for (i=0;i<list.size();i++) {
	    	
	    	 ObjectMapper oMapper = new ObjectMapper();
	    	 Map<String, Object> map2 = oMapper.convertValue(list.get(i), Map.class);
	    	 System.out.println(map2.get("USER_NAME"));
	    	 System.out.println(map2.get("FORCED"));
	    	 System.out.println();
	    	 
	    	// Map mapItems =ArrayUtils.toMap(obj2);
	    	// System.out.println("The item with key0 is : " + mapItems.get("status"));    }
	    }*/
		
	//	 System.out.print(obj2[0][0]);
	}
	
	private  static ArrayList<List<Map<String,String>>>  loadDataFromCsv() throws Exception {

		 ArrayList<List<Map<String,String>>> Object = new ArrayList<List<Map<String,String>>>();
		
		File file = new File(CSVFILEPATH);
		byte[] bytes = FileUtils.readFileToByteArray(file);
		String data = new String(bytes);
		
		data = StringUtils.replaceAll(data, "\r", "");
		String[] dataArray = data.split("\n");
		
		String keys = dataArray[0];
		
		Map<String, Map<String, String>> outerMap = new HashMap<>();
		List<String> keysFromFile = new ArrayList<>();
		
		String[] keyArr = keys.split(",");
		
		keysFromFile.addAll(Arrays.asList(keyArr));
	//	keysFromFile.remove(0);
		 Object[][] obj = new Object[ dataArray.length-1][0];
		
		for(int d = 1; d < dataArray.length-1; d++) {
			
			
			List l=new ArrayList<Map<String,String>>();
			 List<String> row = new ArrayList<>();
			String[] rowArr = dataArray[d].split(",");
			row.addAll(Arrays.asList(rowArr));
			String keyForTestCase = row.get(0);
		//	row.remove(0);
			 Map<String, String> mp	 = new HashMap<String,String>();
		for( int i = 0; i < keysFromFile.size(); i++) {
			 	
			  mp.put(keysFromFile.get(i), row.get(i).trim());
		}
		
		l.add(mp);
		Object.add(l);	//outerMap.put(keyForTestCase, mp);
	}
		
    return Object;
	}

}

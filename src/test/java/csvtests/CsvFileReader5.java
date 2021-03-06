package csvtests;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;

public class CsvFileReader5 {
	
//private static final String CSVFILEPATH = "./Files/VERSION_UPGRADE.csv";





public  static List<Object> retrieveSpecificData(String fileBath,String columnName) throws Exception{
	
	
	List<Object> columnData=new ArrayList<Object>();
	ArrayList<Map<Object,Object>> list=loadDataFromCsv(fileBath);
	
	int i;
    for (i=0;i<list.size();i++) {
    	
    	 ObjectMapper oMapper = new ObjectMapper();
    	 Map<Object, Object> map2 = oMapper.convertValue(list.get(i), Map.class);
    	// System.out.println(map2.get(" STATUS"));
    	 
    	 columnData.add(map2.get(columnName));
    	
    }
	
	return columnData;
}



	public static void main(String[] args) throws Exception {
	
	//	System.out.println(loadDataFromCsv());
		
		
		
		List <Object> DataRetieved=retrieveSpecificData("./Files/upgrade.csv","Status");
		//List <Object> DataRetieved=retrieveSpecificData("./Files/upgrade.csv","Status");
		for(Object d:DataRetieved) {
			
			System.out.print(d + " ");
			
			
		}
	}
	
	private  static ArrayList<Map<Object,Object>>  loadDataFromCsv(String fileBath ) throws Exception {

		 ArrayList<Map<Object,Object>> Object = new ArrayList<Map<Object,Object>>();
		
		File file = new File(fileBath);
		byte[] bytes = FileUtils.readFileToByteArray(file);
		String data = new String(bytes);
		
		data = StringUtils.replaceAll(data, "\r", "");
		String[] dataArray = data.split("\n");
		
	
		
		String keys = dataArray[0];
	
		 
		 
	           
	        
		Map<String, Map<String, String>> outerMap = new HashMap<>();
		List<String> keysFromFile = new ArrayList<>();
		
		String[] keyArr = keys.split(",");
		int sizeArray=keyArr.length;
		
		
		keysFromFile.addAll(Arrays.asList(keyArr));
	//	keysFromFile.remove(0);
		// Object[][] obj = new Object[ dataArray.length-1][0];
		
		for(int d = 1; d < dataArray.length; d++) {
			
			
			List l=new ArrayList<Map<String,String>>();
			 List<String> row = new ArrayList<>();
			String[] rowArr =dataArray[d].split(",");
			
		
			
			row.addAll(Arrays.asList(rowArr));
			
			if (row.size()<sizeArray) {
				
				row.add(null);
				
				
				
			}
	//		String keyForTestCase = row.get(0);
		//	row.remove(0);
			 Map<Object, Object> mp	 = new HashMap<Object,Object>();
		for( int i = 0; i < keysFromFile.size(); i++) {
			 	
			if (row.get(i)==null ||row.get(i).isBlank() ||row.get(i).isEmpty())
			{
				mp.put(keysFromFile.get(i).trim(), null);
			}else {
				mp.put(keysFromFile.get(i).trim(), row.get(i).trim());
			}
			
			  
		}
		
		
		Object.add(mp);	//outerMap.put(keyForTestCase, mp);
	}
		
    return Object;
	}

}

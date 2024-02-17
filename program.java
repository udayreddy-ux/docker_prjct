import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.net.*;
public class program {
	public static void main(String[] args) throws IOException {
		BufferedReader bf=new BufferedReader(new FileReader("/home/data/Limerick-1.txt"));
		BufferedReader bf1=new BufferedReader(new FileReader("/home/data/IF.txt"));
		StringBuilder st=new StringBuilder();
		StringBuilder st1=new StringBuilder();
		String line=null;
		String filePath = "/home/data/result.txt";
		while((line=bf.readLine())!=null) {
			if(!line.isEmpty()) {
				st.append(line);
				st.append(" ");
			}
		}
		while((line=bf1.readLine())!=null) {
			if(!line.isEmpty()) {
				st1.append(line);
				st1.append(" ");
			}
		}
		String res=st.toString();
		res=res.toLowerCase();
		res=res.trim();
		res=res.replaceAll("-", " ");
		res=res.replaceAll("[^\\w\\s]","");
		String res1=st1.toString();
		res1=res1.toLowerCase();
		res1=res1.trim();
		res1=res1.replaceAll("-", " ");
		res1=res1.replaceAll("[^\\w\\s]","");
		//System.out.println(res);
		//System.out.println(res1);
		int count1=0;
		int count2=0;
		for(int i=0;i<res.length();i++) {
			if(res.charAt(i)==' ') {
				count1++;
			}
		}
		for(int i=0;i<res1.length();i++) {
			if(res1.charAt(i)==' ') {
				count2++;
			}
		}
		count1++;
		count2++;
		HashSet<String> hs1=new HashSet<>();
		HashSet<String> hs2=new HashSet<>();
		int left=0;
		for(int i=0;i<res.length();i++) {
			if(res.charAt(i)==' ') {
				hs1.add(res.substring(left,i));
				left=i+1;
			}
		}
		left=0;
		for(int i=0;i<res1.length();i++) {
			if(res1.charAt(i)==' ') {
				hs2.add(res1.substring(left,i));
				left=i+1;
			}
		}
		writeToFile(filePath,"The number of words in Limerick-1 file is:"+count1);
		//System.out.println("The number of words in Limerick-1 file is:"+count1);
		writeToFile(filePath,"The distinct words in Limerick-1 file is:"+hs1.size());
		//System.out.println("The distinct words in Limerick-1 file is:"+hs1.size());
		writeToFile(filePath,"The number of words in IF file is: "+count2);
		//System.out.println("The number of words in IF file is: "+count2);
		writeToFile(filePath,"The distinct words in IF file is:"+hs2.size());
		//System.out.println("The distinct words in IF file is:"+hs2.size());
		writeToFile(filePath,"The total number of words in both files is:"+(count1+count2));
		//System.out.println("The total number of words in both files is:"+(count1+count2));
		HashMap<String,Integer> mp=new HashMap<>();
		left=0;
		for(int i=0;i<res1.length();i++) {
			if(res1.charAt(i)==' ') {
				if(mp.containsKey(res1.substring(left, i))) {
					mp.put(res1.substring(left,i), mp.get(res1.substring(left, i))+1);
				}
				else {
					mp.put(res1.substring(left, i), 1);
				}
				left=i+1;
			}
		}
		 // Sorting the HashMap by values in descending order
        LinkedHashMap<String, Integer> sortedMap = sortByValuesDescending(mp);
        int count=0;
        writeToFile(filePath,"The top 3 words are:-");
        //System.out.println("The top 3 words are:-");
        for(Map.Entry<String, Integer> en:sortedMap.entrySet()) {
        	if(count<3) {
        		writeToFile(filePath,(count+1) +". "+en.getKey()+":"+en.getValue());
        		//System.out.println((count+1) +". "+en.getKey()+":"+en.getValue());
        		count++;
        	}
        	else {
        		break;
        	}
        }
        try {
        	InetAddress localhost=InetAddress.getLocalHost();
        	String ipAddress =localhost.getHostAddress();
        	 // Specify the path where you want to save the file
            writeToFile(filePath, "IP Address of your machine: " + ipAddress);
        	//System.out.println("IP Address of your machine: " + ipAddress);
        }
        catch(UnknownHostException e){
        	 System.out.println("Unable to determine the IP address of your machine.");
             e.printStackTrace();
        }
        displayFileContents(filePath);
 	}
	 public static LinkedHashMap<String, Integer> sortByValuesDescending(HashMap<String, Integer> map) {
	        // Convert HashMap to a list of Map.Entry objects
	        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());

	        // Sort the list based on values in descending order
	        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
	            @Override
	            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
	                return o2.getValue().compareTo(o1.getValue());
	            }
	        });

	        // Create a new LinkedHashMap to store the sorted entries
	        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
	        for (Map.Entry<String, Integer> entry : list) {
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }
	        return sortedMap;
	    }
	    private static void writeToFile(String filePath, String content) throws IOException {
	        FileWriter writer = new FileWriter(filePath,true);
	        writer.write(content + "\n"); // Add newline character to separate each content
	        writer.close();
	    }
	   
	    private static void displayFileContents(String filePath) throws IOException {
	        BufferedReader reader = new BufferedReader(new FileReader(filePath));
	        String line;
	        System.out.println("Contents of the file:");
	        while ((line = reader.readLine()) != null) {
	            System.out.println(line);
	        }
	        reader.close();
	    }
}
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

//import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.Map;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

public class Hello_world{
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1){
                buffer.append(chars, 0, read);
            }

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }

    }
    
    public static Map<String, Map> mapItOut(Map m){
        Map<String, String> description = new HashMap<String, String>();
        description.put("name",(String)m.get("\"name\""));
        description.put("age",(String)m.get("\"age\""));
        description.put("gender",(String)m.get("\"gender\""));
        description.put("race",(String)m.get("\"race\""));
        description.put("occupation",(String)m.get("\"occupation\""));
        description.put("pronounMinus",(String)m.get("\"pronounMinus\""));
        description.put("pronounCapit",(String)m.get("\"pronounCapit\""));
        Map<String,String> physical=new HashMap<String,String>();
        physical.put("hair",(String)m.get("\"hair\""));
        physical.put("eyes",(String)m.get("\"eyes\""));
        physical.put("skin",(String)m.get("\"skin\""));
        physical.put("height",(String)m.get("\"height\""));
        physical.put("build",(String)m.get("\"build\""));
        physical.put("face",(String)m.get("\"face\""));
        physical.put("special",(String)m.get("\"special\""));
        Map<String,String> alignment=new HashMap<String,String>();
        Map<String,String> relationship=new HashMap<String,String>();
        Map<String,String> religion=new HashMap<String,String>();
        Map<String,String> ptraits=new HashMap<String,String>();
        Map<String,String> pquirks=new HashMap<String,String>();
        Map<String,String> hook=new HashMap<String,String>();
        Map<String,String> abilities=new HashMap<String,String>();
        
        Map<String,Map> complete = new HashMap<String,Map>();
        complete.put("description",description);
        complete.put("alignment",alignment);
        complete.put("relationship",relationship);
        complete.put("religion",religion);
        complete.put("ptraits",ptraits);
        complete.put("pquirks",pquirks);
        complete.put("hook",hook);
        complete.put("abilities",abilities);
        
        return complete;//
    }

	
    public static void main(String[] args){
        BufferedReader reader = null;
        try{
            //JSONParser parser = new JSONParser();
            String json = readUrl("http://npcgenerator.azurewebsites.net/_/npc");

            System.out.println(json);
            //System.out.println(json.a);
            json=json.replaceAll("\\{","");
            json=json.replaceAll("\\}","");
            json=json.replace("description\":","");
            json=json.replace("\"hook\":\"","\"description\":");
            json=json.replace("\"pquirks\":\"","\"descriptionpq\":");
            json=json.replace("\"religion\":\"","\"descriptionreligion\":");
            json=json.replace("\"alignment\":\"","\"");
            json=json.replace("\"ptraits\":\"","\"");

            json=json.substring(1,json.length()-1);
            String[] arr=json.split(",");

            System.out.println("~~~~~~~~");
            Map<String, String> myMap  = new HashMap<String, String>();
            for (int i=0;i<arr.length;i++) {
                String pair = arr[i];
                System.out.println(pair);
                if(pair.contains(":")){
                    String[] keyValue = pair.split(":");
                    String cat="";
                    for(int n=1;n<keyValue.length;n++){
                        cat=cat+keyValue[n];
                    }
                    myMap.put(keyValue[0],cat);
                }
            }
            
            myMap.remove("\\{\"description\"");
            myMap.remove("\"physical\"");
            myMap.remove("\"alignment\"");
            myMap.remove("\\{\"description\"");
            myMap.remove("\"relationship\"");
            myMap.remove("\"religion\"");
            myMap.remove("\"ptraits\"");
            myMap.remove("\"pquirks\"");
            myMap.remove("\"hook\"");

            System.out.println("!~~~~~~~~~~~");
            System.out.println((String)myMap.get("\"religion\""));
            System.out.println((String)myMap.get("\"name\""));
            System.out.println(myMap);
            
            Map<String,Map> work=mapItOut(myMap);
            System.out.println(((Map)work.get("description")).get("age"));
            
        }catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
}
import java.net.*;
import java.io.*;

import org.json.*;
public class ParseAPI {


    /*
    parse(int,String) makes a GET request and parses the returned JSONObject. pass the rulekey as the first parameter to parse.
    The second parameter is the specific file or directory in spoon you want to get the bugs for.
    Example: fname="src/main/java/spoon/MavenLauncher.java"; If you want to parse the entire source code, you need to pass "src/" in fname
     */
    public static JSONArray parse(int rulekey, String fname)throws Exception
    {
        String url="https://sonarqube.ow2.org/api/issues/search?types=BUG&rules=squid:S"+ Integer.toString(rulekey)+"&componentKeys=fr.inria.gforge.spoon:spoon-core";
        if(true)
        {
            url=url+":"+fname;
        }
        URL url1 = new URL(url);
        HttpURLConnection con = (HttpURLConnection) url1.openConnection();
        con.setRequestMethod("GET");
        int responseCode=con.getResponseCode();
        System.out.println("\nSending GET request to SonarQube API : " + url);
        if(responseCode!=200)
        {
            System.out.println("Response Code : "+responseCode);
            throw new Exception("ERROR : Wrong Reponse Code from Sonarqube API. Check Internet Connection");
        }
        System.out.println("GET Request Successful");
        BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();


        JSONObject jo = new JSONObject(response.toString());
        JSONArray jsonArray = jo.getJSONArray("issues");
        return jsonArray;
    }
}
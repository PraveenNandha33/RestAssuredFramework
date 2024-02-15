package Resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification reqSpec;

    public RequestSpecification requestSpecification() throws IOException {
        //To avoid overriding in the log file we need to check if req is null else we will use the same reqspec
        //In java if a var is declared  as static , it will use the same var for all the run and test cases ,it wont reinstantiate
        if(reqSpec==null) {
            PrintStream log = new PrintStream(new FileOutputStream("log.txt"));
            reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("url")).setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
            return reqSpec;
        }
        return reqSpec;

    }

    public String getGlobalValue(String Key) throws IOException {
        Properties prop=new Properties();
        FileInputStream fis=new FileInputStream("C:\\Users\\Praveennandha\\IDEAProjects\\RestAssuredFramework\\src\\test\\java\\Resources\\EnvironmentProperties.properties");
        prop.load(fis);
       return prop.getProperty(Key);

    }

    public String getJsonValue(Response response, String Key)
    {

        String res=response.asString();
        JsonPath js=new JsonPath(res);
        return js.getString(Key);

    }
}

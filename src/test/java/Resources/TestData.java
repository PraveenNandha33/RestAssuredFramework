package Resources;

import POJO.AddPlace;
import POJO.Location;

import java.util.Arrays;

public class TestData {
  public AddPlace addPlacePayload(String name , String address,String phoneNumber){
      AddPlace addPlace=new AddPlace();
      addPlace.setAccuracy(50);
      addPlace.setAddress(address);
      addPlace.setLanguage("French-IN");
      addPlace.setName(name);
      addPlace.setPhone_number(phoneNumber);
      addPlace.setWebsite("https://rahulshettyacademy.com");
      addPlace.setTypes(Arrays.asList("shoe park","shop"));
      Location location=new Location();
      location.setLat(-38.383494);
      location.setLng(33.427362);
      addPlace.setLocation(location);
      return addPlace;
  }

  public String deletePlacePayload(String place_id){
      return "{\n" +
              "    \"place_id\":\""+place_id+"\"\n" +
              "}\n";
  }
}

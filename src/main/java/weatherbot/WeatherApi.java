package weatherbot;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class WeatherApi {
	JsonPath jsonpath = null;

	protected ArrayList<String> getWeatherByCityName(String cityName) {
		ArrayList<String> weatherData = new ArrayList<String>();

		RestAssured.baseURI = "http://api.openweathermap.org";

		Response response = given().contentType(ContentType.JSON).param("q", cityName)
				.param("appid", "d30c64fe36af4c96bffef506cc0dea41").param("units", "metric").when()
				.get("/data/2.5/weather").then().extract().response();
		jsonpath = response.jsonPath();

		try {
			if (response.getStatusCode() == 200) {
				weatherData.add(0, jsonpath.get("main.temp").toString());
				weatherData.add(1, jsonpath.get("main.feels_like").toString());
				weatherData.add(2, jsonpath.get("name").toString());

			} else if (response.getStatusCode() == 404) {
				weatherData.add(0, jsonpath.get("message").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return weatherData;

	}

}

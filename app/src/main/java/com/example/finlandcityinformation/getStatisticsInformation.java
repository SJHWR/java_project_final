package com.example.finlandcityinformation;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

// Classes for obtaining information about Statistics Finland
public class getStatisticsInformation {
    static ObjectMapper objectMapper = new ObjectMapper();
    static JsonNode areas = readAreaDataFromTheAPIURL(objectMapper);
    static HashMap<String, String> municipalityNamesToCodesMap = createMunicipalityNamesToCodesMap(areas);
    // Methods for obtaining statistical information
    public static void getStatistics(Context context,String cityname) {
        String code = municipalityNamesToCodesMap.get(cityname);

        try {// Read the JSON data for the population query
            JsonNode jsonQuery = objectMapper.readTree(context.getResources().openRawResource(R.raw.populationquery));
            ((ObjectNode)jsonQuery.findValue("query").get(1).get("selection")).putArray("values").add(code);
            // Create and connect to an API
            URL url = new URL ("https://pxdata.stat.fi:443/PxWeb/api/v1/en/StatFin/synt/statfin_synt_pxt_12dy.px");
            HttpURLConnection con = connectToAPIAndSendPostRequest(objectMapper, jsonQuery, url);

            try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                // Parse the data in the API response
                JsonNode municipalityData = objectMapper.readTree(response.toString());
                JsonNode populations;

                populations = municipalityData.get("value");
                // Setting up information on demographic changes and total population of the city
                getCityInformation.getCity().setCityPopulationChange(String.valueOf(populations.get(0).intValue()));
                getCityInformation.getCity().setCityPopulation(String.valueOf(populations.get(1).intValue()));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {//Reading JSON data for workplace self-sufficiency queries
            JsonNode jsonQuery = objectMapper.readTree(context.getResources().openRawResource(R.raw.wpssquery));
            ((ObjectNode)jsonQuery.findValue("query").get(1).get("selection")).putArray("values").add(code);
            // Create and connect to an API
            URL url = new URL("https://pxdata.stat.fi:443/PxWeb/api/v1/en/StatFin/tyokay/statfin_tyokay_pxt_125s.px");
            HttpURLConnection con = connectToAPIAndSendPostRequest(objectMapper, jsonQuery, url);

            try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                // Parse the data in the API response
                JsonNode municipalityData = objectMapper.readTree(response.toString());
                JsonNode wpss;
                wpss = municipalityData.get("value");

                getCityInformation.getCity().setCityWSS(String.valueOf(wpss.get(0).floatValue()));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        float employmentRate = getEmploymentRate(context,cityname, code);

        getCityInformation.getCity().setCityEmploymentRate(String.valueOf(employmentRate));
    }

    // Methodology for obtaining urban employment rates
    private static float getEmploymentRate(Context context,String municipalityName, String code) {
        try {// Read the JSON data for the employment rate query.
            JsonNode jsonQuery = objectMapper.readTree(context.getResources().openRawResource(R.raw.employmentquery));
            ((ObjectNode)jsonQuery.findValue("query").get(0).get("selection")).putArray("values").add(code);
            // Create and connect to an API
            URL url = new URL("https://pxdata.stat.fi:443/PxWeb/api/v1/en/StatFin/tyokay/statfin_tyokay_pxt_115x.px");
            HttpURLConnection con = connectToAPIAndSendPostRequest(objectMapper, jsonQuery, url);

            try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                // Parse the data in the API response
                JsonNode municipalityData = objectMapper.readTree(response.toString());

                JsonNode employmentRate = null;

                employmentRate = municipalityData.get("value");


                System.out.println(municipalityName);
                System.out.println(employmentRate.get(0).toString());

                System.out.println("==========================");

                return employmentRate.get(0).floatValue();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    // Methods to connect to the API and send POST requests
    private static HttpURLConnection connectToAPIAndSendPostRequest(ObjectMapper objectMapper, JsonNode jsonQuery, URL url)
            throws MalformedURLException, IOException, ProtocolException, JsonProcessingException {

        HttpURLConnection con = (HttpURLConnection)url.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = objectMapper.writeValueAsBytes(jsonQuery);
            os.write(input, 0, input.length);
        }
        return con;
    }

    // Methods for creating city name to code mappings
    private static HashMap<String, String> createMunicipalityNamesToCodesMap(JsonNode areas) {
        JsonNode codes = null;
        JsonNode names = null;

        for (JsonNode node :areas.findValue("variables")) {
            if (node.findValue("text").asText().equals("Area")) {
                codes =  node.findValue("values");
                names = node.findValue("valueTexts");
            }
        }
        HashMap<String, String> municipalityNamesToCodesMap = new HashMap<>();

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i).asText();
            String code = codes.get(i).asText();
            municipalityNamesToCodesMap.put(name, code);
        }
        return municipalityNamesToCodesMap;
    }

    // Methods for reading region data from API URLs
    private static JsonNode readAreaDataFromTheAPIURL(ObjectMapper objectMapper) {
        JsonNode areas = null;
        try {
            areas = objectMapper.readTree(new URL("https://pxdata.stat.fi:443/PxWeb/api/v1/en/StatFin/synt/statfin_synt_pxt_12dy.px"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return areas;
    }
}

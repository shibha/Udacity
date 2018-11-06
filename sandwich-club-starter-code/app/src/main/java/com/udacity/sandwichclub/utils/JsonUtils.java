package com.udacity.sandwichclub.utils;

import android.util.Log;
import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * JsonUtils to parse Sandwich Detail JSON string and create a Sandwich Object
 */
public class JsonUtils {

    private final static String NAME = "name";
    private final static String MAIN_NAME = "mainName";
    private final static String ALSO_KNOWN_AS = "alsoKnownAs";
    private final static String PLACE_OF_ORIGIN = "placeOfOrigin";
    private final static String DESCRIPTION = "description";
    private final static String IMAGE = "image";
    private final static String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        JSONObject sandwichJson = null;
        JSONObject sandwichNameJson = null;
        String mainName = null;
        List<String> alsoKnownAs = null;
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        List<String> ingredients = null;

        /**
         * Read the entire JSON string
         */
        try {
            sandwichJson = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JsonUtils.java", "Error Occured while parsing Base Sandwich JSON. Returning null!");
            e.printStackTrace();
            return sandwich;
        }

        /**
         * Extract the name JSON
         */

        try {
            sandwichNameJson = sandwichJson.getJSONObject(NAME);
        } catch (JSONException e) {
            Log.e("JsonUtils.java", "Error Occured while parsing Sandwich name tag. Returning null!");
            e.printStackTrace();
            return sandwich;
        }

        /**
         * Extract the main name from name JSON
         */
        try {
            mainName = sandwichNameJson.getString(MAIN_NAME);
        } catch (JSONException e) {
            Log.e("JsonUtils.java", "Error Occured while parsing Sandwich mainName tag. Returning null!");
            e.printStackTrace();
            return sandwich;
        }

        /**
         * Extract the also known as array from name JSON
         */
        try {
            JSONArray also_known_as_json_array = sandwichNameJson.getJSONArray(ALSO_KNOWN_AS);
            alsoKnownAs = parseJSONArrayToJavaStringList(also_known_as_json_array);
        } catch (JSONException e) {
            Log.e("JsonUtils.java", "Error Occured while parsing Sandwich also_known_tag tag. Setting blank!");
            e.printStackTrace();
            alsoKnownAs = new ArrayList<>();
        }

        /**
         * Set the main Name and also known as into Sandwich
         */
        sandwich = new Sandwich();
        sandwich.setMainName(mainName);
        sandwich.setAlsoKnownAs(alsoKnownAs);

        /**
         * Extract the place of origin
         */
        try {
            placeOfOrigin = sandwichJson.getString(PLACE_OF_ORIGIN);
        } catch (JSONException e) {
            Log.e("JsonUtils.java", "Error Occured while parsing Sandwich Place Of Origin tag. Setting blank !");
            e.printStackTrace();
            placeOfOrigin = "";
        }

        /**
         * Set the place of origin
         */
        sandwich.setPlaceOfOrigin(placeOfOrigin);

        /**
         * Extract the description
         */
        try {
            description = sandwichJson.getString(DESCRIPTION);
        } catch (JSONException e) {
            Log.e("JsonUtils.java", "Error Occured while parsing Sandwich description tag. Setting blank!");
            e.printStackTrace();
            description = "";
        }

        /**
         * Set the description
         */
        sandwich.setDescription(description);

        /**
         * Extract the image location
         */
        try {
            image = sandwichJson.getString(IMAGE);
        } catch (JSONException e) {
            Log.e("JsonUtils.java", "Error Occured while parsing Sandwich image tag. Setting blank!");
            e.printStackTrace();
            image = "";
        }

        /**
         * Set the image location
         */
        sandwich.setImage(image);


        /**
         * Extract the ingredients array
         */
        try {
            JSONArray ingredients_json_array = sandwichJson.getJSONArray(INGREDIENTS);
            ingredients = parseJSONArrayToJavaStringList(ingredients_json_array);
        } catch (JSONException e) {
            Log.e("JsonUtils.java", "Error Occured while parsing Sandwich also_known_tag tag. Setting blank!");
            e.printStackTrace();
            ingredients = new ArrayList<>();
        }

        /**
         * Set the ingredients array
         */
        sandwich.setIngredients(ingredients);

        return sandwich;

    }

    /**
     * A method to convert from JSONArray to a List of Strings
     * @param jsonArray
     * @return
     * @throws JSONException
     */

    private static List<String> parseJSONArrayToJavaStringList(JSONArray jsonArray) throws JSONException {
        List<String> listOfJsonStrings = new ArrayList<>();
        int jsonArrLen = jsonArray.length() - 1;
        for (int i = 0; i <= jsonArrLen; i++) {
            if (i != jsonArrLen) {
                listOfJsonStrings.add(jsonArray.getString(i) + ", ");
            } else {
                listOfJsonStrings.add(jsonArray.getString(i));
            }

        }

        return listOfJsonStrings;
    }
}


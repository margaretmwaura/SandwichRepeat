package com.udacity.sandwichclub.utils;

import android.content.res.Resources;
import android.util.Log;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

//    The position has been passed to the method
//    This method should return a sandwich
//    Should parse the data and later form a sandwich
      private static Sandwich current = new Sandwich();
    public static Sandwich parseSandwichJson(String json)
    {
//        This is the code for getting the arrays that are in the strings file
//        String arr[] = Resources.getSystem().getStringArray(R.array.sandwich_details);
//        Converting this array into JSON
//        JSONArray jsonArray = new JSONArray(Arrays.asList(arr));
//        Now the data is as JSON
//       Get the object at the particular postion that has been tapped

//            This retrieves the object corresponding to that position
            try
            {
//                This is the main object that covers all the data
                JSONObject object = new JSONObject(json);
//             The object has an inner object name
//                The object name has the string mainname
//                It also has the array for the alsoKnown as names
//                It has the string for the place of origin
//                it has the string for the description
//                it has the string for the image
//                it has an array for the ingredients

//                This is the main object name
                JSONObject name = object.getJSONObject("name");
//                This is the first piece of data
                String mainname = name.getString("mainName");
                JSONArray alsoKnownAs  = name.getJSONArray("alsoKnownAs");
//                This is the string array that will be populated by the strings from the array
//                This is the second piece of data
                List<String> mainnameJava = new ArrayList<String>();
                for(int i=0; i<alsoKnownAs.length();i++)
                {
                    mainnameJava.add(alsoKnownAs.getString(i));
//                    That loop should get all the data from the mainName array
                }
//                This is the second piece of a data
                String placeOfOrigin = object.getString("placeOfOrigin");
//                This is the third piece
                String description = object.getString("description");
//                This is for getting the image which is gotten as a string
                String image = object.getString("image");
                Log.d("Image","This is the image to display"+ image);
//                This is for getting the array of ingredients
                 List<String> ingredientsJava  = new ArrayList<String>();
                 JSONArray ingredients = object.getJSONArray("ingredients");
                 for(int i=0; i<ingredients.length(); i++)
                 {
                     ingredientsJava.add(ingredients.getString(1));
//                     Done with the ingredients
                 }

//                 The next step is to create a sandwich

                 current.setMainName(mainname);
                 current.setAlsoKnownAs(mainnameJava);
                 current.setPlaceOfOrigin(placeOfOrigin);
                 current.setDescription(description);
                 current.setImage(image);
                 current.setIngredients(ingredientsJava);
            }
            catch (Exception e)
            {

            }



        return current;
    }
}

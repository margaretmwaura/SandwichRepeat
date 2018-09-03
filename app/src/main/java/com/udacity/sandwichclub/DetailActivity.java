package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private TextView name,alsoKnownAs,placeOfOrigin,descriptionT,ingredientsT;
    private Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



//        This is since the detail actvity has been started from the mainActivity
        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

//        From the mainActivity was sent the position of the sandwich
        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
//        This returns the particular string that has the sandwich
//        Will redo with a string later within the code
        String json = sandwiches[position];

//        This method should return a sandwich of this particular position

         sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();


        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

//    Need to design this method for it populates the textViews
    private void populateUI()
    {
//       This is the method where I set the text of the textViews

//        This is the text that will need to be set to the textViews
        String mainname , placeoforigin , description;
        List<String> alsoknownaas = new ArrayList<String>();
        List<String> ingredients = new ArrayList<String>();
        mainname = sandwich.getMainName();
        placeoforigin = sandwich.getPlaceOfOrigin();
        description = sandwich.getDescription();
        alsoknownaas = sandwich.getAlsoKnownAs();
        ingredients = sandwich.getIngredients();

//        This now is the textViews
        name = (TextView)findViewById(R.id.name_tv);
        alsoKnownAs = (TextView)findViewById(R.id.alsoKnownAs);
        placeOfOrigin = (TextView) findViewById(R.id.placeOfOrigin);
        descriptionT= (TextView)findViewById(R.id.description1);
        ingredientsT = (TextView) findViewById(R.id.ingredientsId);
        ImageView ingredientsIv = findViewById(R.id.image_iv);

        name.setText(mainname);
        placeOfOrigin.setText(placeoforigin);
        descriptionT.setText(description);

//        This is for appending the alsoKnownAs Array to the textView
        for (int i=0; i<alsoknownaas.size();i++){
            alsoKnownAs.append(alsoknownaas.get(i) + " , " + " ");
        }

//        This is for appending the ingredients to the the textView
        for (int i=0; i<ingredients.size();i++){
            ingredientsT.append(ingredients.get(i) + " , " + " ");
        }
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);
    }
}

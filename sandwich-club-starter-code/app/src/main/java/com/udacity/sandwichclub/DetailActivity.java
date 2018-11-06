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
import com.udacity.sandwichclub.utils.StringUtils;

/**
 * Activity Class to display Activity Detail
 */

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(imageView);

        setTitle(sandwich.getMainName());
    }

    /**
     * display a Toast if no xml to read
     */
    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    /**
     * A method to populate the User Interface of Sandwich Detail Screen
     * @param sandwich
     */
    private void populateUI(Sandwich sandwich) {
        populateAlternateNameTextView(sandwich);
        populateDescriptionTextView(sandwich);
        populateIngredientsTextView(sandwich);
        populatePlaceOfOriginTextView(sandwich);
    }

    /**
     * Populate alternate name
     * @param sandwich
     */
    private void populateAlternateNameTextView(Sandwich sandwich) {
        TextView alternative_name = findViewById(R.id.also_known_tv);
        alternative_name.setText(StringUtils.getAppendedString(sandwich.getAlsoKnownAs()));
    }

    /**
     * Populate description
     * @param sandwich
     */
    private void populateDescriptionTextView(Sandwich sandwich) {
        TextView alternative_name = findViewById(R.id.description_tv);
        alternative_name.setText(sandwich.getDescription());
    }

    /**
     * Popolate ingredients
     * @param sandwich
     */
    private void populateIngredientsTextView(Sandwich sandwich) {
        TextView alternative_name = findViewById(R.id.ingredients_tv);
        alternative_name.setText(StringUtils.getAppendedString(sandwich.getIngredients()));
    }

    /**
     * Populate place of origin
     * @param sandwich
     */
    private void populatePlaceOfOriginTextView(Sandwich sandwich) {
        TextView alternative_name = findViewById(R.id.origin_tv);
        alternative_name.setText(sandwich.getPlaceOfOrigin());
    }


}

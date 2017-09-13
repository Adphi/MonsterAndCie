package be.heroesofmightandmagic.www.monsterandcie;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static be.heroesofmightandmagic.www.monsterandcie.R.id.editTextLevelValue;

public class DetailsActivity extends AppCompatActivity {

    int monsterLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        // Get the Monster Name from the Extra Data of the Intent
        String monsterNameFormated = getIntent().getStringExtra("monsterName");

        final String monsterName = monsterNameFormated.contains(" ") ? monsterNameFormated.replace(" ", "_").toLowerCase() : monsterNameFormated.toLowerCase();

        setTitle(monsterNameFormated);

        ImageView evolEggImageView = (ImageView) findViewById(R.id.evolEgg);
        ImageView evol1ImageView = (ImageView) findViewById(R.id.evol1);
        ImageView evol2ImageView = (ImageView) findViewById(R.id.evol2);
        ImageView evol3ImageView = (ImageView) findViewById(R.id.evol3);
        final ImageView charLevelImageView = (ImageView) findViewById(R.id.characterLevelImage);

        // Get the textview

        final TextView powerTextView = (TextView) findViewById(R.id.powerLevelLabel);
        TextView lifeTextView = (TextView) findViewById(R.id.lifeLevelLabel);
        TextView speedTextView = (TextView) findViewById(R.id.speedLevelLabel);
        TextView staminaTextView = (TextView) findViewById(R.id.staminaLevelLabel);
        TextView monsterNameTextView = (TextView) findViewById(R.id.monsterName);

        //CharacterLevlImage
        Drawable charLevelImage = Utils.getResourceDrawableByString(monsterName + "_egg", getApplicationContext());
        charLevelImageView.setImageDrawable(charLevelImage);

        // Egg
        Drawable evolEggImage = Utils.getResourceDrawableByString(monsterName + "_egg", getApplicationContext());
        evolEggImageView.setImageDrawable(evolEggImage);

        //Evol1
        Drawable evol1Image = Utils.getResourceDrawableByString(monsterName + "_evol1", getApplicationContext());
        evol1ImageView.setImageDrawable(evol1Image);

        //Evol2
        Drawable evol2Image = Utils.getResourceDrawableByString(monsterName + "_evol2", getApplicationContext());
        evol2ImageView.setImageDrawable(evol2Image);

        //Evol3
        Drawable evol3Image = Utils.getResourceDrawableByString(monsterName + "_evol3", getApplicationContext());
        evol3ImageView.setImageDrawable(evol3Image);


        // Get the Element

        ImageView elementImageView = (ImageView) findViewById(R.id.elementTypeLogo);
        TextView elementTextView = (TextView) findViewById(R.id.elementTypeLabel);

        String elementText = Utils.getResourceStringByString(monsterName + "_element", getApplicationContext());
        elementTextView.setText("Type : " + elementText);


        String elementRessourceName = elementText;
        elementRessourceName = elementRessourceName.toLowerCase() + "_logo";
        int elementRessourceId = getResources().getIdentifier(elementRessourceName, "drawable", getPackageName());
        Drawable elementImage = getResources().getDrawable(elementRessourceId, getTheme());
        elementImageView.setImageDrawable(elementImage);



        monsterNameTextView.setText(monsterNameFormated);

        // Get JSON Data back
        String jsonDataString = Utils.getResourceStringByString(monsterName + "_data", getApplicationContext());

        // Execption handling for malformed JSON Data
        try {
            // Get Data from JSON
            JSONObject jsonData = new JSONObject(jsonDataString);

            // Get Element Values Array
            JSONArray powerValues = jsonData.getJSONArray("power");

            // Get Value for the Monster's Level
            String powerValueBase32 = powerValues.getString(monsterLevel);

            // Decode Data from Base 32 to Base 10
            String powerValue = Integer.toString(Integer.parseInt(powerValueBase32, 32), 10);

            // Set Value
            powerTextView.setText(powerValue);

            // Life
            JSONArray lifeValues = jsonData.getJSONArray("life");
            String lifeValueBase32 = lifeValues.getString(monsterLevel);
            String lifeValue = Integer.toString(Integer.parseInt(lifeValueBase32, 32), 10);
            lifeTextView.setText(lifeValue);

            // Speed
            JSONArray speedValues = jsonData.getJSONArray("speed");
            String speedValueBase32 = speedValues.getString(monsterLevel);
            String speedValue = Integer.toString(Integer.parseInt(speedValueBase32, 32), 10);
            speedTextView.setText(speedValue);

        }
        catch (final JSONException e) {

            Log.e("Monsters&Cie", "Json parsing error: " + e.getMessage());
        }



    }
}

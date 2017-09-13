package be.heroesofmightandmagic.www.monsterandcie;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends AppCompatActivity {


    private float posX1, posX2;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Set the custom TYPO
        Typeface typo = Typeface.createFromAsset(getAssets(),"fonts/Curse Casual.ttf");

        TextView evolutionLevel = (TextView) findViewById(R.id.evolutionLevel);
        evolutionLevel.setTypeface(typo);
        TextView statLabel = (TextView) findViewById(R.id.statsLabel);
        statLabel.setTypeface(typo);
        TextView lifeTextView = (TextView) findViewById(R.id.lifeLevelLabel);
        lifeTextView.setTypeface(typo);
        TextView speedTextView = (TextView) findViewById(R.id.speedLevelLabel);
        speedTextView.setTypeface(typo);
        TextView staminaText = (TextView) findViewById(R.id.textStamina);
        staminaText.setTypeface(typo);
        TextView speedText = (TextView) findViewById(R.id.textSpeed);
        speedText.setTypeface(typo);
        TextView lifeText = (TextView) findViewById(R.id.textLife);
        lifeText.setTypeface(typo);
        TextView powerText = (TextView) findViewById(R.id.textPower);
        powerText.setTypeface(typo);

        // Get the Monster Name from the Extra Data of the Intent
        currentIndex = getIntent().getIntExtra("monsterNameIndex", 0);
        String monsterNameFormated = MyAdapter.monstersNameList[currentIndex];

        setTitle(monsterNameFormated);
        final String monsterName = monsterNameFormated.contains(" ") ? monsterNameFormated.replace(" ", "_").toLowerCase() : monsterNameFormated.toLowerCase();

        // Set the Name
        TextView monsterNameTextView = (TextView) findViewById(R.id.monsterName);
        monsterNameTextView.setText(monsterNameFormated);
        monsterNameTextView.setTypeface(typo);

        // Set the background
        ConstraintLayout root = (ConstraintLayout) findViewById(R.id.root);
        int rootImage = getResources().getIdentifier(monsterName + "_habitat", "drawable", getPackageName());
        Drawable rootImag = getResources().getDrawable(rootImage, getTheme());
        rootImag.setAlpha(130);
        root.setBackground(rootImag);

        // Egg
        Drawable evolEggImage = Utils.getResourceDrawableByString(monsterName + "_egg", getApplicationContext());
        ImageView evolEggImageView = (ImageView) findViewById(R.id.evolEgg);
        evolEggImageView.setImageDrawable(evolEggImage);

        //Evol1
        ImageView evol1ImageView = (ImageView) findViewById(R.id.evol1);
        Drawable evol1Image = Utils.getResourceDrawableByString(monsterName + "_evol1", getApplicationContext());
        evol1ImageView.setImageDrawable(evol1Image);

        //Evol2
        ImageView evol2ImageView = (ImageView) findViewById(R.id.evol2);
        Drawable evol2Image = Utils.getResourceDrawableByString(monsterName + "_evol2", getApplicationContext());
        evol2ImageView.setImageDrawable(evol2Image);

        //Evol3
        Drawable evol3Image = Utils.getResourceDrawableByString(monsterName + "_evol3", getApplicationContext());
        ImageView evol3ImageView = (ImageView) findViewById(R.id.evol3);
        evol3ImageView.setImageDrawable(evol3Image);

        // Get the Element
        ImageView elementImageView = (ImageView) findViewById(R.id.elementTypeLogo);
        TextView elementTextView = (TextView) findViewById(R.id.elementTypeLabel);
        String elementText = Utils.getResourceStringByString(monsterName + "_element", getApplicationContext());
        elementTextView.setText(elementText);
        elementTextView.setTypeface(typo);

        String elementRessourceName = elementText;
        elementRessourceName = elementRessourceName.toLowerCase() + "_logo";
        int elementRessourceId = getResources().getIdentifier(elementRessourceName, "drawable", getPackageName());
        Drawable elementImage = getResources().getDrawable(elementRessourceId, getTheme());
        elementImageView.setImageDrawable(elementImage);

        // Set Stamina (constant over levels)
        String staminaLevel = Utils.getResourceStringByString(monsterName + "_stamina", getApplicationContext());
        TextView staminaTextView = (TextView) findViewById(R.id.staminaLevelLabel);
        staminaTextView.setText(staminaLevel);
        staminaTextView.setTypeface(typo);

        // Set Data according to default Level
        setData(monsterName, 0);

        // Level Picker
        NumberPicker levelPicker = (NumberPicker) findViewById(R.id.levelPicker);
        levelPicker.setMinValue(0);
        levelPicker.setMaxValue(70);
        levelPicker.setWrapSelectorWheel(true);

        levelPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldLevel, int newLevel) {
                setData(monsterName, newLevel);
            }
        });
    }

    public void setData(String monsterName, int monsterLevel) {


        //CharacterLevelImage

        if (monsterLevel >= 7) {
        Drawable levelImage = Utils.getResourceDrawableByString(monsterName + "_evol3", getApplicationContext());
        ImageView levelImageView = (ImageView) findViewById(R.id.characterLevelImage);
        levelImageView.setImageDrawable(levelImage); }

        else if (monsterLevel >= 4 && monsterLevel <= 6) {
            Drawable levelImage = Utils.getResourceDrawableByString(monsterName + "_evol2", getApplicationContext());
            ImageView levelImageView = (ImageView) findViewById(R.id.characterLevelImage);
            levelImageView.setImageDrawable(levelImage); }


        else if (monsterLevel >= 1 && monsterLevel <= 3) {
            Drawable levelImage = Utils.getResourceDrawableByString(monsterName + "_evol1", getApplicationContext());
            ImageView levelImageView = (ImageView) findViewById(R.id.characterLevelImage);
            levelImageView.setImageDrawable(levelImage); }

        else {
            Drawable levelImage = Utils.getResourceDrawableByString(monsterName + "_egg", getApplicationContext());
            ImageView levelImageView = (ImageView) findViewById(R.id.characterLevelImage);
            levelImageView.setImageDrawable(levelImage); }



        // Get JSON Data back
        String jsonDataString = Utils.getResourceStringByString(monsterName + "_data", getApplicationContext());

        // Execption handling for malformed JSON Data
        try {
            // Get Data from JSON
            JSONObject jsonData = new JSONObject(jsonDataString);

            // Get Element Values Array
            JSONArray powerValues = jsonData.getJSONArray("power");

            // Get Value for the Monster's Level
            String powerValue = powerValues.getString(monsterLevel);

            // Get the text View
            TextView powerTextView = (TextView) findViewById(R.id.powerLevelLabel);

            // Set Value
            powerTextView.setText(powerValue);

            // Life
            JSONArray lifeValues = jsonData.getJSONArray("life");
            String lifeValue = lifeValues.getString(monsterLevel);
            TextView lifeTextView = (TextView) findViewById(R.id.lifeLevelLabel);
            lifeTextView.setText(lifeValue);

            // Speed
            JSONArray speedValues = jsonData.getJSONArray("speed");
            String speedValue = speedValues.getString(monsterLevel);
            TextView speedTextView = (TextView) findViewById(R.id.speedLevelLabel);
            speedTextView.setText(speedValue);

        }
        catch (final JSONException e) {
            // Simple debug message
            Log.e("Monsters&Cie", "Json parsing error: " + e.getMessage());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                posX1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                posX2 = event.getX();
                float deltaX = posX2 - posX1;
                if (deltaX > 0) {
                    if (currentIndex > 0) {
                        Intent intent = new Intent(this, DetailsActivity.class);
                        intent.putExtra("monsterNameIndex", currentIndex - 1);
                        startActivity(intent);
                    }
                }
                else if (deltaX < 0) {
                    if (currentIndex < 70) {
                        Intent intent = new Intent(this, DetailsActivity.class);
                        intent.putExtra("monsterNameIndex", currentIndex + 1);
                        startActivity(intent);
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }
    // Back to MainActivity
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

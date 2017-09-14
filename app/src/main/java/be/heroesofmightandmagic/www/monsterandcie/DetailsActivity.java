package be.heroesofmightandmagic.www.monsterandcie;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends Fragment {

    public static String MONSTERNAME_INDEX;
    private View rootView;

    public DetailsActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.activity_details, container, false);

        //Set the custom TYPO
        Typeface typo = Typeface.createFromAsset(getContext().getAssets(),"fonts/Curse Casual.ttf");

        TextView evolutionLevel = (TextView) rootView.findViewById(R.id.evolutionLevel);
        evolutionLevel.setTypeface(typo);
        TextView statLabel = (TextView) rootView.findViewById(R.id.statsLabel);
        statLabel.setTypeface(typo);
        TextView lifeTextView = (TextView) rootView.findViewById(R.id.lifeLevelLabel);
        lifeTextView.setTypeface(typo);
        TextView speedTextView = (TextView) rootView.findViewById(R.id.speedLevelLabel);
        speedTextView.setTypeface(typo);
        TextView staminaText = (TextView) rootView.findViewById(R.id.textStamina);
        staminaText.setTypeface(typo);
        TextView speedText = (TextView) rootView.findViewById(R.id.textSpeed);
        speedText.setTypeface(typo);
        TextView lifeText = (TextView) rootView.findViewById(R.id.textLife);
        lifeText.setTypeface(typo);
        TextView powerText = (TextView) rootView.findViewById(R.id.textPower);
        powerText.setTypeface(typo);

        // Get the Monster Name from the Extra Data of the Intent
        int monsterNameIndex = Integer.valueOf(getArguments().getInt(MONSTERNAME_INDEX));
        String monsterNameFormated = MyAdapter.monstersNameList[monsterNameIndex];

        final String monsterName = monsterNameFormated.contains(" ") ? monsterNameFormated.replace(" ", "_").toLowerCase() : monsterNameFormated.toLowerCase();

        // Set the Name
        TextView monsterNameTextView = (TextView) rootView.findViewById(R.id.monsterName);
        monsterNameTextView.setText(monsterNameFormated);
        monsterNameTextView.setTypeface(typo);

        // Set the background
        ConstraintLayout root = (ConstraintLayout) rootView.findViewById(R.id.root);
        int rootImage = getResources().getIdentifier(monsterName + "_habitat", "drawable", getContext().getPackageName());
        Drawable rootImag = getResources().getDrawable(rootImage, getContext().getTheme());
        rootImag.setAlpha(130);
        root.setBackground(rootImag);

        // Get the Element
        ImageView elementImageView = (ImageView) rootView.findViewById(R.id.elementTypeLogo);
        TextView elementTextView = (TextView) rootView.findViewById(R.id.elementTypeLabel);
        String elementText = Utils.getResourceStringByString(monsterName + "_element", getContext());
        elementTextView.setText(elementText);
        elementTextView.setTypeface(typo);

        String elementRessourceName = elementText;
        elementRessourceName = elementRessourceName.toLowerCase() + "_logo";
        int elementRessourceId = getResources().getIdentifier(elementRessourceName, "drawable", getContext()
                .getPackageName());
        Drawable elementImage = getResources().getDrawable(elementRessourceId, getContext().getTheme());
        elementImageView.setImageDrawable(elementImage);

        // Set Stamina (constant over levels)
        String staminaLevel = Utils.getResourceStringByString(monsterName + "_stamina", getContext());
        TextView staminaTextView = (TextView) rootView.findViewById(R.id.staminaLevelLabel);
        staminaTextView.setText(staminaLevel);
        staminaTextView.setTypeface(typo);

        // Set Data according to default Level
        setData(monsterName, 0);

        // Level Picker
        NumberPicker levelPicker = (NumberPicker) rootView.findViewById(R.id.levelPicker);
        levelPicker.setMinValue(0);
        levelPicker.setMaxValue(70);
        levelPicker.setWrapSelectorWheel(true);

        levelPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldLevel, int newLevel) {
                setData(monsterName, newLevel);
            }
        });
        return rootView;
    }

    /**
     * Method updating Data in the layout according to the level
     * @param monsterName
     * @param monsterLevel
     */
    public void setData(String monsterName, int monsterLevel) {

        //CharacterLevelImage
        Drawable evol1Image;
        Drawable evol2Image;
        Drawable evol3Image;

        if (monsterLevel >= 7) {
            Drawable levelImage = Utils.getResourceDrawableByString(monsterName + "_evol3", getContext());
            ImageView levelImageView = (ImageView) rootView.findViewById(R.id.characterLevelImage);
            levelImageView.setImageDrawable(levelImage);
            evol1Image = Utils.getResourceDrawableByString(monsterName + "_egg", getContext());
            evol2Image = Utils.getResourceDrawableByString(monsterName + "_evol1", getContext());
            evol3Image = Utils.getResourceDrawableByString(monsterName + "_evol2", getContext());


        }

        else if (monsterLevel >= 4 && monsterLevel <= 6) {
            Drawable levelImage = Utils.getResourceDrawableByString(monsterName + "_evol2", getContext());
            ImageView levelImageView = (ImageView) rootView.findViewById(R.id.characterLevelImage);
            levelImageView.setImageDrawable(levelImage);
            evol1Image = Utils.getResourceDrawableByString(monsterName + "_egg", getContext());
            evol2Image = Utils.getResourceDrawableByString(monsterName + "_evol1", getContext());
            evol3Image = Utils.getResourceDrawableByString(monsterName + "_evol3", getContext());

        }

        else if (monsterLevel >= 1 && monsterLevel <= 3) {
            Drawable levelImage = Utils.getResourceDrawableByString(monsterName + "_evol1", getContext());
            ImageView levelImageView = (ImageView) rootView.findViewById(R.id.characterLevelImage);
            levelImageView.setImageDrawable(levelImage);
            evol1Image = Utils.getResourceDrawableByString(monsterName + "_egg", getContext());
            evol2Image = Utils.getResourceDrawableByString(monsterName + "_evol2", getContext());
            evol3Image = Utils.getResourceDrawableByString(monsterName + "_evol3", getContext());
        }

        else {
            Drawable levelImage = Utils.getResourceDrawableByString(monsterName + "_egg", getContext());
            ImageView levelImageView = (ImageView) rootView.findViewById(R.id.characterLevelImage);
            levelImageView.setImageDrawable(levelImage);
            evol1Image = Utils.getResourceDrawableByString(monsterName + "_evol1", getContext());
            evol2Image = Utils.getResourceDrawableByString(monsterName + "_evol2", getContext());
            evol3Image = Utils.getResourceDrawableByString(monsterName + "_evol3", getContext());
        }

        //Evol1
        ImageView evol1ImageView = (ImageView) rootView.findViewById(R.id.evolImage1);
        evol1ImageView.setImageDrawable(evol1Image);

        //Evol2
        ImageView evol2ImageView = (ImageView) rootView.findViewById(R.id.evolImage2);
        evol2ImageView.setImageDrawable(evol2Image);

        //Evol3
        ImageView evol3ImageView = (ImageView) rootView.findViewById(R.id.evolImage3);
        evol3ImageView.setImageDrawable(evol3Image);

        // Get JSON Data back
        String jsonDataString = Utils.getResourceStringByString(monsterName + "_data", getContext());

        // Execption handling for malformed JSON Data
        try {
            // Get Data from JSON
            JSONObject jsonData = new JSONObject(jsonDataString);

            // Get Element Values Array
            JSONArray powerValues = jsonData.getJSONArray("power");

            // Get Value for the Monster's Level
            String powerValue = powerValues.getString(monsterLevel);

            // Get the text View
            TextView powerTextView = (TextView) rootView.findViewById(R.id.powerLevelLabel);

            // Set Value
            powerTextView.setText(powerValue);

            // Life
            JSONArray lifeValues = jsonData.getJSONArray("life");
            String lifeValue = lifeValues.getString(monsterLevel);
            TextView lifeTextView = (TextView) rootView.findViewById(R.id.lifeLevelLabel);
            lifeTextView.setText(lifeValue);

            // Speed
            JSONArray speedValues = jsonData.getJSONArray("speed");
            String speedValue = speedValues.getString(monsterLevel);
            TextView speedTextView = (TextView) rootView.findViewById(R.id.speedLevelLabel);
            speedTextView.setText(speedValue);

        }
        catch (final JSONException e) {
            // Simple debug message
            Log.e("Monsters&Cie", "Json parsing error: " + e.getMessage());
        }
    }

}

package be.heroesofmightandmagic.www.monsterandcie;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import static be.heroesofmightandmagic.www.monsterandcie.R.id.elementTypeLabel;
import static be.heroesofmightandmagic.www.monsterandcie.R.id.imageView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Get the Monster Name from the Extra Data of the Intent
        String monsterName = getIntent().getStringExtra("monsterName");

        // TODO: Set the Monster's Data in the Page
        // In order to get the resource we need to create
        // the string for the name of the resource image


        // In order to get the resource we need to create
        // the string for the name of the resource image
        //String monsterResourceName = monsterName + "_evol3";

        // Get the resource ID of the Image to Display
        //int monsterResourceId = itemView.getResources().getIdentifier(monsterResourceName,
        //        "drawable", context.getPackageName());

        // Get the Image from Resources with ID
        //Drawable monsterImage = itemView.getResources().getDrawable(monsterResourceId, context.getTheme());

        // Set the Image
        //imageView.setImageDrawable(monsterImage);

        ImageView evolEggImageView = (ImageView) findViewById(R.id.evolEgg);
        ImageView evol1ImageView = (ImageView) findViewById(R.id.evol1);
        ImageView evol2ImageView = (ImageView) findViewById(R.id.evol2);
        ImageView evol3ImageView = (ImageView) findViewById(R.id.evol3);
        ImageView charLevelImageView = (ImageView) findViewById(R.id.characterLevelImage);

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

        // String test = "Prout";
        // test = test.toLowerCase()

        ImageView elementImageView = (ImageView) findViewById(R.id.elementTypeLogo);
        TextView elementTextView = (TextView) findViewById(R.id.elementTypeLabel);

        String elementText = Utils.getResourceStringByString(monsterName + "_element", getApplicationContext());
        elementTextView.setText("Type : " + elementText);


        String elementRessourceName = elementText;
        elementRessourceName = elementRessourceName.toLowerCase() + "_logo";
        int elementRessourceId = getResources().getIdentifier(elementRessourceName, "drawable", getPackageName());
        Drawable elementImage = getResources().getDrawable(elementRessourceId, getTheme());
        elementImageView.setImageDrawable(elementImage);


        // Get the textview

        TextView powerTextView = (TextView) findViewById(R.id.powerLevelLabel);
        TextView lifeTextView = (TextView) findViewById(R.id.lifeLevelLabel);
        TextView speedTextView = (TextView) findViewById(R.id.speedLevelLabel);
        TextView staminaTextView = (TextView) findViewById(R.id.staminaLevelLabel);


        String powerText = Utils.getResourceStringByString(monsterName + "_power", getApplicationContext());
        powerTextView.setText("Power : " + powerText);

        String lifeText = Utils.getResourceStringByString(monsterName + "_life", getApplicationContext());
        lifeTextView.setText("Life : " + lifeText);

        String speedText = Utils.getResourceStringByString(monsterName + "_speed", getApplicationContext());
        speedTextView.setText("Speed : " + speedText);

        String staminaText = Utils.getResourceStringByString(monsterName + "_stamina", getApplicationContext());
        staminaTextView.setText("Stamina : " + staminaText);


    }
}

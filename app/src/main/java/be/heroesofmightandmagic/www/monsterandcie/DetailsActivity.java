package be.heroesofmightandmagic.www.monsterandcie;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
        String charLevelRessourceName = monsterName + "_egg";
        int charLevelRessourceId = getResources().getIdentifier(charLevelRessourceName, "drawable", getPackageName());
        Drawable charLevelImage = getResources().getDrawable(charLevelRessourceId, getTheme());
        charLevelImageView.setImageDrawable(charLevelImage);

        // Egg
        String evolEggRessourceName = monsterName + "_egg";
        int evolEggRessourceId = getResources().getIdentifier(evolEggRessourceName, "drawable", getPackageName());
        Drawable evolEggImage = getResources().getDrawable(evolEggRessourceId, getTheme());
        evolEggImageView.setImageDrawable(evolEggImage);

        //Evol1
        String evol1RessourceName = monsterName + "_evol1";
        int evol1RessourceID = getResources().getIdentifier(evol1RessourceName, "drawable", getPackageName());
        Drawable evol1Image = getResources().getDrawable(evol1RessourceID,getTheme());
        evol1ImageView.setImageDrawable(evol1Image);

        //Evol2
        String evol2RessourceName = monsterName + "_evol2";
        int evol2RessourceId = getResources().getIdentifier(evol2RessourceName,"drawable", getPackageName());
        Drawable evol2Image = getResources().getDrawable(evol2RessourceId,getTheme());
        evol2ImageView.setImageDrawable(evol2Image);

        //Evol3
        String evol3RessourceName = monsterName + "_evol3";
        int evol3RessourceId = getResources().getIdentifier(evol3RessourceName,"drawable", getPackageName());
        Drawable evol3Image = getResources().getDrawable(evol3RessourceId, getTheme());
        evol3ImageView.setImageDrawable(evol3Image);

        // Get the textview

        



    }
}

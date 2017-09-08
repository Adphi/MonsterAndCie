package be.heroesofmightandmagic.www.monsterandcie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Get the Monster Name from the Extra Data of the Intent
        String monsterName = getIntent().getStringExtra("monsterName");

        // TODO: Set the Monster's Data in the Page
        // 

    }
}

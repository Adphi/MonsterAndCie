package be.heroesofmightandmagic.www.monsterandcie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String monsterName = "fire_lion";

        String text = getResources().getString(getResources().getIdentifier(monsterName + "_element", "string", getPackageName()));

        final Button button = (Button) findViewById(R.id.button);
        button.setText(text);
        final Intent intent = new Intent(this, DetailsActivity.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}

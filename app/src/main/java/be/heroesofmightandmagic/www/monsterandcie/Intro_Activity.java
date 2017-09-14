package be.heroesofmightandmagic.www.monsterandcie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Intro_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_);
        final Intent intent = new Intent(this, MainActivity.class);

        ImageView logo = (ImageView) findViewById(R.id.logo);
        TextView myImageView = (TextView) findViewById(R.id.press_logo);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(Intro_Activity.this, R.anim.touch_screen_anim);
        myImageView.startAnimation(myFadeInAnimation);

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
                overridePendingTransition(R.anim.enter_right, R.anim.out_left);
            }
        });
    }
}

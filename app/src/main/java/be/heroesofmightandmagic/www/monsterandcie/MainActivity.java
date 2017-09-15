package be.heroesofmightandmagic.www.monsterandcie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // BackButton pressed counter
    private int backButtonCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the RecyclerView from Resource System
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Stuff from Android doc to improve performances
        mRecyclerView.setHasFixedSize(true);

        // Create the Layout for the RecyclerView to display the Cards
        // ... A Grid Layout, with 2 Columns
        RecyclerView.LayoutManager mLayoutManager;
        int orientation = getResources().getConfiguration().orientation;
        int portrait =  getResources().getConfiguration().ORIENTATION_PORTRAIT;
        if (orientation == portrait) mLayoutManager = new GridLayoutManager(this, 2);
        else mLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Set the Custom Adapter
        mRecyclerView.setAdapter(new MyAdapter(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        backButtonCount = 0;
    }

    // Exit on Back pressed twice else toast
    @Override
    public void onBackPressed() {
        if(backButtonCount > 0) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, getString(R.string.mainBackPressedMessage), Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }
}

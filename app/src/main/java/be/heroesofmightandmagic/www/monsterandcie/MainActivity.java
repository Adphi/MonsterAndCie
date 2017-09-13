package be.heroesofmightandmagic.www.monsterandcie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the RecyclerView from Resource System
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Stuff from Android doc to improve performances
        mRecyclerView.setHasFixedSize(true);

        // Create the Layout for the RecyclerView to display the Cards
        // ... A Grid Layout, with 2 Columns
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Set the Custom Adapter
        mRecyclerView.setAdapter(new MyAdapter());
    }
}

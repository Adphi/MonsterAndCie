package be.heroesofmightandmagic.www.monsterandcie;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by adphi on 07/09/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    // Create the list of the Monsters names
    private static final String[] monstersNameList =
            {"Fire Lion","Panda","Rockilla","Thunder Eagle","Mersnake",
                    "Tyrannoking","Geni","Light Spirit","Arch Knight","Metalsaur"};

    // We need to override this method to know the item's count
    @Override
    public int getItemCount() {
        // Return List Length
        return monstersNameList.length;
    }

    // We override this method to create custom view
    // and set the layout for the content of the card
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_layout, parent, false);
        return new MyViewHolder(view);
    }

    // We need this one too, to set the appropriate data,
    // corresponding to the index of the card in the list
    @Override
    public void onBindViewHolder(MyViewHolder holder, int index) {
        // Set Data into List Item Layout
        holder.setData(index);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        // Declaration of Context, ImageView, Intent (to launch
        // the next Activity on Card Clicked)
        private final Intent intent;

        // Constructor of the ViewHolder ()
        public MyViewHolder(View itemView) {
            super(itemView);
            Context context = itemView.getContext();
            // Get Context in order to access Resources System
            intent = new Intent(context, DetailsActivity.class);

            // Set the Click Listener on the Card to go the Details Avtivity
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.getContext().startActivity(intent);
                }
            });
        }

        // The method setting the Monster Image ...
        public void setData(int index){
            // Get The Contex
            Context context = itemView.getContext();

            // Get the name of the Monster to display in the Card
            String monsterNameFormated = monstersNameList[index];

            // Add the Monster Name to the Details Activity
            intent.putExtra("monsterName", monsterNameFormated);

            String monsterName = monsterNameFormated.contains(" ") ? monsterNameFormated.replace(" ", "_").toLowerCase() : monsterNameFormated.toLowerCase();

            // In order to get the resource we need to create
            // the string for the name of the resource image
            String monsterResourceName = monsterName + "_card";

            // Get the Image from Resources with ID
            Drawable monsterImage = Utils.getResourceDrawableByString(monsterResourceName, context);

            ImageView imageView = itemView.findViewById(R.id.monsterImage);

            // Set the Image
            imageView.setImageDrawable(monsterImage);

            ImageView elementImageView = itemView.findViewById(R.id.monsterElementImage);
            String elementResourceName = monsterName + "_element";
            String elementName = Utils.getResourceStringByString(elementResourceName, context);

            String elementImageName = elementName.toLowerCase() + "_logo";
            Drawable elementImage = Utils.getResourceDrawableByString(elementImageName, context);

            elementImageView.setImageDrawable(elementImage);

            TextView cardMonsterName = (TextView) itemView.findViewById(R.id.cardMonsterName);
            cardMonsterName.setText(monsterNameFormated);

            Typeface typo = Typeface.createFromAsset(context.getAssets(),"fonts/Curse Casual.ttf");
            cardMonsterName.setTypeface(typo);
        }
    }
}
package be.heroesofmightandmagic.www.monsterandcie;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by adphi on 07/09/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    // Create the list of the Monsters names
    private static final String[] monstersNameList =
            {"fire_lion","panda","rockilla","thunder_eagle","mersnake","tyrannoking","geni","light_spirit","arch_knight","metalsaur"};


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
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    // We need this one too, to set the appropriate data,
    // corresponding to the index of the card in the list
    @Override
    public void onBindViewHolder(MyViewHolder holder, int index) {
        // Set Data into List Item Layout
        holder.setData(index);
    }


    //
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private final ImageView imageView;

        // Constructor of the ViewHolder ()
        public MyViewHolder(View itemView) {
            super(itemView);
            // Get Context in order to access Resources System
            context = itemView.getContext();
            // Get the Image View
            imageView = itemView.findViewById(R.id.monsterImage);

            // Set the Click Listener on the Card to go the Details Avtivity
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Intent intent = new Intent(context, DetailsActivity.class);
                    //intent.putExtra("data", articleData);
                    context.startActivity(intent);
                }
            });
        }

        // The method setting the Monster Image ...
        public void setData(int index){
            // Get the name of the Monster to display in the Card
            String monsterName = monstersNameList[index];
            // In order to get the resource we need to create
            // the string for the name of the resource image
            String monsterResourceName = monsterName + "_evol3";

            // Get the resource ID of the Image to Display
            int monsterResourceId = itemView.getResources().getIdentifier(monsterResourceName, "drawable", context.getPackageName());

            // Get the Image from Resources with ID
            Drawable monsterImage = itemView.getResources().getDrawable(monsterResourceId, context.getTheme());

            // Set the Image
            imageView.setImageDrawable(monsterImage);
        }
    }
;
}
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

    private static final String[] monstersNameList =
            {"fire_lion","panda","rockilla","thunder_eagle","mersnake","tyrannoking","geni","light_spirit","arch_knight","metalsaur"};


    @Override
    public int getItemCount() {
        // Return List Length
        return monstersNameList.length;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // Set Data into List Item Layout
        holder.display(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private final ImageView nameText;

        public MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            nameText = itemView.findViewById(R.id.monsterImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Intent intent = new Intent(context, DetailsActivity.class);
                    //intent.putExtra("data", articleData);
                    context.startActivity(intent);
                }
            });
        }

        public void display(int index){
            String monsterName = monstersNameList[index];
            String monsterResourceName = monsterName + "_evol3";
            int monsterResourceId = itemView.getResources().getIdentifier(monsterResourceName, "drawable", context.getPackageName());

            Drawable monsterImage = itemView.getResources().getDrawable(monsterResourceId, context.getTheme());
            nameText.setImageDrawable(monsterImage);
        }
    }
;
}
package com.thomas.test.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.thomas.test.R;
import com.thomas.test.object.Item;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final ClickListener clickListener;
    private List<Item> listItems;


    public RecyclerViewAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        listItems = new ArrayList<>();
    }

    public interface ClickListener{
        void onClickItem(Item item);
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int pos) {
        Item currentItem = listItems.get(pos);
        viewHolder.txtTitre.setText(currentItem.title);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTitre;
        private ImageView imageThumb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageThumb = itemView.findViewById(R.id.itemImageThumb);
            txtTitre = itemView.findViewById(R.id.itemTitre);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClickItem(listItems.get(getAdapterPosition()));
                }
            });
        }
    }

    public void setListItems(List<Item> list){
        listItems.clear();
        listItems.addAll(list);
        notifyDataSetChanged();
    }
}

package com.uchettovarovgeeks.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uchettovarovgeeks.R;
import com.uchettovarovgeeks.databinding.ListCardBinding;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Item;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.RecordHolder> {
    private Item[] listItems;
    private OnItemClickListener itemClickListener;
    private LayoutInflater inflater;


    public interface OnItemClickListener {
        void onItemClick(View view, Item position);

        void onItemClickQts(View v, Item item);
    }

    public void SetOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ItemListAdapter(Item[] listItems, LayoutInflater inflater) {
        this.listItems = listItems;
        this.inflater = inflater;
    }

    public void setListItems(Item[] listItems) {
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public ItemListAdapter.RecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemListAdapter.RecordHolder(inflater.inflate(R.layout.list_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListAdapter.RecordHolder holder, int position) {
        holder.setData(listItems[position]);
    }

    @Override
    public int getItemCount() {
        return listItems.length;
    }

    public class RecordHolder extends RecyclerView.ViewHolder {
        ListCardBinding ui;
        Item item;

        public RecordHolder(@NonNull View view) {
            super(view);
            ui = ListCardBinding.bind(view);
            ui.itemName.setOnClickListener(v -> {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(v, item);
                }
            });

            ui.itemQuantity.setOnClickListener(v -> {
                if (itemClickListener != null) {
                    itemClickListener.onItemClickQts(v, item);
                }
            });
        }

        public void setData(Item listItem) {
            item = listItem;
            ui.itemName.setText(listItem.getName());
            ui.itemSku.setText(listItem.getSku());
            ui.itemQuantity.setText("" + listItem.getTotalQty());
            ui.itemUnit.setText("" + listItem.getUnit().getName());

        }
    }
}

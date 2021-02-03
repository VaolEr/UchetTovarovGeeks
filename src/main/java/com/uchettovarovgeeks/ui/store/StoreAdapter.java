package com.uchettovarovgeeks.ui.store;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.uchettovarovgeeks.R;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Item;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Storehouse;
import com.uchettovarovgeeks.jsonModel.jsonObjects.StorehousesBalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    private final ArrayList<StorehousesBalance> mValues;
    private final HashMap<Integer, Storehouse> storehouses;
    private final String[] storehousesString;
    private final Item item;
    private ArrayAdapter<String> arrayAdapter;

    public StoreAdapter(ArrayList<StorehousesBalance> storehousesBalances, List<Storehouse> storehouses, String[] storehousesString, Item item) {
        mValues = storehousesBalances;
        this.storehousesString = storehousesString;
        this.item = item;
        this.storehouses = new HashMap<>();
        for (Storehouse s : storehouses
        ) {
            this.storehouses.put(s.getID(), s);
        }
    }

    public ArrayList<StorehousesBalance> getmValues() {
        return mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        mValues.get(position).setName(storehouses.get(mValues.get(position).getId()).getName());
        holder.mIdView.setSelection(arrayAdapter.getPosition(mValues.get(position).getName()));

        holder.mContentView.setText("" + mValues.get(position).getQuantity());
        holder.mUnitView.setText("" + item.getUnit().getName());

        holder.mContentView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                mValues.get(position).setQuantity(Integer.parseInt(v.getText().toString()));

                return false;

            }
        });
        holder.mContentView.setOnFocusChangeListener(
                (v, hasFocus) -> {
                    if (!hasFocus) {
                        mValues.get(position).setQuantity(Integer.parseInt((((TextView) v).getText().toString())));
                    }
                });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final Spinner mIdView;
        public final TextView mContentView;
        public StorehousesBalance mItem;
        public final TextView mUnitView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (Spinner) view.findViewById(R.id.spinner);
            arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, storehousesString);
            mIdView.setAdapter(arrayAdapter);
            mContentView = (TextView) view.findViewById(R.id.content);
            this.mUnitView = (TextView) view.findViewById(R.id.unit);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
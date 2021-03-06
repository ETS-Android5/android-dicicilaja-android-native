package com.dicicilaja.app.BusinessReward.ui.Cart;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicicilaja.app.BusinessReward.dataAPI.delCart.DelCart;
import com.dicicilaja.app.BusinessReward.dataAPI.getCart.Included;
import com.dicicilaja.app.BusinessReward.dataAPI.getCart.Item;
import com.dicicilaja.app.BusinessReward.dataAPI.postCart.PostCart;
import com.dicicilaja.app.BusinessReward.network.ApiClient3;
import com.dicicilaja.app.BusinessReward.network.ApiService3;
import com.dicicilaja.app.R;
import com.dicicilaja.app.Session.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Item> itemList;
    private List<Included> includedList;
    private int rowLayout;
    private Context context;

    private CartCallback mCallback;

    SessionManager session;
    String apiKey;
    ProgressDialog progressBar;


    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView image_cart;
        TextView product_name;
        TextView product_point;
        TextView qty;
        TextView value;
        ImageView plus;
        ImageView minus;
        ImageView delete;

        public CartViewHolder(View v) {
            super(v);
            image_cart      = v.findViewById(R.id.image_cart);
            product_name    = v.findViewById(R.id.product_name);
            product_point   = v.findViewById(R.id.product_point);
            qty             = v.findViewById(R.id.qty);
            value           = v.findViewById(R.id.value);
            plus            = v.findViewById(R.id.plus);
            minus           = v.findViewById(R.id.minus);
            delete          = v.findViewById(R.id.delete);
        }

        public void toggleButtonAddSubstract(boolean isEnable) {
            plus.setEnabled(isEnable);
            minus.setEnabled(isEnable);

            if (isEnable) {
                plus.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
                minus.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
            } else {
                plus.setBackgroundColor(ContextCompat.getColor(context, R.color.colorIndicator));
                minus.setBackgroundColor(ContextCompat.getColor(context, R.color.colorIndicator));
            }
        }
    }

    public CartAdapter(List<Item> items, List<Included> includeds, int rowLayout, Context context, CartCallback mCallback) {
        this.itemList = items;
        this.includedList = includeds;
        this.rowLayout = rowLayout;
        this.context = context;
        this.mCallback = mCallback;
    }

    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new CartAdapter.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartAdapter.CartViewHolder holder, int position) {


        session = new SessionManager(context);
        apiKey = "Bearer " + session.getToken();

        Glide.with(context).load(includedList.get(position).getAttributes().getFoto()).centerCrop().into(holder.image_cart);

        String value_title = includedList.get(position).getAttributes().getNama();
        if (value_title.length() >= 35) {
            value_title = value_title.substring(0, 36) + "...";
            holder.product_name.setText(value_title);
        } else {
            holder.product_name.setText(value_title);
        }

        int value_message = includedList.get(position).getAttributes().getPoint();
        holder.product_point.setText(String.valueOf(value_message) + " POINT");
        holder.qty.setText(" x" + itemList.get(position).getCounts());
        holder.value.setText(String.valueOf(itemList.get(position).getCounts()));

        if (itemList.get(position).getCounts() == 99) {
            holder.plus.setEnabled(false);
            holder.plus.setBackgroundColor(ContextCompat.getColor(context, R.color.colorIndicator));
        } else {
            holder.plus.setEnabled(true);
            holder.plus.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        }

        if (itemList.get(position).getCounts() == 1) {
            holder.minus.setEnabled(false);
            holder.minus.setBackgroundColor(ContextCompat.getColor(context, R.color.colorIndicator));
        } else {
            holder.minus.setEnabled(true);
            holder.minus.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        }

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.toggleButtonAddSubstract(false);
                ApiService3 apiService3 = ApiClient3.getClient().create(ApiService3.class);

                Call<PostCart> call = apiService3.postCart(apiKey, String.valueOf(itemList.get(position).getProductId()), "add");
                call.enqueue(new Callback<PostCart>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(Call<PostCart> call, Response<PostCart> response) {
                        holder.toggleButtonAddSubstract(true);
                        mCallback.onUpdateCart();
                        //((Activity) context).finish();
                    }

                    @Override
                    public void onFailure(Call<PostCart> call, Throwable t) {
                    }
                });
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.toggleButtonAddSubstract(false);
                ApiService3 apiService3 = ApiClient3.getClient().create(ApiService3.class);

                Call<PostCart> call = apiService3.postCart(apiKey, String.valueOf(itemList.get(position).getProductId()), "subtract");
                call.enqueue(new Callback<PostCart>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(Call<PostCart> call, Response<PostCart> response) {
                        holder.toggleButtonAddSubstract(true);
                        mCallback.onUpdateCart();
                        //((Activity) context).finish();
                    }

                    @Override
                    public void onFailure(Call<PostCart> call, Throwable t) {
                    }
                });
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onDeleteCart(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public interface CartCallback {
        void onUpdateCart();

        void onDeleteCart(int position);
    }
}

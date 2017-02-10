package com.app.ilovezappos;



/**
 * Created by ketkitrimukhe on 2/8/17.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ketkitrimukhe on 2/5/17.
 */

// Adapter for the shopping cart

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{

    private ArrayList<Product> products;
    private Context context;
    private Activity activity;

    public CartAdapter(ArrayList<Product> products, Context context, Activity activity) {
        this.products = products;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartAdapter.ViewHolder viewHolder, final int i) {

        final String product_name = products.get(i).getProductName();
        final String brand_name = products.get(i).getBrandName();
        final String price = products.get(i).getPrice();
        final String image_url = products.get(i).getThumbnailImageUrl();

        viewHolder.product_name.setText(Html.fromHtml(products.get(i).getProductName()));
        viewHolder.brand_name.setText(products.get(i).getBrandName());
        viewHolder.price.setText(products.get(i).getPrice());
        Picasso.with(context).load(image_url).into(viewHolder.product_image);

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(context);
                db.deleteRow(product_name);

                Intent intent = activity.getIntent();
                activity.finish();
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView product_name,brand_name,price;
        private ImageView product_image;
        private Button delete;
        public ViewHolder(View view) {
            super(view);

            product_name = (TextView)view.findViewById(R.id.cart_product_name);
            brand_name = (TextView)view.findViewById(R.id.cart_brand_name);
            price = (TextView)view.findViewById(R.id.cart_price);
            product_image = (ImageView)view.findViewById(R.id.cart_product_image);
            delete = (Button) view.findViewById(R.id.delete_button);
        }
    }

}

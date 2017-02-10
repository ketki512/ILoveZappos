package com.app.ilovezappos;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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
// Adapter for the product
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    private ArrayList<Product> products;
    private Context context;
    TextView badge;

    public ProductAdapter(ArrayList<Product> products, Context context, TextView badge) {
        this.products = products;
        this.context = context;
        this.badge=badge;
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder viewHolder, int i) {

        final String product_name = products.get(i).getProductName();
        final String brand_name = products.get(i).getBrandName();
        final String price = products.get(i).getPrice();
        final String image_url = products.get(i).getThumbnailImageUrl();


        viewHolder.product_name.setText(Html.fromHtml(products.get(i).getProductName()));
        viewHolder.brand_name.setText(products.get(i).getBrandName());
        viewHolder.price.setText(products.get(i).getPrice());
        Picasso.with(context).load(products.get(i).getThumbnailImageUrl()).into(viewHolder.product_image);

        viewHolder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product();
                product.setPrice(price);
                product.setProductName(product_name);
                product.setBrandName(brand_name);
                product.setThumbnailImageUrl(image_url);

                DatabaseHandler db = new DatabaseHandler(context);
                db.addProduct(product);

                long count = db.getCount();
                if (count > 0) {
                    badge.setVisibility(View.VISIBLE);
                    badge.setText(String.valueOf(count));
                }
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
        private Button addButton;
        public ViewHolder(View view) {
            super(view);

            product_name = (TextView)view.findViewById(R.id.product_name);
            brand_name = (TextView)view.findViewById(R.id.brand_name);
            price = (TextView)view.findViewById(R.id.price);
            product_image = (ImageView)view.findViewById(R.id.product_image);
            addButton = (Button) view.findViewById(R.id.add_button);
        }
    }

}

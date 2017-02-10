package com.app.ilovezappos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

/**
 * Created by ketkitrimukhe on 2/7/17.
 */

// displays the shopping cart of the user
public class CartActivity extends AppCompatActivity {

    private RecyclerView cartRecyclerView;

    DatabaseHandler db = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);

        setSupportActionBar(toolbar);

        cartRecyclerView = (RecyclerView) findViewById(R.id.cart_recycler_view);
        cartRecyclerView.setHasFixedSize(true);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        CartAdapter adapter = new CartAdapter(db.getAllProducts(), getApplicationContext(), this);
        cartRecyclerView.setAdapter(adapter);

    }
}
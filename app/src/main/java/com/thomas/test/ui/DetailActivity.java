package com.thomas.test.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.thomas.test.R;
import com.thomas.test.glide.GlideApp;
import com.thomas.test.object.Item;


public class DetailActivity extends AppCompatActivity {

    private final static Drawable defaultColorDrawable = new ColorDrawable(Color.LTGRAY);
    public final static String ITEM_TAG = "item";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        Item item = (Item) getIntent().getSerializableExtra(ITEM_TAG);
        ImageView imageItem = findViewById(R.id.detailImageView);
        TextView titleItem = findViewById(R.id.detailTitle);
        titleItem.setText(item.title);

        GlideApp
                .with(this)
                .load(item.url)
                .override(400,400)
                .fitCenter()
                .placeholder(defaultColorDrawable)
                .into(imageItem);
    }
}

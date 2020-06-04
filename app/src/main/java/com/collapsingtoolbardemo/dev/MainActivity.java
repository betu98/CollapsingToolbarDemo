package com.collapsingtoolbardemo.dev;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class MainActivity extends AppCompatActivity {

    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Toolbar Title");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView toolbarTopTitle =  (TextView)findViewById(R.id.tv_toolbar_top_title);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if(verticalOffset == 0)
                {
                    toolbar.setTitleTextColor(Color.TRANSPARENT);
                }
                else
                {

                    float alpha = ((float)Math.abs(verticalOffset))/scrollRange;
                    int color = Color.WHITE;
                    color = (color & 0x00ffffff) | ((byte)(alpha * 255) << 24);
                    toolbar.setTitleTextColor(color);

                    int topColor = Color.WHITE;
                    topColor = (topColor & 0x00ffffff) | ((byte)((1-alpha) * 255) << 24);

                    toolbarTopTitle.setTextColor(topColor);
                }

                if(scrollRange + verticalOffset == 0)
                {
                    toolbarTopTitle.setTextColor(Color.TRANSPARENT);
                }
            }
        });
    }
}

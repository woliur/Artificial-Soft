package com.woliur.artificialsoftwoliur.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.woliur.artificialsoftwoliur.R;

public class DetailsActivity extends AppCompatActivity {

    ImageView ivPic;
    TextView tvName;
    TextView tvNumber;
    TextView tvWho;
    TextView tvId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ivPic = findViewById(R.id.iv_detail_pic);
        tvName = findViewById(R.id.tv_details_name);
        tvNumber = findViewById(R.id.tv_details_phone);
        tvWho = findViewById(R.id.tv_details_who);
        tvId = findViewById(R.id.tv_details_id);

        Bundle bundle = getIntent().getExtras();

        String id = bundle.getString("id");
        String user = bundle.getString("number");
        String name = bundle.getString("name");
        String who = bundle.getString("who");
        String image = bundle.getString("pic");

        Glide.with(this)
                .asBitmap()
                .load(image)
                .into(ivPic);

        tvName.setText(name);
        tvWho.setText(who);
        tvId.setText("Id: "+id);
        tvNumber.setText("Phone: "+user);
    }

}

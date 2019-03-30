package com.woliur.artificialsoftwoliur.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.woliur.artificialsoftwoliur.R;
import com.woliur.artificialsoftwoliur.activity.DetailsActivity;
import com.woliur.artificialsoftwoliur.model.ProfileModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    Context context;
    ArrayList<ProfileModel> userList;

    public RecycleViewAdapter(Context context, ArrayList<ProfileModel> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.listName.setText(userList.get(position).getName());
        Glide.with(context)
                .asBitmap()
                .load(userList.get(position).getImage())
                .into(viewHolder.listImage);


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView listImage;
        TextView listName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            listImage = itemView.findViewById(R.id.iv_list_image);
            listName = itemView.findViewById(R.id.tv_list_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ProfileModel profileModel = userList.get(getAdapterPosition());
                    Intent intent = new Intent(context, DetailsActivity.class);

                    intent.putExtra("pic",profileModel.getImage());
                    intent.putExtra("who",profileModel.getWho());
                    intent.putExtra("name",profileModel.getName());
                    intent.putExtra("number",profileModel.getUser());
                    intent.putExtra("id",profileModel.getId());

                    context.startActivity(intent);
                }
            });
        }
    }
}

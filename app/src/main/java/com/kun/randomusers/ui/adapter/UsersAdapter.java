package com.kun.randomusers.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kun.randomusers.R;
import com.kun.randomusers.domain.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * @author Julio Kun
 * @version 0.1
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private ArrayList<User> users;
    private LayoutInflater inflater;
    private OnUserSelectedListener listener;

    public UsersAdapter(Context context, OnUserSelectedListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = users.get(position);
        holder.itemView.setTag(user);

        Picasso.with(holder.imageView.getContext()).load(user.getThumbnailImageUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (users != null) {
            return users.size();
        }
        return 0;
    }

    public void addUsers(ArrayList<User> userList) {
        /*if (users == null) {
            users = new ArrayList<>();
        }
        users.addAll(userList);*/
        this.users = userList;
        notifyDataSetChanged();
    }

    public void clearUsers() {
        users = null;
        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.userImage);
            view.setOnClickListener(onClickListener);
        }
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (listener != null) {
                User user = (User) view.getTag();
                listener.onUserSelected(user);
            }
        }
    };


    public interface OnUserSelectedListener {
        void onUserSelected(User user);
    }
}

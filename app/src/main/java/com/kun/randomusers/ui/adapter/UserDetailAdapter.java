package com.kun.randomusers.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kun.randomusers.R;
import com.kun.randomusers.domain.model.User;
import com.squareup.picasso.Picasso;

import java.util.concurrent.RecursiveAction;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Julio Kun
 * @version 0.1
 */

public class UserDetailAdapter extends RecyclerView.Adapter<UserDetailAdapter.ViewHolder> {

    private User user;
    private boolean withPicture;
    private final static int PICTURE_POSITION = 0;
    private final static int USERNAME_POSITION = 1;
    private final static int FIRST_NAME_POSITION = 2;
    private final static int LAST_NAME_POSITION = 3;
    private final static int EMAIL_POSITION = 4;

    private final static int VIEW_TYPE_PICTURE = 1;
    private final static int VIEW_TYPE_DEFAULT = 0;

    public UserDetailAdapter(boolean withPicture, User user) {
        this.user = user;
        this.withPicture = withPicture;
    }

    public void setUser(User user) {
        this.user = user;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == VIEW_TYPE_PICTURE) {
            view = inflater.inflate(R.layout.item_user_detail_image, parent, false);
        } else {
            view = inflater.inflate(R.layout.item_user_detail_attribute, parent, false);
        }
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (!withPicture) {
            position++;
        } else {
            if (position == PICTURE_POSITION) {
                Picasso.with(holder.imageView.getContext())
                        .load(user.getBigImageUrl())
                        .placeholder(R.drawable.default_avatar_large)
                        .into(holder.imageView);
                return;
            }
        }

        holder.titleTextView.setText(getTitle(position, holder.titleTextView.getContext()));
        holder.detailTextView.setText(getDetail(position));

        holder.divider.setVisibility(position == getItemCount() ? View.GONE : View.VISIBLE);
    }

    @Override
    public int getItemViewType(int position) {
        if (withPicture && position == PICTURE_POSITION) {
            return 1;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        if (user == null) {
            return 0;
        }
        return withPicture ? 5 : 4;
    }

    private String getTitle(int position, Context context) {
        switch (position) {
            case USERNAME_POSITION:
                return context.getString(R.string.txtUsername);
            case FIRST_NAME_POSITION:
                return context.getString(R.string.txtFirstName);
            case LAST_NAME_POSITION:
                return context.getString(R.string.txtLastName);
            case EMAIL_POSITION:
                return context.getString(R.string.txtEmail);
            default:
                return null;
        }
    }

    private String getDetail(int position) {
        switch (position) {
            case USERNAME_POSITION:
                return user.getUsername();
            case FIRST_NAME_POSITION:
                return user.getFirstName();
            case LAST_NAME_POSITION:
                return user.getLastName();
            case EMAIL_POSITION:
                return user.getEmail();
            default:
                return null;
        }
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView titleTextView;
        public TextView detailTextView;
        public View divider;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            titleTextView = (TextView) view.findViewById(R.id.titleTV);
            detailTextView = (TextView) view.findViewById(R.id.detailTV);
            divider = view.findViewById(R.id.divider);
        }
    }
}

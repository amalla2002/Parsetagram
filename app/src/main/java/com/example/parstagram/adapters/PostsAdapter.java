package com.example.parstagram.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.parstagram.Post;
import com.example.parstagram.PostDetails;
import com.example.parstagram.R;
import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseUser;

import org.parceler.Parcels;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    public final String TAG = "PostsAdapter";
    private Context context;
    private List<Post> posts;
    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }
    @Override
    public int getItemCount() {
        return posts.size();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private ImageButton ibLikeSetOC;
        private List<String> likedBy;
        private TextView tvLikes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivImage = itemView.findViewById(R.id.ivImage);
            ibLikeSetOC = itemView.findViewById(R.id.ibLike);
            tvLikes = itemView.findViewById(R.id.tvLikeCount);
        }
        public void bind(Post post) {
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            tvLikes.setText(String.valueOf(post.getLikedBy().size()));
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }
            ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Log.d(TAG, "clicked");
                    if (position != RecyclerView.NO_POSITION) {
                        Post post = posts.get(position);
                        Intent intent = new Intent(context, PostDetails.class);
                        intent.putExtra(Post.class.getSimpleName(), Parcels.wrap(post));
                        context.startActivity(intent);
                    }
                }
            });
            ibLikeSetOC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likedBy = post.getLikedBy();
                    String user = ParseUser.getCurrentUser().getObjectId();
                    Boolean flag = likedBy.contains(user);
                    Log.i(TAG, String.valueOf(flag));
                    if (flag) {
                        likedBy.remove(user);
                        ibLikeSetOC.setColorFilter(Color.GRAY);
                    }
                    else {
                        likedBy.add(user);
                        ibLikeSetOC.setColorFilter(Color.RED);
//                        Drawable icon = R.drawable.ufi_heart_active;
//                        ibLikeSetOC.setImageDrawable(icon);
                    }
                    post.setLikedBy(likedBy);
                    tvLikes.setText(String.valueOf(post.getLikedBy().size()));
                    post.saveInBackground();
                }
            });
        }
        public void clear() {
            posts.clear();
            notifyDataSetChanged();
        }
        public void addAll(List<Post> list) {
            posts.addAll(list);
            notifyDataSetChanged();
        }
    }
}

package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    private RecyclerView rvPosts;
    public final String TAG = "FeedActivity";

    protected PostsAdapter adapter;
    protected List<Post> allPost;
    private SwipeRefreshLayout swipeContainer;
    private EndlessRecyclerViewScrollListener scrollListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        rvPosts = findViewById(R.id.rvPosts);
        swipeContainer = findViewById(R.id.swipeContainer);

        allPost = new ArrayList<>();
        adapter = new PostsAdapter(this, allPost);

        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(linearLayoutManager);

        queryPost(0);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                allPost.clear(); // new
                adapter.notifyDataSetChanged(); //new
                queryPost(0);
                swipeContainer.setRefreshing(false); //new
            }
        });
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                queryPost(allPost.size());
            }
        };

        rvPosts.addOnScrollListener(scrollListener);
    }

    private void queryPost(int fromItem) {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(1);
        query.setSkip(fromItem);
        query.addDescendingOrder("createdAt");
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e!=null) {
                    Log.e(TAG, "Issue with getting post", e);
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
                allPost.addAll(posts);
                adapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false); //new
            }
        });

    }
}
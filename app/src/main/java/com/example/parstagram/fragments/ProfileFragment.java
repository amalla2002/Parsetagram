package com.example.parstagram.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.parstagram.EndlessRecyclerViewScrollListener;
import com.example.parstagram.Post;
import com.example.parstagram.adapters.PostsAdapter;
import com.example.parstagram.R;
import com.example.parstagram.adapters.ProfilePostAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    private final static String TAG = "ProfileFragment";
    private TextView tvUsername;
    private RecyclerView rvSelfPost;
    protected ProfilePostAdapter adapter;
    protected List<Post> allPost;
    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
    private SwipeRefreshLayout swipeContainer;
    private EndlessRecyclerViewScrollListener scrollListener;

    public ProfileFragment() {} //TODO: pass in current user

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        tvUsername = view.findViewById(R.id.tvUsernameProfile);
        rvSelfPost = view.findViewById(R.id.rvSelfPost);
        swipeContainer = view.findViewById(R.id.swipeContainer);
        allPost = new ArrayList<>();
        adapter = new ProfilePostAdapter(getContext(), allPost);
        rvSelfPost.setAdapter(adapter);
        rvSelfPost.setLayoutManager(gridLayoutManager);
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
        scrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                queryPost(allPost.size());
            }
        };
        rvSelfPost.addOnScrollListener(scrollListener);
    }
    private void queryPost(int fromItem) {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(20);
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
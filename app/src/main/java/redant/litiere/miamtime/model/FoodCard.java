package redant.litiere.miamtime.model;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.*;
import redant.litiere.miamtime.R;

import java.util.ArrayList;

@Layout(R.layout.tinder_card_view)
public class FoodCard {

    @View(R.id.profileImageView)
    private ImageView profileImageView;

    @View(R.id.nameTxt)
    private TextView nameTxt;

    @View(R.id.locationNameTxt)
    private TextView locationNameTxt;

    @View(R.id.tags)
    private TextView tags;

    private FoodElement mFoodElement;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;

    public FoodCard(Context context, FoodElement foodElement, SwipePlaceHolderView swipeView) {
        mContext = context;
        mFoodElement = foodElement;
        mSwipeView = swipeView;
    }

    @Resolve
    private void onResolved() {
        Glide.with(mContext).load(mFoodElement.getImageUrl()).into(profileImageView);
        nameTxt.setText(mFoodElement.getName());
        locationNameTxt.setText(mFoodElement.getLocation());
        String allTags = "";
        for (String tag : mFoodElement.getTags()) {
            allTags = allTags.concat(tag + ", ");
        }
        tags.setText(allTags.substring(0,allTags.length()-2));

    }

    @SwipeOut
    private void onSwipedOut() {
        Log.d("EVENT", "onSwipedOut");
        mSwipeView.addView(this);
    }

    @SwipeCancelState
    private void onSwipeCancelState() {
        Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn() {
        Log.d("EVENT", "onSwipedIn");
    }

    @SwipeInState
    private void onSwipeInState() {
        Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState() {
        Log.d("EVENT", "onSwipeOutState");
    }
}
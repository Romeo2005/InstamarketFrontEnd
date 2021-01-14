package org.romeo.instamarketApp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.romeo.instamarketApp.R;
import org.w3c.dom.ls.LSOutput;

public class UserFragment extends Fragment {

    public static final String USER_JSON = "USER_JSON";
    private InstagramUser user;
    private View root;

    public UserFragment() {
    }

    public static UserFragment newInstance(InstagramUser user) {
        assert user != null;
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(USER_JSON, new Gson().toJson(user));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getArguments() != null;

        user = new Gson().fromJson(
                getArguments().getString(USER_JSON),
                InstagramUser.class);
    }

    private void initUserProperties() {
        assert root != null;

        View name = root.findViewById(R.id.property_name);
        View country = root.findViewById(R.id.property_country);
        View profession = root.findViewById(R.id.property_profession);
        View description = root.findViewById(R.id.property_description);
        View followersNumber = root.findViewById(R.id.property_followers_number);

        Picasso.get().load(user.getHd_profile_pic_url_info().url)
                .into((ImageView) name.findViewById(R.id.property_image));
        ((EditText) name.findViewById(R.id.property_text)).setText(user.getUsername());

        ((ImageView) country.findViewById(R.id.property_image))
                .setImageResource(R.drawable.house_icon);
        ((EditText) country.findViewById(R.id.property_text)).setText(R.string.country);

        ((ImageView) profession.findViewById(R.id.property_image))
                .setImageResource(R.drawable.case_icon);
        ((EditText) country.findViewById(R.id.property_text)).setText(R.string.profession);

        ((ImageView) description.findViewById(R.id.property_image))
                .setImageResource(R.drawable.description_icon);
        ((EditText) country.findViewById(R.id.property_text)).setText(user.getBiography());

        ((ImageView) followersNumber.findViewById(R.id.property_image))
                .setImageResource(R.drawable.followers_icon);
        ((EditText) country.findViewById(R.id.property_text)).setText(String.valueOf(user.getFollower_count()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_user, container, false);
        initUserProperties();
        return root;
    }
}
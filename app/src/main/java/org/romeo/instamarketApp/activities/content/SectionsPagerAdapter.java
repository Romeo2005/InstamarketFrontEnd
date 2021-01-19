package org.romeo.instamarketApp.activities.content;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.gson.Gson;

import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.jetbrains.annotations.NotNull;
import org.romeo.instamarketApp.R;
import org.romeo.instamarketApp.activities.authorisatation.AuthActivity;
import org.romeo.instamarketApp.activities.content.fragments.AddsFragment;
import org.romeo.instamarketApp.activities.content.fragments.FiltersFragment;
import org.romeo.instamarketApp.activities.content.fragments.user.UserFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    @StringRes
    private final int[] TAB_TITLES = new int[]{R.string.filters_title, R.string.user_title, R.string.adds_title};
    private final Context mContext;
    private final InstagramUser user;

    public SectionsPagerAdapter(AppCompatActivity activity, FragmentManager fm) {
        super(fm);
        this.mContext = activity;
        this.user =
                new Gson().fromJson(
                        activity.getIntent().getStringExtra(AuthActivity.USER_JSON),
                        InstagramUser.class);
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FiltersFragment();
            case 1:
                return UserFragment.newInstance(user);
            case 2:
                return new AddsFragment();
        }

        return UserFragment.newInstance(user);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
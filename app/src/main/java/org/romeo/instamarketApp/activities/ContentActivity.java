package org.romeo.instamarketApp.activities;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.jetbrains.annotations.NotNull;
import org.romeo.instamarketApp.R;
import org.romeo.instamarketApp.activities.authorisatation.MainActivity;
import org.romeo.instamarketApp.fragments.AddsFragment;
import org.romeo.instamarketApp.fragments.FiltersFragment;
import org.romeo.instamarketApp.fragments.UserFragment;

public class ContentActivity extends AppCompatActivity {
    private InstagramUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        user = new Gson().fromJson(getIntent().getStringExtra(MainActivity.USER_JSON), InstagramUser.class);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    class SectionsPagerAdapter extends FragmentPagerAdapter {

        @StringRes
        private final int[] TAB_TITLES = new int[]{R.string.filters_title, R.string.user_title, R.string.adds_title};
        private final Context mContext;

        public SectionsPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            mContext = context;
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

            return null;
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
}
package com.example.AppTest.ui.main;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.AppTest.R;
import com.example.AppTest.cfContest;
import com.example.AppTest.cfProb;
import com.example.AppTest.cfUser;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
//    private static final int[] TAB_TITLES = new int[] {R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3};
    private static final int[] TAB_TITLES = new int[] {R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
//        return com.example.AppTest.ui.main.PlaceholderFragment.newInstance(position + 1);
        Fragment fragment=null;
        switch(position){
            case 0:
                fragment=new cfUser();
                break;
            case 1:
                fragment=new cfContest();
                break;
//            case 2:
//                fragment=new cfProb();
//                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}
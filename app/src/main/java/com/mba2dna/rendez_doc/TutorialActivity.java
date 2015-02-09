package com.mba2dna.rendez_doc;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import constants.Constants;
import me.relex.circleindicator.CircleIndicator;


public class TutorialActivity extends FragmentActivity {
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    TextView PassBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        mDemoCollectionPagerAdapter =
                new DemoCollectionPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
        CircleIndicator defaultIndicator = (CircleIndicator) findViewById(R.id.indicator_custom);
        defaultIndicator.setViewPager(mViewPager);
        Typeface tf = Typeface.createFromAsset(getAssets(),
                Constants.fontBold);
        Typeface tl = Typeface.createFromAsset(getAssets(),
                Constants.fontLight);
        PassBtn = (TextView) findViewById(R.id.PassBtn);
        PassBtn.setTypeface(tf);
        PassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent myIntent = new Intent(TutorialActivity.this,
                        MainActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });
    }

    // Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
    public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new tut1Fragment();
            switch (i) {
                case 0:
                    fragment = new tut1Fragment();
                    break;
                case 1:
                    fragment = new tut2Fragment();
                    break;
                case 2:
                    fragment = new tut3Fragment();
                    break;
                case 3:
                    fragment = new tut2Fragment();
                    break;

            }
            //  Fragment fragment = new tut1Fragment();
           /* Bundle args = new Bundle();
            // Our object is just an integer :-P
            args.putInt(tut1Fragment.ARG_OBJECT, i + 1);
            fragment.setArguments(args);*/
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "OBJECT " + (position + 1);
        }
    }


    // Instances of this class are fragments representing a single
// object in our collection.
    public static class tut1Fragment extends Fragment {
        TextView Tl, Disc;

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            // The last two arguments ensure LayoutParams are inflated
            // properly.
            Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
                    Constants.fontBold);
            Typeface tl = Typeface.createFromAsset(getActivity().getAssets(),
                    Constants.fontLight);
            View rootView = inflater.inflate(
                    R.layout.fragment_tut1, container, false);
            Tl = (TextView) rootView.findViewById(R.id.Tl);
            Tl.setTypeface(tf);
            Disc = (TextView) rootView.findViewById(R.id.Disc);
            Disc.setTypeface(tl);
            return rootView;
        }
    }

    public static class tut2Fragment extends Fragment {
        TextView Tl, Disc;

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            // The last two arguments ensure LayoutParams are inflated
            // properly.
            View rootView = inflater.inflate(
                    R.layout.fragment_tut2, container, false);
            Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
                    Constants.fontBold);
            Typeface tl = Typeface.createFromAsset(getActivity().getAssets(),
                    Constants.fontLight);

            Tl = (TextView) rootView.findViewById(R.id.Tl);
            Tl.setTypeface(tf);
            Disc = (TextView) rootView.findViewById(R.id.Disc);
            Disc.setTypeface(tl);
            return rootView;
        }
    }
    public static class tut3Fragment extends Fragment {
        TextView Tl, Disc;

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            // The last two arguments ensure LayoutParams are inflated
            // properly.
            Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
                    Constants.fontBold);
            Typeface tl = Typeface.createFromAsset(getActivity().getAssets(),
                    Constants.fontLight);
            View rootView = inflater.inflate(
                    R.layout.fragment_tut3, container, false);
            Tl = (TextView) rootView.findViewById(R.id.Tl);
            Tl.setTypeface(tf);
            Disc = (TextView) rootView.findViewById(R.id.Disc);
            Disc.setTypeface(tl);
            return rootView;
        }
    }
}

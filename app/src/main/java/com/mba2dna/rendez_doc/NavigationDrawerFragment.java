package com.mba2dna.rendez_doc;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xperi.avataimageview.DSAvatarImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import constants.Constants;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView name, email;

    public static final String PREF_FILE_NAME = "testPref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private boolean mUserLearnerDrawer;
    private boolean mSavedFrominstence;

    private recyclerAdapter adapter;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnerDrawer = Boolean.valueOf(ReadToPrefrencece(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null) {
            mSavedFrominstence = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Typeface tl = Typeface.createFromAsset(getActivity().getAssets(),
                Constants.fontLight);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
                Constants.fontBold);

        View rootView = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        name = (TextView) rootView.findViewById(R.id.name);
        name.setTypeface(tf);
        email = (TextView) rootView.findViewById(R.id.email);
        email.setTypeface(tl);
        DSAvatarImageView avataImageView=(DSAvatarImageView) rootView.findViewById(R.id.cicleIm);
        avataImageView.setImageResource(R.drawable.profile);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rview);
        adapter = new recyclerAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

    public static List<ItemRow> getData() {
        List<ItemRow> row = new ArrayList<>();
        int[] icons = { R.drawable.ic_action_search,R.drawable.ic_action_favorie, R.drawable.ic_action_agenda, R.drawable.ic_action_setting};
        String[] titles = { "Rechercher un docteur","Mes docteurs favories", "Mes rendez-vous", "Paramatres"};
        for (int i = 0; i < icons.length && i < titles.length; i++) {
            ItemRow curr = new ItemRow();
            curr.Title = titles[i];
            curr.icon = icons[i];
            row.add(curr);
        }
        return row;
    }


    public void setUp(DrawerLayout dr, Toolbar tl) {
        mDrawerLayout = dr;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), dr, tl, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }


    public String ReadToPrefrencece(Context context, String PrefrenceceName, String PrefrenceceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PrefrenceceName, PrefrenceceValue);

    }

    public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
        private LayoutInflater inflater;
        List<ItemRow> Rows = Collections.emptyList();

        public recyclerAdapter(Context context, List<ItemRow> Rows) {
            inflater = LayoutInflater.from(context);
            this.Rows = Rows;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View view = inflater.inflate(R.layout.navigation_drawer_item, parent, false);
            MyViewHolder holder = new MyViewHolder(view,getActivity());
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder viewHolder, int i) {
            ItemRow current = Rows.get(i);

            Typeface tl = Typeface.createFromAsset(getActivity().getAssets(),
                    Constants.fontLight);

            viewHolder.title.setText(current.Title);
            viewHolder.title.setTypeface(tl);
           /* if(i==2){
                Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
                        Constants.fontBold);
                viewHolder.title.setTypeface(tf);
                viewHolder.title.setTextColor(getResources().getColor(R.color.accentColor));
            }*/
            viewHolder.Icon.setImageResource(current.icon);
        }

        @Override
        public int getItemCount() {
            return Rows.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView title;
            ImageView Icon;
            Context contxt;

            public MyViewHolder(View itemView,Context c) {
                super(itemView);
                contxt = c;
                itemView.setClickable(true);
                itemView.setOnClickListener(this);
                title = (TextView) itemView.findViewById(R.id.ItemText);
                Icon = (ImageView) itemView.findViewById(R.id.itemIcom);
            }
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawers();
                Toast.makeText(contxt, "The Item Clicked is: " + getPosition(), Toast.LENGTH_SHORT).show();

            }
        }
    }

    public static class ItemRow {
        String Title;
        int icon;
    }
}

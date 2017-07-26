package com.mba2dna.rendez_doc;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import constants.Constants;
import me.drakeet.materialdialog.MaterialDialog;


public class MainActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private Button searchBtn;
    private EditText NomMidecin;
    private String[] Specialites;
    private Spinner mySpinner;
    private Typeface myFont;
    private MaterialDialog mMaterialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface tf = Typeface.createFromAsset(getAssets(),
                Constants.fontBold);
        Typeface tl = Typeface.createFromAsset(getAssets(),
                Constants.fontLight);
        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigationdrawer);
        drawerFragment.setUp((DrawerLayout) findViewById(R.id.drwlo), toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "fab clicked", Toast.LENGTH_SHORT).show();
            }
        });
        searchBtn = (Button) findViewById(R.id.searchBtn);
        searchBtn.setTypeface(tl);
        NomMidecin = (EditText) findViewById(R.id.NomMidecin);
        NomMidecin.setTypeface(tl);
        Specialites = getResources().getStringArray(R.array.specialiter_array);
        myFont = Typeface.createFromAsset(getAssets(), Constants.fontLight);

        mySpinner = (Spinner) findViewById(R.id.Specialites);
        MyArrayAdapter ma = new MyArrayAdapter(this);
        mySpinner.setAdapter(ma);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        mMaterialDialog = new MaterialDialog(this);
        mMaterialDialog.setTitle("Fermeture de session");
        mMaterialDialog.setMessage("Etes vous sure de vouloir fermer l'application?");
        mMaterialDialog.setPositiveButton("OUI", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  mMaterialDialog.dismiss();
                finish();
            }
        });
        mMaterialDialog.setNegativeButton("Annuler", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();

            }
        });

        mMaterialDialog.show();
    }
    private class MyArrayAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        public MyArrayAdapter(Context inscriptionTab) {
            // TODO Auto-generated constructor stub
            mInflater = LayoutInflater.from(inscriptionTab);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return Specialites.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final ListContent holder;
            View v = convertView;
            if (v == null) {
                v = mInflater.inflate(R.layout.spinner_style, null);
                holder = new ListContent();

                holder.name = (TextView) v.findViewById(R.id.SpinnerItem);

                v.setTag(holder);
            } else {

                holder = (ListContent) v.getTag();
            }

            holder.name.setTypeface(myFont);
            holder.name.setText("" + Specialites[position]);

            return v;
        }

    }

    static class ListContent {

        TextView name;

    }
}

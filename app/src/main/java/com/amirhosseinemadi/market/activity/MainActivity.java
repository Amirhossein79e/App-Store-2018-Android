package com.amirhosseinemadi.market.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import com.amirhosseinemadi.market.R;
import com.amirhosseinemadi.market.common.AppCls;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton floatingActionButton;
    private BottomAppBar bottomAppBar;
    private SearchView searchView;
    private CardView cardView;
    private Boolean networkc = false;
    private ConnectivityManager connectivityManager;
    private AlertDialog.Builder alert;
    private AlertDialog alertDialog;
    private AppCompatImageButton voice;
    ArrayList<String> strings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(AppCls.component.langManager().getLang()));
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Resources resources = new Resources(getAssets(),displayMetrics,configuration);

        setContentView(R.layout.activity_main);

        alert = new AlertDialog.Builder(MainActivity.this,R.style.myAlert);
        alert.setTitle(R.string.NetFailed)
                .setMessage(R.string.NetFailedMsg)
                .setPositiveButton(R.string.NetSetting, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run()
                            {
                                Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                startActivity(intent);
                            }
                        });

                    }
                })
                .setCancelable(false);


        alertDialog = alert.create();


        bottomNavigationView = findViewById(R.id.bottom_nav);
        floatingActionButton = findViewById(R.id.fab);
        searchView = findViewById(R.id.search_view);
        searchView.setIconifiedByDefault(false);
        cardView = findViewById(R.id.search_card);
        bottomAppBar = findViewById(R.id.bottom_app_bar);
        voice = findViewById(R.id.btn_voice);


        floatingActionButton.setOnClickListener(this::floatingClick);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::navClick);
        cardView.setOnClickListener(this::cardClick);
        voice.setOnClickListener(this::voiceClick);


        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkRequest.Builder netReq = new NetworkRequest.Builder();


        connectivityManager.registerNetworkCallback(netReq.build(),new ConnectivityManager.NetworkCallback()
        {
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        networkc = true;
                        alertDialog.dismiss();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new HomeFragment(),null).addToBackStack("home").commit();
                    }
                });
            }

            @Override
            public void onUnavailable() {
                super.onUnavailable();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alertDialog.show();
                        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.md_blue_grey_800));
                        networkc = false;
                    }
                });
            }


            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alertDialog.show();
                        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.md_blue_grey_800));
                        networkc = false;
                    }
                });
            }
        });





        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            SearchFragment searchFragment;
            Bundle bundle = new Bundle();

            @Override
            public boolean onQueryTextSubmit(String query) {

                if (query.length()>= 3)
                {
                    searchFragment = new SearchFragment();

                    bundle.putString("query",query);

                    searchFragment.setArguments(bundle);

                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,searchFragment,null).addToBackStack("search").commit();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText.length()>= 3)
                {

                    searchFragment = new SearchFragment();

                    bundle.putString("query",newText);

                    searchFragment.setArguments(bundle);

                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,searchFragment,null).addToBackStack("search").commit();

                }
                return false;
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();

        if (!networkc)
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    alertDialog.show();
                    alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.md_blue_grey_800));

                }
            });
        }

    }

    public void floatingClick(View v)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new HomeFragment(),null).addToBackStack("home").commit();
    }



    public boolean navClick(MenuItem item)
    {

        switch (item.getItemId())
        {

            case R.id.bottom_category:

                getSupportFragmentManager().beginTransaction().replace(R.id.frame,new CategoryFragment(),null).addToBackStack("category").commit();
                bottomNavigationView.getMenu().getItem(0).setChecked(true);
                break;

            case R.id.bottom_setting:

                getSupportFragmentManager().beginTransaction().replace(R.id.frame,new SettingFragment(),null).addToBackStack("setting").commit();
                bottomNavigationView.getMenu().getItem(2).setChecked(true);
                break;
        }

        return false;
    }



    public void cardClick(View v)
    {
        searchView.requestFocus();
    }


    public void voiceClick(View view)
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,getString(R.string.voice));
        if (AppCls.component.langManager().getLang().equals("fa"))
        {
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"fa");
        }else
        {
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"en");
        }

        startActivityForResult(intent,1);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
        {

            if (data != null)
            {
                if (data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) != null) {
                    strings = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    for (String s : strings) {
                        searchView.setQuery(s, true);
                    }
                }
            }

        }
    }
}

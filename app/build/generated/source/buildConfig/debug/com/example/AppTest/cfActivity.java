package com.example.AppTest;

import android.content.Intent;
import android.os.Bundle;

import com.example.AppTest.activities.notesMain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;

import com.example.AppTest.ui.main.SectionsPagerAdapter;

public class cfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_cf);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_dash){
                    Intent intent=new Intent(getApplicationContext(),dashboard.class);
                    startActivity(intent);
                }
                if(item.getItemId()==R.id.nav_notes){
                    Intent intent=new Intent(getApplicationContext(), notesMain.class);
                    startActivity(intent);
                }
                if(item.getItemId()==R.id.nav_home){
                    Intent intent=new Intent(getApplicationContext(),SplashActivity.class);
                    startActivity(intent);
                }
                if(item.getItemId()==R.id.nav_gallery){
                    Intent intent=new Intent(getApplicationContext(),cowinActivity.class);
                    startActivity(intent);
                }
                if(item.getItemId()==R.id.nav_slideshow){
                    Intent intent=new Intent(getApplicationContext(),cfActivity.class);
                    startActivity(intent);
                }
                DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_home);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent=new Intent(cfActivity.this,dashboard.class);
                startActivity(intent);
            }
        });
    }
}
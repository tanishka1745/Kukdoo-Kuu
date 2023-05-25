package com.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ThemedSpinnerAdapter;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ViewPagerAdapter;
import com.example.kukdookuu.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    FirebaseAuth firebaseAuth;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager2 viewPager2;
    String []titles= new String[]{"Home","Health","Science","Technology","Entertainment"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().hide();
        firebaseAuth=FirebaseAuth.getInstance();

        tabLayout=findViewById(R.id.tablayout);
        viewPager2=findViewById(R.id.viewpager);
        viewPagerAdapter= new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);


        new TabLayoutMediator(tabLayout,viewPager2,((tab, position)->tab.setText( titles[position]))).attach();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.signout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        switch (id)
        {
            case R.id.items1:
                Toast.makeText(this, "Log Out here", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,LoginActivity.class));
                firebaseAuth=null;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
package com.reodinas2.tabbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.reodinas2.tabbar.config.Config;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    // 각 프래그먼트를 멤버변수로 만든다.
    public Fragment firstFragment;
    Fragment secondFragment;
    Fragment thirdFragment;
    private String accessToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 억세스 토큰이 있는지 확인
        SharedPreferences sp = getSharedPreferences(Config.SP_NAME, MODE_PRIVATE);
        accessToken = sp.getString(Config.ACCESS_TOKEN, "");

        if (accessToken.isEmpty()){

            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        getSupportActionBar().setTitle("홈");

        navigationView = findViewById(R.id.bottomNavigationView);

        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
        thirdFragment = new ThirdFragment();

        // 탭바를 누르면 동작하는 코드
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemid = item.getItemId();

                Fragment fragment = null;

                if (itemid == R.id.firstFragment){
                    fragment = firstFragment;
                    getSupportActionBar().setTitle("홈");

                } else if (itemid == R.id.secondFragment) {
                    fragment = secondFragment;
                    getSupportActionBar().setTitle("내 포스팅");


                } else if (itemid == R.id.thirdFragment) {
                    fragment = thirdFragment;
                    getSupportActionBar().setTitle("설정");

                }

                return loadFragment(fragment);
            }
        });

    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .commit();
            return true;
        }else {
            return false;
        }
    }


}
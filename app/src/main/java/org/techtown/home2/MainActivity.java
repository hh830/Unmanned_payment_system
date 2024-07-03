package org.techtown.home2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Bottom1Fragment bottom1_f;
    Bottom2Fragment bottom2_f;
    Bottom3Fragment bottom3_f;
    Bottom4Fragment bottom4_f;
    Bottom5Fragment bottom5_f;
    LocationFragment location_f;
    ActionBar actionBar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);//커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);//뒤로가기 버튼

        bottom1_f = new Bottom1Fragment();
        bottom2_f = new Bottom2Fragment();
        bottom3_f = new Bottom3Fragment();
        bottom4_f = new Bottom4Fragment();
        bottom5_f = new Bottom5Fragment();
        location_f = new LocationFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container2, bottom1_f).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container2,bottom1_f).commit();
                        return true;
                    case R.id.tab2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container2,bottom2_f).commit();
                        return true;
                    case R.id.tab3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container2,bottom3_f).commit();
                        return true;
                    case R.id.tab4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container2,bottom4_f).commit();
                        return true;
                    case R.id.tab5:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container2,bottom5_f).commit();
                        return true;
                }
                return false;
            }
        });

    }

    @Override//메뉴바
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
    }


    @Override//위치 선택시 location_f 레이아웃으로 이동
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_location:
                getSupportFragmentManager().beginTransaction().replace(R.id.container2,location_f).commit();
                break;
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}
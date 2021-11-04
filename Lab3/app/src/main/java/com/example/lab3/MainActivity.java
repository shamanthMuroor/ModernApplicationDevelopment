package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    FirstFragment mFragmentOne;
    SecondFragment mFragmentTwo;
    Button btn;
    Button btn2;
    int showingFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);

        mFragmentOne = new FirstFragment();
        mFragmentTwo = new SecondFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.frameLayout, mFragmentOne);
        fragmentTransaction.commit();
        showingFragment = 1;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if(showingFragment==1) {
                    fragmentTransaction.replace(R.id.frameLayout, mFragmentTwo);
                    showingFragment=2;
                }
                else {
                    fragmentTransaction.replace(R.id.frameLayout, mFragmentOne);
                    showingFragment=1;
                }
                fragmentTransaction.commit();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if(showingFragment==1) {
                    fragmentTransaction.remove(mFragmentOne);
                }
                else {
                    fragmentTransaction.remove(mFragmentTwo);
                }
                showingFragment=0;
                fragmentTransaction.commit();
            }
        });

    }
}
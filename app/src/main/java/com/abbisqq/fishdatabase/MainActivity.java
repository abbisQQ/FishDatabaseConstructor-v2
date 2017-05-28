package com.abbisqq.fishdatabase;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.abbisqq.fishdatabase.fragments.AddTasksFragment;
import com.abbisqq.fishdatabase.fragments.StartingFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            goBack();
        }
    }


    // Checks if the user is in starting fragment the app will exit
    // Else if he is at add fragment he will return to start fragment
    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.container);
        if (f instanceof AddTasksFragment)
            goBack();
        else {
            this.finish();
        }
    }


    public void fabButtonClicked(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new AddTasksFragment())
                .commit();
    }


    //Goes back to Staring Fragment
    private void goBack() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new StartingFragment())
                .commit();
    }


}
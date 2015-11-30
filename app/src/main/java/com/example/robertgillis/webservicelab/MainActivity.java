package com.example.robertgillis.webservicelab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new UserListFragment ())
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id == R.id.action_add_user)
        {
            AddUserFragment addUserFragment = new AddUserFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, addUserFragment).addToBackStack(null).commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
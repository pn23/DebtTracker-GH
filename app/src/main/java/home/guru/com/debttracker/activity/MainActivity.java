package home.guru.com.debttracker.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import home.guru.com.debttracker.R;

public class MainActivity extends AppCompatActivity
        implements FragmentDrawer.FragmentDrawerListener {

    private int selectedNavPos = 0;//checks which drawer item selected.
    private int count = 0;//checks number of timesback button pressed.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        FragmentDrawer mDrawerFragment = (FragmentDrawer)
                getSupportFragmentManager().
                        findFragmentById(R.id.fragment_navigation_drawer);
        mDrawerFragment.setUp(R.id.fragment_navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        mDrawerFragment.setDrawerListener(this);
        displayView(0);
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
    public void onDrawerItemSelected(View view, int position) {

        selectedNavPos = position;
        displayView(selectedNavPos);
    }

    @SuppressWarnings("ConstantConditions")
    private void displayView(int position) {
        Fragment fragment = null;
        String mTitle = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                mTitle = getString(R.string.title_home);
                break;
            case 1:
                fragment = new FriendsFragment();
                mTitle = getString(R.string.title_friends);
                break;
            case 2:
                fragment = new MessagesFragment();
                mTitle = getString(R.string.title_messages);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            //fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(mTitle);
        }

    }

    @Override
    public void onBackPressed() {
        count++;
        if (selectedNavPos != 0 && count == 1) {
            displayView(0);
        } else {
            super.onBackPressed();
        }
    }
    /*private boolean exit=false;
    @Override
    public void onBackPressed() {

        if (selectedNavPos != 0) {
            displayView(0);
            //moveTaskToBack(true);
        }  else {
            super.onBackPressed();
            }
        }*/

    /*public void performNoBackStackTransaction
            (final FragmentManager fragmentManager, String tag, Fragment fragment) {
        final int newBackStackLength =
                fragmentManager.getBackStackEntryCount() +1;

        fragmentManager.beginTransaction()
                .replace(R.id.container_body, fragment, tag)
                .addToBackStack(tag)
                .commit();
        getSupportActionBar().setTitle(mTitle);

        fragmentManager.addOnBackStackChangedListener
                (new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int nowCount = fragmentManager.getBackStackEntryCount();
                if (newBackStackLength != nowCount) {
                    // we don't really care if going back or forward. we already performed the logic here.
                    fragmentManager.removeOnBackStackChangedListener(this);

                    if ( newBackStackLength > nowCount ) { // user pressed back
                        fragmentManager.popBackStackImmediate();
                    }
                }
            }
        });
    }*/
}


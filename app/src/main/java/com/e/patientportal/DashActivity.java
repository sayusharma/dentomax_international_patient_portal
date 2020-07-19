package com.e.patientportal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class DashActivity extends AppCompatActivity {
    SNavigationDrawer sNavigationDrawer;
    Class fragmentClass;
    public static Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        sNavigationDrawer = findViewById(R.id.navigationDrawer);
        List<MenuItem> menuItems = new ArrayList<>();

        //Use the MenuItem given by this library and not the default one.
        //First parameter is the title of the menu item and then the second parameter is the image which will be the background of the menu item.

        menuItems.add(new MenuItem("Home",R.drawable.back));
        menuItems.add(new MenuItem("My Profile",R.drawable.back));
        menuItems.add(new MenuItem("About Us",R.drawable.back));
        menuItems.add(new MenuItem("Logout",R.drawable.back));

        sNavigationDrawer.setMenuItemList(menuItems);
        fragmentClass =  HomeFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
        }



        //Listener to handle the menu item click. It returns the position of the menu item clicked. Based on that you can switch between the fragments.

        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                System.out.println("Position " + position);

                switch (position) {
                    case 0: {
                        fragmentClass = HomeFragment.class;
                        break;
                    }
                    case 1: {
                        fragmentClass = MyProfileFragment.class;
                        break;
                    }
                    case 2: {
                        fragmentClass = AboutUsFragment.class;
                        break;
                    }
                    case 3: {
                        fragmentClass = LogoutFragment.class;
                        break;
                    }

                }

                //Listener for drawer events such as opening and closing.
                sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {

                    @Override
                    public void onDrawerOpened() {

                    }

                    @Override
                    public void onDrawerOpening() {

                    }

                    @Override
                    public void onDrawerClosing() {
                        System.out.println("Drawer closed");

                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (fragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();

                        }
                    }

                    @Override
                    public void onDrawerClosed() {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        System.out.println("State " + newState);
                    }
                });
            }
        });
    }
    public void onClickLogout(View view){
        SaveSharedPreference.clearPreference(getApplicationContext());
        Intent intent = new Intent(DashActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
    public void onClickPrescription(View view){
        Intent intent = new Intent(DashActivity.this,PrescriptionActivity.class);
        startActivity(intent);
    }
    public void onClickFeedback(View view){
        Intent intent = new Intent(DashActivity.this,FeedbackActivity.class);
        startActivity(intent);
    }
    public void onClickComplaint(View view){
        Intent intent = new Intent(DashActivity.this,ComplaintActivity.class);
        startActivity(intent);
    }
    public void onClickPast(View view){
        Intent intent = new Intent(DashActivity.this,PastHistoryActivity.class);
        startActivity(intent);
    }
    public void onClickMedical(View view){
        Intent intent = new Intent(DashActivity.this,MedicalHistoryActivity.class);
        startActivity(intent);
    }
    public void onClickReports(View view){
        Intent intent = new Intent(DashActivity.this,ReportActivity.class);
        startActivity(intent);
    }
    public void onClickImages(View view){
        Intent intent = new Intent(DashActivity.this,ImageActivity.class);
        startActivity(intent);
    }
    public void onClickDiagnoses(View view){
        Intent intent = new Intent(DashActivity.this,DiagnosesActivity.class);
        startActivity(intent);
    }
    public void onClickPayments(View view){
        Intent intent = new Intent(DashActivity.this,PaymentActivity.class);
        startActivity(intent);
    }
    public void onClickSuper(View view){
        Intent intent = new Intent(DashActivity.this,SupervisingDoctorActivity.class);
        startActivity(intent);
    }

}
package com.example.robertgillis.webservicelab;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * Created by Robear on 2015/11/25.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testDataShowsUp() {
        solo.unlockScreen();
        boolean textFound = solo.searchText("robear");
        assertTrue("User List retrieved", textFound);
    }

    public void testAddUser() {
        solo.clickOnText("Add User");
        boolean textFound = solo.searchText("Enter email address");
        assertTrue("Went to the next fragment", textFound);
    }

    @Override
    public void tearDown() throws Exception {
        //tearDown() is run after a test case has finished.
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();
    }
}


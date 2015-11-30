package com.example.robertgillis.webservicelab;

import junit.framework.TestCase;

/**
 * Created by Robear on 2015/11/25.
 */
public class UserTest extends TestCase {
    public void testConstructor() {
        User.UserInfo user = new User.UserInfo("test", "testing");
        assertNotNull(user);
    }

    public void testToString() {
        User.UserInfo user = new User.UserInfo("test", "testing");
        assertEquals("test", user.toString());
    }

}


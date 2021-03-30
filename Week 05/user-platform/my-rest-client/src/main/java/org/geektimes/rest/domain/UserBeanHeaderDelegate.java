package org.geektimes.rest.domain;

import javax.ws.rs.ext.RuntimeDelegate;

public class UserBeanHeaderDelegate implements RuntimeDelegate.HeaderDelegate<User> {
    @Override
    public User fromString(String value) {
        return null;
    }

    @Override
    public String toString(User value) {

        return "";
    }
}

package com.kun.randomusers.data.entity;

import java.util.ArrayList;

/**
 * @author Julio Kun
 * @version 0.1
 */

public class UserPageEntity {

    private Info info;
    private ArrayList<UsersEntity> results;

    public Info getInfo() {
        return info;
    }

    public ArrayList<UsersEntity> getResults() {
        return this.results;
    }

    public static class Info {
        private int page;
        private String seed;
        private int results;

        public int getPage() {
            return page;
        }

        public String getSeed() {
            return seed;
        }

        public int getResults() {
            return results;
        }
    }

}

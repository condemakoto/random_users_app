package com.kun.randomusers.data.entity;

/**
 * @author Julio Kun
 * @version 0.1
 */

public class UsersEntity {


    private String gender;
    private Name name;
    private Location location;
    private Pictures picture;
    private String email;
    private String phone;
    private Login login;

    public static class Name {
        private String title;
        private String first;
        private String last;

        public String getTitle() {
            return title;
        }

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }
    }

    public static class Street {
        private long number;
        private String name;

        public long getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }
    }

    public static class Location {
        private Street street;
        private String city;
        private String state;
        private String postcode;

        public Street getStreet() {
            return street;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public String getPostCode() {
            return postcode;
        }
    }

    public static class Pictures {
        private String large;
        private String medium;
        private String thumbnail;

        public String getLarge() {
            return large;
        }

        public String getMedium() {
            return medium;
        }

        public String getThumbnail() {
            return thumbnail;
        }
    }

    public static class Login {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    public String getGender() {
        return gender;
    }

    public Name getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Pictures getPicture() {
        return this.picture;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Login getLogin() {
        return login;
    }
}

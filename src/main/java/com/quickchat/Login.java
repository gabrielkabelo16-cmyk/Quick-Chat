package com.quickchat;

public class Login {

    private String username;
    private String password;
    private String cellPhone;
    private String firstName;
    private String lastName;
    private boolean registered = false;

    public Login(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // username must have an underscore and be 5 chars or less
    public boolean checkUserName() {
        if (username.contains("_") && username.length() <= 5) {
            return true;
        }
        return false;
    }

    // password must be 8+ chars with a capital, number and special char
    public boolean checkPasswordComplexity() {
        boolean capital = false;
        boolean number = false;
        boolean special = false;

        if (password.length() < 8) {
            return false;
        }

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                capital = true;
            } else if (Character.isDigit(c)) {
                number = true;
            } else if (!Character.isLetterOrDigit(c)) {
                special = true;
            }
        }

        return capital && number && special;
    }

    // cell number must have the international code e.g. +27
    // regex pattern referenced from stackoverflow / regexlib
    public boolean checkCellPhoneNumber() {
        if (cellPhone.matches("^\\+[1-9]\\d{9,13}$")) {
            return true;
        }
        return false;
    }

    public String registerUser(String username, String password, String cellPhone) {
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;

        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your "
                    + "username contains an underscore and is no more than five "
                    + "characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the "
                    + "password contains at least eight characters, a capital letter, "
                    + "a number, and a special character.";
        }
        if (!checkCellPhoneNumber()) {
            return "Cell number is incorrectly formatted or does not contain an "
                    + "international code; please correct the number and try again.";
        }

        registered = true;
        return "Welcome " + firstName + ", " + lastName + " it is great to see you.";
    }

    // checks login details against what was stored during registration
    public boolean loginUser(String username, String password) {
        if (registered && this.username.equals(username) && this.password.equals(password)) {
            return true;
        }
        return false;
    }

    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you.";
        }
        return "Username or password incorrect, please try again.";
    }

    public boolean isRegistered() {
        return registered;
    }
}

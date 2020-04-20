package com.example.sqlitedatabase;

public class Data {
    int id;
    String email;
    String pass;

    public Data(){

    }
    //Create table (query)

    public static final String CREATE_TABLE=
            "CREATE TABLE " + "HelloNew" + "("+
                    "Id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Email" + " TexT, " +
                    "Password" + " TexT "+ ")";

//    public static  String CREATE_TABLE = "CREATE TABLE " + "New" + "("
//            + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//            + "email" + " TEXT,"
//            + "pass" + " TEXT,"
//            +  ")";
    public Data(int id, String email, String pass) {
        this.id = id;
        this.email = email;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

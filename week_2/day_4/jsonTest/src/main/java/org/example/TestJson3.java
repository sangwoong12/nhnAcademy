package org.example;

import org.json.JSONObject;

public class TestJson3 {
    public static class Info{
        String address;
        int age;
        boolean mail;

        public Info() {
            this.address = "IT 2211";
            this.age = 20;
        }

        public String getAddress() {
            return address;
        }

        public int getAge() {
            return age;
        }

        public boolean isMail() {
            return mail;
        }
    }

    public static void main(String[] args) {
        Info info = new Info();
        JSONObject object = new JSONObject(info);
        System.out.println(object.toString(4));
    }
}

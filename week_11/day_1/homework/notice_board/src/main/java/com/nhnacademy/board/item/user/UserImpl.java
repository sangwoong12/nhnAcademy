package com.nhnacademy.board.item.user;


import com.nhnacademy.board.domain.UserRequest;

public class UserImpl implements User {
    private String id;
    private String name;
    private String password;
    private String profileFileName;

    public UserImpl(String id, String name, String password, String profileFileName) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.profileFileName = profileFileName;
    }

    public static User createByUserRequest(UserRequest userRequest) {
        return new UserImpl(userRequest.getId(), userRequest.getName(), userRequest.getPassword(), null);
    }

    public UserImpl() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getProfileFileName() {
        return profileFileName;
    }

    @Override
    public void setProfileFileName(String profileFileName) {
        this.profileFileName = profileFileName;
    }

}

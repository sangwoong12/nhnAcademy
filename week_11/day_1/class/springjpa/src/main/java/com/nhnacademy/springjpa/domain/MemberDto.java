package com.nhnacademy.springjpa.domain;

import java.util.List;

public interface    MemberDto {
    String getUserName();
    List<MemberDetailDto> getMemberDetails();

    interface MemberDetailDto {
        String getType();
        String getDescription();
    }
}

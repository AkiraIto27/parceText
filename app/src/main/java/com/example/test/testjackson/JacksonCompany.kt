package com.example.test.testjackson

import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.annotation.Nulls
import testjackson.JacksonUserInfo

class JacksonCompany(
    @JsonSetter(nulls = Nulls.SKIP) var name: String = "",
    var userInfos: List<JacksonUserInfo>
) {

    override fun toString(): String {
        return "Company{" +
                "name='" + name + '\'' +
                ", userInfos=" + userInfos +
                '}'
    }
}
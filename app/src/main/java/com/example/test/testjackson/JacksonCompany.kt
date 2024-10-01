package com.example.test.testjackson

import testjackson.JacksonUserInfo

class JacksonCompany {
    var name: String? = null
    var userInfos: List<JacksonUserInfo>? = null

    override fun toString(): String {
        return "Company{" +
            "name='" + name + '\'' +
            ", userInfos=" + userInfos +
            '}'
    }
}
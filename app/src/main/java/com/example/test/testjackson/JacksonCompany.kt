package com.example.test.testjackson

class JacksonCompany(
     var name: String? = null,
     var userInfos: List<JacksonUserInfo>? = null
) {

    override fun toString(): String {
        return "Company{" +
                "name='" + name + '\'' +
                ", userInfos=" + userInfos +
                '}'
    }
}
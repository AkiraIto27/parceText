package com.example.test.testjackson

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)// data classにないKeyがJSONにある場合はここがないとExceptionが出る
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
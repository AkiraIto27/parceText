package com.example.test.testjackson

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)// data classにないKeyがJSONにある場合はここがないとExceptionが出る
class JacksonUserInfo(
    var name: String? = null,
    var age: Int = 0
) {
    override fun toString(): String {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}'
    }
}
package com.example.test.testMoshi

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoshiUserInfo(
    var name: String = "",
    var age: Int,
) {
    override fun toString(): String {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}'
    }
}
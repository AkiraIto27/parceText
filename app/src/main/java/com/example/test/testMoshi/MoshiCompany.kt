package com.example.test.testMoshi

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoshiCompany(
    var name: String = "",
    var userInfos: List<MoshiUserInfo>
) {
    override fun toString(): String {
        return "Company{" +
                "name='" + name + '\'' +
                ", userInfos=" + userInfos +
                '}'
    }
}
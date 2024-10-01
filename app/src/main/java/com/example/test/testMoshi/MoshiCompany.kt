package com.example.test.testMoshi

import testMoshi.MoshiUserInfo

class MoshiCompany {
    var name: String? = null
    var userInfos: List<MoshiUserInfo>? = null

    override fun toString(): String {
        return "Company{" +
            "name='" + name + '\'' +
            ", userInfos=" + userInfos +
            '}'
    }
}
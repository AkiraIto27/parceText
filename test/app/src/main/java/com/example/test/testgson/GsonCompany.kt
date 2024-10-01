package com.example.test.testgson


class GsonCompany {
    private val name: String? = null
    private val userInfos: List<GsonUserInfo>? = null

    override fun toString(): String {
        return "Company{" +
            "name='" + name + '\'' +
            ", userInfos=" + userInfos +
            '}'
    }
}
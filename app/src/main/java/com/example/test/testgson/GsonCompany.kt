package com.example.test.testgson


data class GsonCompany(
    private val name: String,
    private val userInfos: List<GsonUserInfo>
) {

    override fun toString(): String {
        return "Company{" +
                "name='" + name + '\'' +
                ", userInfos=" + userInfos +
                '}'
    }
}
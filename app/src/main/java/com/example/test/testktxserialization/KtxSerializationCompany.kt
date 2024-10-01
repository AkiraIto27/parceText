package com.example.test.testktxserialization

import kotlinx.serialization.Serializable

@Serializable
data class KtxSerializationCompany(
    val name: String = "",
    val userInfos: List<KtxSerializationUserInfo>
) {
    override fun toString(): String {
        return "Company{" +
                "name='" + name + '\'' +
                ", userInfos=" + userInfos +
                '}'
    }
}
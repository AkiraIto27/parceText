package com.example.test.testktxserialization

import kotlinx.serialization.Serializable

@Serializable
data class KtxSerializationCompany (
    val name: String? = null,
    val userInfos: List<KtxSerializationUserInfo>? = null
){
    override fun toString(): String {
        return "Company{" +
            "name='" + name + '\'' +
            ", userInfos=" + userInfos +
            '}'
    }
}
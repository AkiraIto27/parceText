package com.example.test.testktxserialization

import kotlinx.serialization.Serializable

@Serializable
data class KtxSerializationUserInfo(
    val name: String = "",
    val age: Int
) {

    override fun toString(): String {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}'
    }
}
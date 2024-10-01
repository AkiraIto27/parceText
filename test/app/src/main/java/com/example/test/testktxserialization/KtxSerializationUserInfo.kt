package com.example.test.testktxserialization

import kotlinx.serialization.Serializable

@Serializable
data class KtxSerializationUserInfo(
    val name: String? = null,
    val age: Int = 0
) {

    override fun toString(): String {
        return "UserInfo{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}'
    }
}
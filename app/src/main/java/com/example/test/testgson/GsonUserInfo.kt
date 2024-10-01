package com.example.test.testgson

data class GsonUserInfo(
    private val name: String,
    private val age: Int
) {

    override fun toString(): String {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}'
    }
}
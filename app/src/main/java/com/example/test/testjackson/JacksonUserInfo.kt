package testjackson

import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.annotation.Nulls

class JacksonUserInfo(
    @JsonSetter(nulls = Nulls.SKIP) var name: String = "",
    var age: Int,
) {
    override fun toString(): String {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}'
    }
}
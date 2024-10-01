package testjackson

class JacksonUserInfo {
    var name: String? = null
    var age: Int = 0

    override fun toString(): String {
        return "UserInfo{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}'
    }
}
package com.geeklog.common.util

import com.geeklog.common.exception.ValidatorException
import com.geeklog.domain.User
import com.geeklog.dto.UserWithPermission

class ConverterSpec extends BaseUtilSpec {

    def "Converter.convert test"() {
        User user = new User()
        user.userId = 1
        user.username = "phr272018"
        user.password = "123456"
        user.nickname = "phr"
        user.avatar = "/a/b"
        user.isAdmin = true

        when: "object is null"
        Converter.convert(null, UserWithPermission)
        then:
        ValidatorException validatorException = thrown()
        unexpectedValidatorError(validatorException, "Converter.convert(object cannot be null)")

        when: "clazz is null"
        Converter.convert(user, null)
        then:
        validatorException = thrown()
        unexpectedValidatorError(validatorException, "Converter.convert(clazz cannot be null)")

        when: "正常转换"
        UserWithPermission userWithPermission = Converter.convert(user, UserWithPermission.class)
        then:
        with(userWithPermission) {
            userId == user.userId
            username == user.username
            nickname == user.nickname
            avatar == user.avatar
            isAdmin == user.isAdmin
            canComment
            canWriteArticle
        }
    }
}

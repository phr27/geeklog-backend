package com.geeklog.common.util

import com.geeklog.common.exception.ValidatorException
import com.geeklog.domain.User
import com.geeklog.dto.UserWithPermission

class ConverterSpec extends BaseUtilSpec {

    def "Converter.domainToDTO test"() {
        User user = new User()
        user.userId = 1
        user.username = "phr272018"
        user.password = "123456"
        user.nickname = "phr"
        user.avatar = "/a/b"
        user.isAdmin = true

        when: "domain is null"
        Converter.domainToDTO(null, UserWithPermission)
        then:
        ValidatorException validatorException = thrown()
        unexpectedValidatorError(validatorException, "Converter.domainToDTO(domain cannot be null)")

        when: "dtoClass is null"
        Converter.domainToDTO(user, null)
        then:
        validatorException = thrown()
        unexpectedValidatorError(validatorException, "Converter.domainToDTO(dtoClass cannot be null)")

        when: "正常转换"
        UserWithPermission userWithPermission = Converter.domainToDTO(user, UserWithPermission.class)
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

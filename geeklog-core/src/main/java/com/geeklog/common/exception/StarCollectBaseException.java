package com.geeklog.common.exception;

public interface StarCollectBaseException {

    ValidatorException getValidatorException();

    RoleException getRoleException();

    StarCollectBaseException STAR = new StarCollectBaseException() {
        @Override
        public ValidatorException getValidatorException() {
            return ValidatorException.NO_STAR_INFO;
        }

        @Override
        public RoleException getRoleException() {
            return RoleException.OTHER_USER_STAR;
        }
    };

    StarCollectBaseException COLLECT = new StarCollectBaseException() {
        @Override
        public ValidatorException getValidatorException() {
            return ValidatorException.NO_COLLECT_INFO;
        }

        @Override
        public RoleException getRoleException() {
            return RoleException.OTHER_USER_COLLECT;
        }
    };
}

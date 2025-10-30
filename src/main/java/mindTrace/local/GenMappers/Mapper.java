package mindTrace.local.GenMappers;

import mindTrace.local.Dtos.UserRegistrationDTO;
import mindTrace.local.Entities.User;

public class Mapper {
    public static User fromDtoToUserEntity(UserRegistrationDTO req) {
        return User.builder().
                firstname(req.getFirstname()).
                lastname(req.getLastname()).
                email(req.getEmail()).
                build();
    }
}

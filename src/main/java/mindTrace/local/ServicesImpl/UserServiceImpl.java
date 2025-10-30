package mindTrace.local.ServicesImpl;

import lombok.RequiredArgsConstructor;
import mindTrace.local.Dtos.UserRegistrationDTO;
import mindTrace.local.Entities.User;
import mindTrace.local.Repositories.UserRepository;
import mindTrace.local.Services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static mindTrace.local.Constants.ExceptionsMessages.*;
import static mindTrace.local.GenMappers.Mapper.fromDtoToUserEntity;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private void validateUserData(UserRegistrationDTO userRegistrationDTO) {
        if(userRegistrationDTO.getFirstname() == null || userRegistrationDTO.getFirstname().isEmpty()) {
            throw new RuntimeException(FIRSTNAME_REQUIRED);
        }
        if(userRegistrationDTO.getLastname() == null || userRegistrationDTO.getLastname().isEmpty()) {
            throw new RuntimeException(LASTNAME_REQUIRED);
        }

        if(userRegistrationDTO.getEmail() == null || userRegistrationDTO.getEmail().isEmpty()) {
            throw new RuntimeException(EMAIL_REQUIRED);
        }
        if(userRegistrationDTO.getPassword() == null || userRegistrationDTO.getPassword().isEmpty()) {
            throw new RuntimeException(PASSWORD_REQUIRED);
        }

        if(userRegistrationDTO.getPasswordConfirmation() == null || userRegistrationDTO.getPasswordConfirmation().isEmpty()) {
            throw new RuntimeException(PASSWORDS_CONFIRMATION_REQUIRED);
        }

        if(userRegistrationDTO.getPassword().length() < 10) {
            throw new RuntimeException(WEAK_PASSWORD);
        }

        if(!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getPasswordConfirmation())) {
            throw new RuntimeException(PASSWORDS_MISMATCH);
        }
    }

    @Override
    public void registerUser(UserRegistrationDTO dto) {
        validateUserData(dto);
        String hashedPass = passwordEncoder.encode(dto.getPassword());
        User user = fromDtoToUserEntity(dto);
        user.setPassword(hashedPass);
        userRepository.save(user);
    }
}

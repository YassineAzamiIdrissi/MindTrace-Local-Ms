package mindTrace.local.ServicesImpl;

import lombok.RequiredArgsConstructor;
import mindTrace.local.Dtos.LoginRequest;
import mindTrace.local.Dtos.SessionDto;
import mindTrace.local.Dtos.UserRegistrationDTO;
import mindTrace.local.Entities.User;
import mindTrace.local.Repositories.UserRepository;
import mindTrace.local.Services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static mindTrace.local.Constants.ExceptionsMessages.*;
import static mindTrace.local.GenMapper.Mapper.fromDtoToUserEntity;

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
    private void validateLoginData(LoginRequest loginRequest) {
        if(loginRequest.getEmail() == null || loginRequest.getEmail().isEmpty()) {
            throw new RuntimeException(EMAIL_REQUIRED);
        }
        if(loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            throw new RuntimeException(PASSWORD_REQUIRED);
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

    @Override
    public SessionDto loginUser(LoginRequest request) {
        validateLoginData(request);
        User concernedUser = userRepository.findByEmail(
                request.getEmail()
        ).orElseThrow(
                ()-> new RuntimeException(AUTH_ERROR)
        );
        String hashedPass = passwordEncoder.encode(request.getPassword());
        if(hashedPass.equals(concernedUser.getPassword())) {
            SessionDto sessionDto = new SessionDto();
            sessionDto.setEmail(concernedUser.getEmail());
            sessionDto.setFirstname(concernedUser.getFirstname());
            sessionDto.setLastname(concernedUser.getLastname());
            return sessionDto;
        } else {
            throw new RuntimeException(AUTH_ERROR);
        }
    }
}

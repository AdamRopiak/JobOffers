package pl.joboffers.domain.userloginandregistration;

import org.junit.Test;
import pl.joboffers.domain.userloginandregistration.dto.NewUserRequestDto;
import pl.joboffers.domain.userloginandregistration.dto.RegistrationResultDto;
import pl.joboffers.domain.userloginandregistration.dto.UserDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class UserLoginAndRegistrationFacadeTest {

    UserLoginAndRegistrationFacade userFacade = new UserLoginAndRegistrationFacade(new inMemoryUserRepository());

    @Test
    public void should_register_user(){
        //given
        NewUserRequestDto newUser = new NewUserRequestDto("User", "12345");

        //when
        RegistrationResultDto results = userFacade.registerNewUser(newUser);

        //then
        assertThat(results.userId()).isNotEmpty();
        assertThat(results.userName()).isEqualTo("User");
    }

    @Test
    public void should_find_user_by_user_name(){
        //given
        NewUserRequestDto newUser = new NewUserRequestDto("User", "12345");
        RegistrationResultDto results = userFacade.registerNewUser(newUser);

        //when
        UserDto userDto = userFacade.findUserByUserName(results.userName());

        //then
        assertThat(userDto).isEqualTo(new UserDto("User"));
    }

    @Test
    public void should_throw_exception_when_user_not_found(){
        //given
        String userToFind = "User";

        //when
        Throwable throwable = catchThrowable(() -> userFacade.findUserByUserName(userToFind));

        //then
        assertThat(throwable).isInstanceOf(UserNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("User not found");


    }
}
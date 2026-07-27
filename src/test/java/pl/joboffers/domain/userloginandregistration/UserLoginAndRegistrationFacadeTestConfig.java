package pl.joboffers.domain.userloginandregistration;




public class UserLoginAndRegistrationFacadeTestConfig {

    private final InMemoryUserRepository repository;

    public UserLoginAndRegistrationFacadeTestConfig() {
        this.repository = new InMemoryUserRepository();
    }

    public UserLoginAndRegistrationFacade userLoginAndRegistrationFacadeForTests() {
        UserLoginAndRegistrationService service = new UserLoginAndRegistrationService(repository);
        return new UserLoginAndRegistrationFacade(service);
    }

}

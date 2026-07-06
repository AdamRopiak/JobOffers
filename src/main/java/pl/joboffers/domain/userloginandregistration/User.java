package pl.joboffers.domain.userloginandregistration;

import lombok.Builder;

@Builder
record User(String userId, String userName, String password) {

}

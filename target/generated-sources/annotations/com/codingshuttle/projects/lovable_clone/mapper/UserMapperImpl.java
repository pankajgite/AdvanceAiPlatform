package com.codingshuttle.projects.lovable_clone.mapper;

import com.codingshuttle.projects.lovable_clone.dto.auth.SignupRequest;
import com.codingshuttle.projects.lovable_clone.dto.auth.UserProfileResponse;
import com.codingshuttle.projects.lovable_clone.entity.User;
import com.codingshuttle.projects.lovable_clone.enums.ProjectRole;
import java.time.Instant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-15T17:34:14+0530",
    comments = "version: 1.6.0, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntityFromSignupRequest(SignupRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( request.username() );
        user.password( request.password() );
        user.name( request.name() );

        return user.build();
    }

    @Override
    public UserProfileResponse toUserProfileResposeFromUser(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String username = null;
        String name = null;

        id = user.getId();
        username = user.getUsername();
        name = user.getName();

        ProjectRole role = null;
        Instant invitedAt = null;

        UserProfileResponse userProfileResponse = new UserProfileResponse( id, username, name, role, invitedAt );

        return userProfileResponse;
    }
}

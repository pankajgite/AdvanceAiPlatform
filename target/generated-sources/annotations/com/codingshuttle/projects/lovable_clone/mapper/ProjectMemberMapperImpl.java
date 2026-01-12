package com.codingshuttle.projects.lovable_clone.mapper;

import com.codingshuttle.projects.lovable_clone.dto.member.MemberResponse;
import com.codingshuttle.projects.lovable_clone.entity.ProjectMember;
import com.codingshuttle.projects.lovable_clone.entity.User;
import com.codingshuttle.projects.lovable_clone.enums.ProjectRole;
import java.time.Instant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-12T00:27:29+0530",
    comments = "version: 1.6.0, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class ProjectMemberMapperImpl implements ProjectMemberMapper {

    @Override
    public MemberResponse toProjectMemberResponseFromOwner(User owner) {
        if ( owner == null ) {
            return null;
        }

        Long userId = null;
        String email = null;
        String name = null;
        String avatarUrl = null;

        userId = owner.getId();
        email = owner.getEmail();
        name = owner.getName();
        avatarUrl = owner.getAvatarUrl();

        ProjectRole projectRole = ProjectRole.OWNER;
        Instant invitedAt = null;

        MemberResponse memberResponse = new MemberResponse( userId, email, name, avatarUrl, projectRole, invitedAt );

        return memberResponse;
    }

    @Override
    public MemberResponse toProjectMemberResponseFromProjectMember(ProjectMember projectMember) {
        if ( projectMember == null ) {
            return null;
        }

        Long userId = null;
        String email = null;
        String name = null;
        String avatarUrl = null;
        ProjectRole projectRole = null;
        Instant invitedAt = null;

        userId = projectMemberUserId( projectMember );
        email = projectMemberUserEmail( projectMember );
        name = projectMemberUserName( projectMember );
        avatarUrl = projectMemberUserAvatarUrl( projectMember );
        projectRole = projectMember.getProjectRole();
        invitedAt = projectMember.getInvitedAt();

        MemberResponse memberResponse = new MemberResponse( userId, email, name, avatarUrl, projectRole, invitedAt );

        return memberResponse;
    }

    private Long projectMemberUserId(ProjectMember projectMember) {
        User user = projectMember.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    private String projectMemberUserEmail(ProjectMember projectMember) {
        User user = projectMember.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getEmail();
    }

    private String projectMemberUserName(ProjectMember projectMember) {
        User user = projectMember.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getName();
    }

    private String projectMemberUserAvatarUrl(ProjectMember projectMember) {
        User user = projectMember.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getAvatarUrl();
    }
}

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
    date = "2026-01-15T17:34:14+0530",
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
        String username = null;
        String name = null;

        userId = owner.getId();
        username = owner.getUsername();
        name = owner.getName();

        ProjectRole projectRole = ProjectRole.OWNER;
        Instant invitedAt = null;

        MemberResponse memberResponse = new MemberResponse( userId, username, name, projectRole, invitedAt );

        return memberResponse;
    }

    @Override
    public MemberResponse toProjectMemberResponseFromProjectMember(ProjectMember projectMember) {
        if ( projectMember == null ) {
            return null;
        }

        Long userId = null;
        String username = null;
        String name = null;
        ProjectRole projectRole = null;
        Instant invitedAt = null;

        userId = projectMemberUserId( projectMember );
        username = projectMemberUserUsername( projectMember );
        name = projectMemberUserName( projectMember );
        projectRole = projectMember.getProjectRole();
        invitedAt = projectMember.getInvitedAt();

        MemberResponse memberResponse = new MemberResponse( userId, username, name, projectRole, invitedAt );

        return memberResponse;
    }

    private Long projectMemberUserId(ProjectMember projectMember) {
        User user = projectMember.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    private String projectMemberUserUsername(ProjectMember projectMember) {
        User user = projectMember.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getUsername();
    }

    private String projectMemberUserName(ProjectMember projectMember) {
        User user = projectMember.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getName();
    }
}

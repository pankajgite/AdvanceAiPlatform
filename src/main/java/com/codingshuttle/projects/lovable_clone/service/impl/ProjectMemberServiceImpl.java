package com.codingshuttle.projects.lovable_clone.service.impl;

import com.codingshuttle.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.codingshuttle.projects.lovable_clone.dto.member.MemberResponse;
import com.codingshuttle.projects.lovable_clone.dto.member.UpdateMemberRoleRequest;
import com.codingshuttle.projects.lovable_clone.entity.Project;
import com.codingshuttle.projects.lovable_clone.entity.ProjectMember;
import com.codingshuttle.projects.lovable_clone.entity.ProjectMemberId;
import com.codingshuttle.projects.lovable_clone.entity.User;
import com.codingshuttle.projects.lovable_clone.mapper.ProjectMemberMapper;
import com.codingshuttle.projects.lovable_clone.repository.ProjectMemberRepository;
import com.codingshuttle.projects.lovable_clone.repository.ProjectRepository;
import com.codingshuttle.projects.lovable_clone.repository.UserRepository;
import com.codingshuttle.projects.lovable_clone.service.ProjectMemberService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    private final UserRepository userRepository;

    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        Project project = getAccessableProjectById(projectId, userId);
        List<MemberResponse> memberList = new ArrayList<>();
        memberList.add(projectMemberMapper.toProjectMemberResponseFromOwner(project.getOwner()));
        memberList.addAll(
                projectMemberRepository.findByIdProjectId(projectId)
                        .stream()
                        .map(projectMemberMapper::toProjectMemberResponseFromProjectMember)
                        .toList());

        return memberList;
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        Project project = getAccessableProjectById(projectId, userId);


        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("You Cannot Invite You Are not the Owner!!");
        }

        User invitee = userRepository.findByEmail(request.email()).orElseThrow(()->new RuntimeException("User Not Found With this Email: "+request.email()));

        if(invitee.getId().equals(userId)){
            throw new RuntimeException("Cannot Invite yourself");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId,invitee.getId());

        if(projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("Cannot Invite Once Again");
        }
        ProjectMember member = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())
                .invitedAt(Instant.now())
                .build();
        projectMemberRepository.save(member);


        return projectMemberMapper.toProjectMemberResponseFromProjectMember(member);
    }

    @Override
    public void removeProjectMember(Long projectId, Long memberId, Long userId) {
        Project project = getAccessableProjectById(projectId,userId);

        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("You Are not the Owner!!");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId,memberId);

        ProjectMember member = projectMemberRepository.findById(projectMemberId).orElseThrow(()->new RuntimeException("Member Not Found"));

        projectMemberRepository.delete(member);


    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId) {
        Project project = getAccessableProjectById(projectId,userId);

        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("You Are not the Owner!!");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId,memberId);

        ProjectMember member = projectMemberRepository.findById(projectMemberId).orElseThrow(()->new RuntimeException("Member Not Found"));

        member.setProjectRole(request.role());

        projectMemberRepository.save(member);

        return projectMemberMapper.toProjectMemberResponseFromProjectMember(member);
    }

    public Project getAccessableProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessableProjectById(projectId, userId).orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
    }
}

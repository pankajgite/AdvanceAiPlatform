package com.codingshuttle.projects.lovable_clone.service.impl;

import com.codingshuttle.projects.lovable_clone.dto.project.ProjectRequest;
import com.codingshuttle.projects.lovable_clone.dto.project.ProjectResponse;
import com.codingshuttle.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.codingshuttle.projects.lovable_clone.dto.suscription.PlanResponse;
import com.codingshuttle.projects.lovable_clone.entity.Project;
import com.codingshuttle.projects.lovable_clone.entity.ProjectMember;
import com.codingshuttle.projects.lovable_clone.entity.ProjectMemberId;
import com.codingshuttle.projects.lovable_clone.entity.User;
import com.codingshuttle.projects.lovable_clone.enums.ProjectRole;
import com.codingshuttle.projects.lovable_clone.error.ResourceNotFoundException;
import com.codingshuttle.projects.lovable_clone.mapper.ProjectMapper;
import com.codingshuttle.projects.lovable_clone.repository.ProjectMemberRepository;
import com.codingshuttle.projects.lovable_clone.repository.ProjectRepository;
import com.codingshuttle.projects.lovable_clone.repository.UserRepository;
import com.codingshuttle.projects.lovable_clone.security.AuthUtil;
import com.codingshuttle.projects.lovable_clone.service.PlanService;
import com.codingshuttle.projects.lovable_clone.service.ProjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@Slf4j
public class ProjectServiceImpl implements ProjectService {
    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;
    ProjectMemberRepository projectMemberRepository;
    AuthUtil authUtil;


    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        Long userId=authUtil.getCurrentUserId();
//        User owner=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User",userId.toString()));
        User owner=userRepository.getReferenceById(userId);
        Project project = Project.builder()
                .name(request.name())
                .isPublic(false)
                .build();

        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(),userId);
        project=projectRepository.save(project);
        ProjectMember member = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(owner)
                .projectRole(ProjectRole.OWNER)
                .build();

        projectMemberRepository.save(member);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects() {
        List<Project> projects=projectRepository.findAllAccessibleProjectsByUser(authUtil.getCurrentUserId());
        return projectMapper.toProjectSummaryResponse(projects);
    }

    @Override
    @PreAuthorize("@security.canViewProject(#id)")
    public ProjectResponse getProjectById(Long id) {
        Project project=getAccessableProjectById(id);
        return projectMapper.toProjectResponse(project);
    }



    @Override
    @PreAuthorize("@security.canEditProject(#id)")
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        log.info("Under Update Project BY"+authUtil.getCurrentUserId());
        Project project=getAccessableProjectById(id);
        project.setName(request.name());
        projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    @PreAuthorize("@security.canDeleteProject(#id)")
    public void softDeleteProject(Long id) {
        Project project= getAccessableProjectById(id);

        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }


    public Project getAccessableProjectById(Long projectId){

        return projectRepository.findAccessableProjectById(projectId,authUtil.getCurrentUserId()).orElseThrow(()->new ResourceNotFoundException("Project",projectId.toString()));
    }
}

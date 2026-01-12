package com.codingshuttle.projects.lovable_clone.service.impl;

import com.codingshuttle.projects.lovable_clone.dto.project.ProjectRequest;
import com.codingshuttle.projects.lovable_clone.dto.project.ProjectResponse;
import com.codingshuttle.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.codingshuttle.projects.lovable_clone.dto.suscription.PlanResponse;
import com.codingshuttle.projects.lovable_clone.entity.Project;
import com.codingshuttle.projects.lovable_clone.entity.User;
import com.codingshuttle.projects.lovable_clone.error.ResourceNotFoundException;
import com.codingshuttle.projects.lovable_clone.mapper.ProjectMapper;
import com.codingshuttle.projects.lovable_clone.repository.ProjectRepository;
import com.codingshuttle.projects.lovable_clone.repository.UserRepository;
import com.codingshuttle.projects.lovable_clone.service.PlanService;
import com.codingshuttle.projects.lovable_clone.service.ProjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class ProjectServiceImpl implements ProjectService {
    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;


    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {
        User owner=userRepository.findById(userId).orElseThrow();
        System.out.println("under Implementation");
        Project project = Project.builder()
                .name(request.name())
                .owner(owner)
                .isPublic(false)
                .build();
        project=projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {
        List<Project> projects=projectRepository.findAllAccessibleProjectsByUser(userId);
        return projectMapper.toProjectSummaryResponse(projects);
    }

    @Override
    public ProjectResponse getProjectById(Long id, Long userId) {
        Project project=getAccessableProjectById(id,userId);
        return projectMapper.toProjectResponse(project);
    }



    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        Project project=getAccessableProjectById(id,userId);
        project.setName(request.name());
        projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDeleteProject(Long id, Long userId) {
        Project project= getAccessableProjectById(id,userId);

        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("You Are not allowed to Delete");
        }
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }


    public Project getAccessableProjectById(Long projectId,Long userId){
        return projectRepository.findAccessableProjectById(projectId,userId).orElseThrow(()->new ResourceNotFoundException("Project",projectId.toString()));
    }
}

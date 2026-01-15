package com.codingshuttle.projects.lovable_clone.mapper;

import com.codingshuttle.projects.lovable_clone.dto.auth.UserProfileResponse;
import com.codingshuttle.projects.lovable_clone.dto.project.ProjectResponse;
import com.codingshuttle.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.codingshuttle.projects.lovable_clone.entity.Project;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-15T17:34:14+0530",
    comments = "version: 1.6.0, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectResponse toProjectResponse(Project project) {
        if ( project == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Instant createdAt = null;
        Instant updatedAt = null;

        id = project.getId();
        name = project.getName();
        createdAt = project.getCreatedAt();
        updatedAt = project.getUpdatedAt();

        UserProfileResponse owner = null;

        ProjectResponse projectResponse = new ProjectResponse( id, name, createdAt, updatedAt, owner );

        return projectResponse;
    }

    @Override
    public List<ProjectSummaryResponse> toProjectSummaryResponse(List<Project> projects) {
        if ( projects == null ) {
            return null;
        }

        List<ProjectSummaryResponse> list = new ArrayList<ProjectSummaryResponse>( projects.size() );
        for ( Project project : projects ) {
            list.add( projectToProjectSummaryResponse( project ) );
        }

        return list;
    }

    protected ProjectSummaryResponse projectToProjectSummaryResponse(Project project) {
        if ( project == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Instant createdAt = null;
        Instant updatedAt = null;

        id = project.getId();
        name = project.getName();
        createdAt = project.getCreatedAt();
        updatedAt = project.getUpdatedAt();

        ProjectSummaryResponse projectSummaryResponse = new ProjectSummaryResponse( id, name, createdAt, updatedAt );

        return projectSummaryResponse;
    }
}

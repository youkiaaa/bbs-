package com.atyume.modules.system.dto;

import com.atyume.modules.system.po.Role;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RoleDto {

    private Long id;
    private String role;
    private String description;
    private String resourceIds;
    private List<Long> resourceIdList;
    private Boolean available;
    private String resourceNames;

    public RoleDto() {

    }

    public RoleDto(Role role) {
        this.id = role.getId();
        this.role = role.getRole();
        this.description = role.getDescription();
        this.resourceIds = role.getResourceIds();
        this.resourceIdList = Arrays.asList(role.getResourceIds().split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
        this.available = role.getAvailable();
    }


    public String getResourceNames() {
        return resourceNames;
    }

    public void setResourceNames(String resourceNames) {
        this.resourceNames = resourceNames;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public List<Long> getResourceIdList() {
        return resourceIdList;
    }

    public void setResourceIdList(List<Long> resourceIdList) {
        this.resourceIdList = resourceIdList;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}

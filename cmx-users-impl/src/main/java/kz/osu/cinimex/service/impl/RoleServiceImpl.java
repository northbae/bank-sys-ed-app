package kz.osu.cinimex.service.impl;

import kz.osu.cinimex.dto.RoleDto;
import kz.osu.cinimex.entity.Role;
import kz.osu.cinimex.entity.SearchCriteria;
import kz.osu.cinimex.entity.specification.RoleWithCriteriaSpecification;
import kz.osu.cinimex.exception.EntryExistsException;
import kz.osu.cinimex.mapper.RoleMapper;
import kz.osu.cinimex.repository.RoleRepository;
import kz.osu.cinimex.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.data.web.config.SpringDataJacksonConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    @Transactional
    public void createRole(RoleDto roleDto) {
        if (roleRepository.existsById(roleDto.getName())) {
            throw new EntryExistsException("Указанная роль уже существует");
        }
        else {
            roleRepository.save(roleMapper.roleDtoToRole(roleDto));
        }
    }

    @Override
    public PagedModel<RoleDto> getAllRoles(Pageable pageable, String name, String description) {
        Specification<Role> specificationName = new RoleWithCriteriaSpecification(
                new SearchCriteria("name", "=", name));
        Specification<Role> specificationDescription = new RoleWithCriteriaSpecification(
                new SearchCriteria("description", "like", description));
        Specification<Role> specification = Specification.where(specificationName).and(specificationDescription);

        return new PagedModel<>(
                roleRepository.findAll(specification, pageable)
                .map(roleMapper::roleToRoleDto));
    }

    @Override
    public void deleteRoleById(String name) {
        roleRepository.deleteById(name);
    }

    @Override
    @Transactional
    public List<Role> getRolesByName(List<String> roleNames) {
        return roleRepository.findAllById(roleNames);
    }

    @Override
    @Transactional
    public boolean existsByName(String name) {
        return roleRepository.existsById(name);
    }
}

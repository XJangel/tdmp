package com.jx.tdmp.service.impl;

import com.jx.tdmp.entity.Organization;
import com.jx.tdmp.mapper.OrganizationMapper;
import com.jx.tdmp.service.Interface.OrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jiejie Xu
 * @since 2020-12-29
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    @Override
    public List<Organization> listOrganizations() {
        return null;
    }

    @Override
    public Organization getOrganizationById(String id) {
        return null;
    }

    @Override
    public Organization createOrganization(Organization Organization) {
        // 启动相应的fabric-ca，并暴露服务（service，集群内部能访问即可）
        // 将organization写入mysql name即服务域名？？
        return null;
    }

    @Override
    public Organization updateOrganization(Organization Organization) {
        return null;
    }

    @Override
    public boolean deleteOrganization(String id) {
        return false;
    }
}

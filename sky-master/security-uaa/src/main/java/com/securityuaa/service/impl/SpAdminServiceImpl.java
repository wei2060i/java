package com.securityuaa.service.impl;

import com.securityuaa.entity.SpAdminEntity;
import com.securityuaa.repository.SpAdminRepository;
import com.securityuaa.service.SpAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author sky
 * @date 2022/2/6 2:23
 */
@Service
public class SpAdminServiceImpl implements SpAdminService, UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(SpAdminServiceImpl.class);

    @Resource
    private SpAdminRepository spAdminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SpAdminEntity user = spAdminRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        List<String> userAllPowerIds = spAdminRepository.getUserAllPowerIds(user.getId());
        Set<Long> powerIdSet = new HashSet<>();
        for (String userAllPowerId : userAllPowerIds) {
            String substring = userAllPowerId.substring(1, userAllPowerId.length() - 1);
            if (StringUtils.isEmpty(substring)) {
                continue;
            }
            String[] split = substring.split(",");
            for (String s : split) {
                powerIdSet.add(Long.valueOf(s));
            }
        }
        List<String> userPermissions = spAdminRepository.getUserPermissions(powerIdSet);
        String[] permission = new String[userPermissions.size()];
        userPermissions.toArray(permission);

        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(permission)
                //.roles(roles) role authorities 不能一起使用
                .accountLocked(user.getLocked())
                .build();
    }

}
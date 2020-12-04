package com.itheima.shiro;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm  extends AuthorizingRealm {

    @Autowired
    UserService userService;
    /*
    执行 授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权。。。。");
        // 给资源授权
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        User u=(User)subject.getPrincipal();
        User dbUser=userService.findById(u.getId());
        info.addStringPermission(dbUser.getPerms());
        return info;
    }


    /*
      执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        System.out.println("执行认证逻辑。。。.");
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)arg0;
        User u=userService.findByName(token.getUsername());
        if(u ==null){
            // 用户名不存在  shiro底层抛出UnKnowAccountException
            return null;
        }
        //参数是  Principal 密码  名字
        return new SimpleAuthenticationInfo(u,u.getPassword(),"");
    }
}

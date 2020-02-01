package cn.edu.scau.employee.config.shiro;

import cn.edu.scau.common.result.CommonResult;
import cn.edu.scau.common.util.ObjectUtil;
import cn.edu.scau.employee.dao.UserDao;
import cn.edu.scau.employee.entity.Resource;
import cn.edu.scau.employee.entity.User;
import cn.edu.scau.employee.service.ResourceService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chen.jiale
 * @description 自定义校验realm
 * @date 2019/11/8 16:00
 */
public class UserRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private ResourceService resourceService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        logger.info("用户: {} 权限校验中", user.getUsername());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<Resource> resources = resourceService.findByRoleId(user.getRoleId());
        List<String> permissions = resources.stream().map(resource -> resource.getUrl())
                .collect(Collectors.toList());
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        logger.info("用户: {} 登录校验中", username);
        userDao.findByUsername(username);
        List<User> users = userDao.findByUsername(username);
        if (ObjectUtil.isEmpty(users) || users.size() <= 0) {
            throw new UnauthenticatedException();
        }
        ByteSource salt = ByteSource.Util.bytes(username);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                users.get(0),
                users.get(0).getPassword(),
                salt,
                getName()
        );
        return authenticationInfo;
    }

    @Override
    public boolean isPermitted(PrincipalCollection principals, Permission permission) {
        AuthorizationInfo info = getAuthorizationInfo(principals);
        Collection<Permission> permissions = getPermissions(info);
        User user = (User) principals.getPrimaryPrincipal();
        if (permissions.isEmpty()) {
            logger.info("用户: {} 权限校验失败", user.getUsername());
            return false;
        }
        for (Permission perm : permissions) {
            if (perm.implies(permission)) {
                logger.info("用户: {} 权限校验成功", user.getUsername());
                return true;
            }
        }
        logger.info("用户: {} 权限校验失败", user.getUsername());
        return false;
    }
}

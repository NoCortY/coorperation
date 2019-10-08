package com.objectspace.coorperation.config;

import com.objectspace.coorperation.dao.ShiroDao;
import com.objectspace.coorperation.entity.UrlFilter;
import com.objectspace.coorperation.shiro.DatabaseRealm;
import com.objectspace.coorperation.shiro.URLPathMatchingFilter;
import com.objectspace.coorperation.util.SpringUtil;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @Description: Shiro框架配置类
* @Author: NoCortY
* @Date: 2019/10/4
*/
@Configuration
public class ShiroConfig {

    /**
     * @Description: 配置Shiro的过滤器工厂，包含url默认过滤器和安全管理器
     * @Param: [securityManager, filterChainDefinitionMap]
     * @return: org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,LinkedHashMap<String,String> filterChainDefinitionMap) {
        //配置shiro的过滤器工厂类
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置过滤器的安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //配置登录请求地址
        shiroFilterFactoryBean.setLoginUrl("/frontend/login");
        //如果访问到未授权url，跳转到403界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/frontend/403");
        //设置免认证url
        /*
        2019.10.4 改为在数据库中进行配置

        LinkedHashMap<String,String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/frontend/index", "anon");
        filterChainDefinitionMap.put("/frontend/register","anon");
        filterChainDefinitionMap.put("/usercontroller/registeruser", "anon");
        filterChainDefinitionMap.put("/usercontroller/login", "anon");
        filterChainDefinitionMap.put("/captchacontroller/getcaptcha","anon");
        filterChainDefinitionMap.put("/captchacontroller/iscaptchaexist","anon");
        filterChainDefinitionMap.put("/frontend/login", "anon");
        filterChainDefinitionMap.put("/views/unauthorized", "anon");
        filterChainDefinitionMap.put("/views/**","anon");
        filterChainDefinitionMap.put("/captcha/**","anon");
        filterChainDefinitionMap.put("/assets/**","anon");*/
        //配置退出
        filterChainDefinitionMap.put("/logout", "logout");
        //除以上以外所有url都必须通过认证才可以访问
        filterChainDefinitionMap.put("/**", "url");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        Map<String, Filter> filters = new LinkedHashMap<String,Filter>();
        filters.put("url", URLPathMatchingFilter());
        filters.put("logout", logoutFilter());
        shiroFilterFactoryBean.setFilters(filters);
        return shiroFilterFactoryBean;
    }

    /**为logoutFilter创建注册bean(将该过滤器交由shiro管理)
     * 2019.08.26脱离SpringBoot进行独立配置
     * SpringBoot文档:任何Servlet或Filter bean都将自动注册到servlet容器中。
     * 要禁用特定Filter或Servlet bean的注册，请为其创建注册bean并将其标记为禁用。
     * @param logoutFilter
     * @return
     */
    /**
     * @Description: 退出登录的拦截器
     * @Param: []
     * @return: org.apache.shiro.web.filter.authc.LogoutFilter
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public LogoutFilter logoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/index");
        return logoutFilter;
    }
    /*开启注释会产生循环重定向问题
     * 症结大概存在于SpringBoot和Shiro会为该拦截器都加载到自己的容器中
     * 导致有些页面先走anno拦截器再走该自定义拦截器
     * 而该拦截器内部逻辑是未登录自动跳转到登陆界面
     * 于是每次在访问login页面时都会产生循环重定向问题
     *@Bean(name="urlPathMatchingFilter")
     * 解决方案：配置时直接new而不使用 @Bean方式配置。*/

    /**
     * @Description: 自定义url拦截器Bean，只要不存在于FilterChainDefinitionMap中value为anon的url，都需要到这个拦截器进行权限验证
     * @Param: []
     * @return: com.objectspace.coorperation.shiro.URLPathMatchingFilter
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public URLPathMatchingFilter URLPathMatchingFilter() {
        URLPathMatchingFilter urlPathMatchingFilter = new URLPathMatchingFilter();
        return urlPathMatchingFilter;
    }


    /**
     * @Description:会话ID生成器
     * @Param: []
     * @return: org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    @Bean(name="sessionIdGenerator")
    public JavaUuidSessionIdGenerator javaUuidSessionIdGenerator() {
        JavaUuidSessionIdGenerator javaUuidSessionIdGenerator = new JavaUuidSessionIdGenerator();
        return javaUuidSessionIdGenerator;
    }
    /**
     * @Description:会话Cookie模板 关闭浏览器立即失效
     * @Param: []
     * @return: org.apache.shiro.web.servlet.SimpleCookie
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    @Bean(name="sessionIdCookie")
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("shiroSessionId");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(-1);
        return simpleCookie;
    }
    /**
     * @Description:  会话DAO
     * @Param: [sessionIdGenerator]
     * @return: org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    @Bean(name="sessionDAO")
    public EnterpriseCacheSessionDAO enterpriseCacheSessionDAO(JavaUuidSessionIdGenerator sessionIdGenerator) {
        EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
        enterpriseCacheSessionDAO.setSessionIdGenerator(sessionIdGenerator);
        return enterpriseCacheSessionDAO;
    }
    /**
     * @Description:  会话验证调度器，每30分钟执行一次验证 ，设定会话超时及保存
     * @Param: [sessionManager]
     * @return: org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    @Bean(name="sessionValidateionScheduler")
    public ExecutorServiceSessionValidationScheduler executorServiceSessionValidationScheduler(SessionManager sessionManager) {
        ExecutorServiceSessionValidationScheduler executorServiceSessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
        executorServiceSessionValidationScheduler.setInterval(1800000);
        executorServiceSessionValidationScheduler.setSessionManager((ValidatingSessionManager) sessionManager);
        return executorServiceSessionValidationScheduler;
    }
    /**
     * @Description: 会话管理器 参数必须配置懒加载，否则判定为循环依赖
     * @Param: [sessionDAO, sessionIdCookie, sessionValidationScheduler]
     * @return: org.apache.shiro.web.session.mgt.DefaultWebSessionManager
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    @Bean(name="sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager(EnterpriseCacheSessionDAO sessionDAO,SimpleCookie sessionIdCookie,@Lazy ExecutorServiceSessionValidationScheduler sessionValidationScheduler) {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        //全局会话超时时间（单位毫秒），默认30分钟
        defaultWebSessionManager.setGlobalSessionTimeout(1800000);
        defaultWebSessionManager.setDeleteInvalidSessions(true);
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        defaultWebSessionManager.setSessionValidationScheduler(sessionValidationScheduler);
        defaultWebSessionManager.setSessionIdCookie(sessionIdCookie);
        defaultWebSessionManager.setSessionDAO(sessionDAO);
        defaultWebSessionManager.setSessionIdCookieEnabled(true);
        return defaultWebSessionManager;
    }
    /**
     * @Description:  安全管理器
     * @Param: [databaseRealm, sessionManager]
     * @return: org.apache.shiro.mgt.SecurityManager
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    @Bean(name="securityManager")
    public SecurityManager securityManager(DatabaseRealm databaseRealm, DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(databaseRealm);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }
    /**
     * 相当于调用SecurityUtils.setSecurityManager(securityManager)
     * @param securityManager
     * @return
     */
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(SecurityManager securityManager) {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(securityManager);
        return methodInvokingFactoryBean;
    }
    /**
     * @Description: 密码匹配器
     * @Param: []
     * @return: org.apache.shiro.authc.credential.HashedCredentialsMatcher
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    @Bean(name="credentialsMatcher")
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(ConstantValue.PASSWORD_ENCRYPTION_TYPE);
        hashedCredentialsMatcher.setHashIterations(ConstantValue.PASSWORD_ENCRYPTION_TIME);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }
    /**
     * @Description:  授权/认证器
     * @Param: [credentialsMatcher]
     * @return: com.objectspace.coorperation.shiro.DatabaseRealm
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    @Bean("databaseRealm")
    public DatabaseRealm databaseRealm(HashedCredentialsMatcher credentialsMatcher) {
        DatabaseRealm databaseRealm = new DatabaseRealm();
        databaseRealm.setCredentialsMatcher(credentialsMatcher);
        return databaseRealm;
    }
    /**
     * @Description: 保证实现了Shiro内部lifecycle函数的bean执行
     * @Param: []
     * @return: org.apache.shiro.spring.LifecycleBeanPostProcessor
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
        return lifecycleBeanPostProcessor;
    }
    /**
     * @Description:  配置FilterChainDefinitionMap 服务器启动时从服务器读取url对应的过滤器
     * @Param: []
     * @return: java.util.LinkedHashMap<java.lang.String,java.lang.String>
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    @Bean("filterChainDefinitionMap")
    @DependsOn({"shiroDao","springUtil"})
    LinkedHashMap<String,String> filterChainDefinitionMap(){
        ShiroDao shiroDao = SpringUtil.getBean(ShiroDao.class);
        List<UrlFilter> urlFilterList = shiroDao.listUrlFilter();
        LinkedHashMap<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        for(UrlFilter urlFilter:urlFilterList){
            filterChainDefinitionMap.put(urlFilter.getUrl(),urlFilter.getFilterName());
        }
        return filterChainDefinitionMap;
    }
}

package com.objectspace.coorperation.config;

import com.objectspace.coorperation.shiro.DatabaseRealm;
import com.objectspace.coorperation.shiro.URLPathMatchingFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
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
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.annotation.Lazy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro的配置类
 * @author Object
 *
 */
@Configuration
public class ShiroConfig {
    /**
     * 配置shiro的过滤器工厂类
     * @param securityManager
     * @return
     */
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        //配置shiro的过滤器工厂类
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置过滤器的安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //配置登录请求地址
        shiroFilterFactoryBean.setLoginUrl("/login");
        //如果访问到未授权url，跳转到403界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        //设置免认证url
        LinkedHashMap<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        filterChainDefinitionMap.put("/frontend/index", "anon");
        filterChainDefinitionMap.put("/frontend/register","anon");
        filterChainDefinitionMap.put("/usercontroller/registeruser", "anon");
        filterChainDefinitionMap.put("/usercontroller/login", "anon");
        filterChainDefinitionMap.put("/captchacontroller/getcaptcha","anon");
        filterChainDefinitionMap.put("/captchacontroller/iscaptchaexist","anon");
        filterChainDefinitionMap.put("/frontend/login", "anon");
        filterChainDefinitionMap.put("/unauthorized", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("../static/**", "anon");
        filterChainDefinitionMap.put("/views/**","anon");
        filterChainDefinitionMap.put("/assets/**","anon");
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

    /**
     * 配置退出过滤器
     * @return
     */
    public LogoutFilter logoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/index");
        return logoutFilter;
    }
    /**为logoutFilter创建注册bean(将该过滤器交由shiro管理)
     *
     * SpringBoot文档:任何Servlet或Filter bean都将自动注册到servlet容器中。
     * 要禁用特定Filter或Servlet bean的注册，请为其创建注册bean并将其标记为禁用。
     * @param logoutFilter
     * @return
     */
    /**
     * 路径匹配过滤器 所有需要授权的请求都将被过滤
     * @return
     */
    /*开启注释会产生循环重定向问题
     * 症结大概存在于SpringBoot和Shiro会为该拦截器都加载到自己的容器中
     * 导致有些页面先走anno拦截器再走该自定义拦截器
     * 而该拦截器内部逻辑是未登录自动跳转到登陆界面
     * 于是每次在访问login页面时都会产生循环重定向问题
     *
     * 解决方案：配置时直接new而不使用 @Bean方式配置。*/
    //@Bean(name="urlPathMatchingFilter")
    public URLPathMatchingFilter URLPathMatchingFilter() {
        URLPathMatchingFilter urlPathMatchingFilter = new URLPathMatchingFilter();
        return urlPathMatchingFilter;
    }

    /**
     * 配置会话ID生成器
     * @return
     */
    @Bean(name="sessionIdGenerator")
    public JavaUuidSessionIdGenerator javaUuidSessionIdGenerator() {
        JavaUuidSessionIdGenerator javaUuidSessionIdGenerator = new JavaUuidSessionIdGenerator();
        return javaUuidSessionIdGenerator;
    }
    /**
     * 会话Cookie模板 关闭浏览器立即失效
     * @return
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
     * 配置会话DAO
     * @param sessionIdGenerator
     * @return
     */
    @Bean(name="sessionDAO")
    public EnterpriseCacheSessionDAO enterpriseCacheSessionDAO(JavaUuidSessionIdGenerator sessionIdGenerator) {
        EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
        enterpriseCacheSessionDAO.setSessionIdGenerator(sessionIdGenerator);
        return enterpriseCacheSessionDAO;
    }
    /**
     * 会话验证调度器，每30分钟执行一次验证 ，设定会话超时及保存
     * @param sessionManager
     * @return
     */
    @Bean(name="sessionValidateionScheduler")
    public ExecutorServiceSessionValidationScheduler executorServiceSessionValidationScheduler(SessionManager sessionManager) {
        ExecutorServiceSessionValidationScheduler executorServiceSessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
        executorServiceSessionValidationScheduler.setInterval(1800000);
        executorServiceSessionValidationScheduler.setSessionManager((ValidatingSessionManager) sessionManager);
        return executorServiceSessionValidationScheduler;
    }
    /**
     * 会话管理器
     * 参数必须配置懒加载，否则判定为循环依赖
     * @param sessionValidationScheduler
     * @param sessionDAO
     * @param sessionIdCookie
     * @return
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
     * 安全管理器
     * @return
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
     * 密码匹配器
     * @return
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
     * 授权/认证器
     * @param credentialsMatcher
     * @return
     */
    @Bean("databaseRealm")
    public DatabaseRealm databaseRealm(HashedCredentialsMatcher credentialsMatcher) {
        DatabaseRealm databaseRealm = new DatabaseRealm();
        databaseRealm.setCredentialsMatcher(credentialsMatcher);
        return databaseRealm;
    }
    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     * @return
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
        return lifecycleBeanPostProcessor;
    }
}

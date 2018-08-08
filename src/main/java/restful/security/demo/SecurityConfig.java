package restful.security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import restful.security.demo.main.restsec.AuthenticationService;
import restful.security.demo.main.restsec.TokenManager;

//@EnableWebSecurity
//@Configuration
class SecurityConfig extends WebSecurityConfigurerAdapter {


//    <bean:bean id="tokenManager" class="restful.security.demo.main.secimpl.TokenManagerSingle"/>
//
//	<bean:bean id="authenticationService" class="restful.security.demo.main.secimpl.AuthenticationServiceDefault"
//    c:authenticationManager-ref="restAuthenticationManager" c:tokenManager-ref="tokenManager"/>
//
//	<bean:bean id="restAuthenticationFilter" class="restful.security.demo.main.restsec.TokenAuthenticationFilter"
//    c:authenticationService-ref="authenticationService" c:logoutLink="/logout"/>


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.addFilterBefore(sessionRepositoryFilter, ChannelProcessingFilter.class)
                .csrf().disable();

        //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/*").permitAll();
        http.authorizeRequests().antMatchers("/secure/**").fullyAuthenticated();

//
//        <http realm="Protected API"
//        use-expressions="true"
//        create-session="stateless"
//        entry-point-ref="unauthorizedEntryPoint"
//        authentication-manager-ref="restAuthenticationManager">
//
//		<!--
//                Added after moving to Spring Boot 1.3 + Spring Security 4.x,
//                otherwise we could not login with basic auth because of: Expected CSRF token not found
//        TODO: Please, mind, that I did not migrate this XML to Spring Security 4.x except for this element
//                -->
//		<csrf disabled="true"/>
//
//		<!--
//                This is not easily possible, because it causes:
//        DEBUG o.s.s.w.a.ExceptionTranslationFilter - Authentication exception occurred; redirecting to authentication entry point
//        org.springframework.security.authentication.AuthenticationCredentialsNotFoundException: An Authentication object was not found in the SecurityContext
//                -->
//		<!--<anonymous enabled="false"/>-->
//		<custom-filter ref="restAuthenticationFilter" position="FORM_LOGIN_FILTER"/>
//
//		<intercept-url pattern="/*" access="permitAll"/>
//		<intercept-url pattern="/secure/**" access="isFullyAuthenticated()"/>
//	</http>
//
//	<bean:bean id="unauthorizedEntryPoint" class="restful.security.demo.main.restsec.UnauthorizedEntryPoint"/>
//
//	<bean:bean id="userDetailService" class="restful.security.demo.main.secimpl.MyUserDetailsService"/>
//
//	<authentication-manager id="restAuthenticationManager">
//		<authentication-provider user-service-ref="userDetailService">
//			<!--
//                Default password encoder is PlaintextPasswordEncoder, which fits with our hardcoded users.
//                Obviously not a good choice otherwise.
//                -->
//		</authentication-provider>
//	</authentication-manager>
//
//	<bean:bean id="tokenManager" class="restful.security.demo.main.secimpl.TokenManagerSingle"/>
//
//	<bean:bean id="authenticationService" class="restful.security.demo.main.secimpl.AuthenticationServiceDefault"
//        c:authenticationManager-ref="restAuthenticationManager" c:tokenManager-ref="tokenManager"/>
//
//	<bean:bean id="restAuthenticationFilter" class="restful.security.demo.main.restsec.TokenAuthenticationFilter"
//        c:authenticationService-ref="authenticationService" c:logoutLink="/logout"/>

    }
}

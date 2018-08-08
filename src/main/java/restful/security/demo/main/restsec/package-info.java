/**
 * This package contains drop-in solution for token-based authentication. It is self-contained with a couple of
 * extension points to implement - one needs to implement {@link restful.security.demo.main.restsec.AuthenticationService}
 * and {@link restful.security.demo.main.restsec.TokenManager}.
 * <p>
 * None of these classes rely on annotation based configuration.
 */
package restful.security.demo.main.restsec;
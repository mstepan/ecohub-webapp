package com.oracle.cerner.cloud.soarian.config;


import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Accessors(fluent = false, chain = false)
@ConfigurationProperties(prefix = "idcs")
public final class IdcsConfig {

    private static final String OPEN_ID_REDIRECT_URL_TEMPLATE =
        "%s/oauth2/v1/authorize?client_id=%s&response_type=code&scope=openid&redirect_uri=%s";

    private static final String TOKEN_URL_TEMPLATE = "%s/oauth2/v1/token";

    private static final String SOARIAN_REDIRECT_URL = "https://localhost:8443/soarian";

    private static final String INTROSPECT_TOKEN_URL_TEMPLATE = "%s/oauth2/v1/introspect";

    private String domainUrl;

    private String clientId;

    private String clientSecret;

    private String callbackUrl;

    /**
     * For details check https://docs.oracle.com/en-us/iaas/Content/Identity/api-getstarted/usingopenidconnect.htm#openidconnectauthcode
     *
     * @return OpenID redirect URL with scopes.
     */
    public String openIdRedirectUrl() {
        return OPEN_ID_REDIRECT_URL_TEMPLATE.formatted(domainUrl, clientId, callbackUrl);
    }

    public String tokenUrl() {
        return TOKEN_URL_TEMPLATE.formatted(domainUrl);
    }

    public String introspectTokenUrl() {
        return INTROSPECT_TOKEN_URL_TEMPLATE.formatted(domainUrl);
    }

    /**
     * https://docs.oracle.com/en-us/iaas/Content/Identity/api-getstarted/usingopenidconnect.htm#logoutopenidconnect
     */
    public String logoutUrl(String idToken){
        return "%s/oauth2/v1/userlogout?post_logout_redirect_uri=%s&state=c3004d28&id_token_hint=%s"
            .formatted(domainUrl, SOARIAN_REDIRECT_URL, idToken);
    }

}


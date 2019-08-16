package com.paypal.service;

import com.paypal.exception.EntityNotFoundException;
import com.paypal.repos.PaypalTokenInfo;
import com.paypal.repos.PaypalUserInfo;
import com.paypal.repos.User;
import com.paypal.repos.UserRepository;
import com.paypal.api.openidconnect.Session;
import com.paypal.api.openidconnect.Tokeninfo;
import com.paypal.api.openidconnect.Userinfo;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService
{
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Value("${paypal.client.app}")
    private String clientAppID;
    @Value("${paypal.client.secret}")
    private String clientAppSecret;
    @Override public void signup(String username, String password)
    {

    }




    @Override public void handleReturnURLFromPaypal(String code, String scope)
    {
        //first step is to save the user info returned to the return URL provided
        User user = saveUserWithAuthorizationCode(code,scope);
        //second step to call paypal to get access token
        Tokeninfo tokeninfo = getUserTokenInfo(user);
        //third step to get user info
        getUserInfo(user,tokeninfo);
    }

    public void getRedirectURL(){
        // Initialize apiContext with proper credentials and environment.
        APIContext context = new APIContext(clientAppID, clientAppSecret, "sandbox");

        List<String> scopes = new ArrayList<String>() {{
            /**
             * 'openid'
             * 'profile'
             * 'address'
             * 'email'
             * 'phone'
             * 'https://uri.paypal.com/services/paypalattributes'
             * 'https://uri.paypal.com/services/expresscheckout'
             * 'https://uri.paypal.com/services/invoicing'
             */
            add("openid");
            add("profile");
            add("email");
        }};
        String redirectUrl = Session.getRedirectURL("UserConsent", scopes, context);
        System.out.println(redirectUrl);
    }

    public User saveUserWithAuthorizationCode(String code,String scope){
        LOG.info("getAuthorizationCode code ::"+code+" scope ::"+scope);
        List<String> params = getTokensWithCollection(scope);
        User user;
        try
        {
            user = findCheckedUser(params.get(3));
            user.setPaypalAuthorizationCode(code);
//            user.setAddress(params.get(0));
//            user.setOpenid(params.get(1));
//            user.setProfile(params.get(2));
//            user.setEmail(params.get(3));
        }catch (EntityNotFoundException ex){
            LOG.error("Can't find user with email ::"+params.get(3),ex);
            user = new User(code);
        }
        userRepository.save(user);
        return user;

    }


    public List<String> getTokensWithCollection(String str) {
        return Collections.list(new StringTokenizer(str, "%20")).stream()
            .map(token -> (String) token)
            .collect(Collectors.toList());
    }

    private User findCheckedUser(String email) throws EntityNotFoundException{
        return userRepository.findByPaypalUserInfoEmail(email).orElseThrow(() -> new EntityNotFoundException("Can't find user with email ::"+email));
    }

    public Tokeninfo getUserTokenInfo(User user){
        LOG.info("getUserTokenInfo for ::"+user.getPaypalAuthorizationCode());
        Tokeninfo tokeninfo = null;
        try
        {
            APIContext context = new APIContext(clientAppID, clientAppSecret, "sandbox");
            tokeninfo = Tokeninfo.createFromAuthorizationCode(context, user.getPaypalAuthorizationCode());
            String accessToken = tokeninfo.getAccessToken();
            String refreshToken = tokeninfo.getRefreshToken();
           user.setPaypalTokenInfo(new PaypalTokenInfo(tokeninfo));
            userRepository.save(user);
        }catch (PayPalRESTException ex){
            LOG.error("exception occurred while trying to get access token",ex);
        }
        return tokeninfo;
    }


    public void getUserInfo(User user,Tokeninfo tokeninfo){
        try
        {
            // Initialize apiContext with proper credentials and environment. Also, set the refreshToken retrieved from previous step.
            APIContext userAPIContext = new APIContext(clientAppID, clientAppSecret, "sandbox").setRefreshToken(tokeninfo.getRefreshToken());

            Userinfo userinfo = Userinfo.getUserinfo(userAPIContext);
            user.setPaypalUserInfo(new PaypalUserInfo(userinfo));
            userRepository.save(user);
        }catch (PayPalRESTException ex){
            LOG.error("exception occurred while trying to get user info",ex);
        }
    }
}

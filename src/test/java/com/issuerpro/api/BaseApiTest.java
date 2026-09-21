package com.issuerpro.api;

import com.issuerpro.api.clients.AdminApiClient;
import com.issuerpro.api.clients.AuthApiClient;
import com.issuerpro.api.clients.CustomerApiClient;
import com.issuerpro.api.clients.TransactionApiClient;
import com.issuerpro.listeners.TestListener;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;

import java.util.Map;

/**
 * Base class for all RestAssured API tests.
 */
@Listeners(TestListener.class)
public abstract class BaseApiTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final AuthApiClient authApiClient = new AuthApiClient();
    protected final CustomerApiClient customerApiClient = new CustomerApiClient();
    protected final TransactionApiClient transactionApiClient = new TransactionApiClient();
    protected final AdminApiClient adminApiClient = new AdminApiClient();

    protected Map<String, String> getAdminCookies() {
        Response response = authApiClient.loginAsAdmin();
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to authenticate as Admin. Status: " + response.getStatusCode());
        }
        return response.getCookies();
    }
}

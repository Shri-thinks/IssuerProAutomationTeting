package com.issuerpro.api;

/**
 * Constants for REST API endpoints on IssuerPro.
 */
public final class ApiEndpoints {

    private ApiEndpoints() {}

    // Auth
    public static final String AUTH_LOGIN = "/auth/login";
    public static final String AUTH_LOGOUT = "/auth/logout";
    public static final String AUTH_ME = "/auth/me";

    // Dashboard
    public static final String DASHBOARD_STATS = "/dashboard/stats";

    // Customers
    public static final String CUSTOMERS = "/customers";
    public static final String CUSTOMER_BY_ID = "/customers/{id}";

    // Accounts
    public static final String ACCOUNTS = "/accounts";

    // Cards
    public static final String CARDS = "/cards";
    public static final String CARD_ACTION = "/cards/{action}/{id}";

    // Transactions
    public static final String TRANSACTIONS = "/transactions";
    public static final String TRANSACTIONS_PURCHASE = "/transactions/purchase";
    public static final String TRANSACTIONS_REFUND = "/transactions/refund";
    public static final String TRANSACTIONS_WITHDRAWAL = "/transactions/withdrawal";
    public static final String REVERSALS = "/reversals";

    // Settlement & ACH
    public static final String SETTLEMENTS_RUN = "/settlements/run";
    public static final String ACH_CREDIT = "/ach/credit";
    public static final String ACH_DEBIT = "/ach/debit";
    public static final String ACH_RETURN = "/ach/return";

    // Chargebacks
    public static final String CHARGEBACKS = "/chargebacks";

    // Admin & Testing
    public static final String ADMIN_RESET = "/admin/reset";
    public static final String ADMIN_FRAUD_CONFIG = "/admin/fraud-config";
    public static final String ADMIN_FRAUD_CONFIG_RESET = "/admin/fraud-config/reset";
}

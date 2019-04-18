package api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestURLBuilder {
    private static final Logger logger = LoggerFactory.getLogger(RequestURLBuilder.class);
    private String BASE_URL;
    private String apiVersion;
    private String endPoint;
    private String action;
    private String params;
    private StringBuilder result = new StringBuilder();

    public static RequestURLBuilder initDefaults() {
        String baseURL = "https://petstore.swagger.io";
        String apiVersion = "v2/";
        String endPoint = "pet/";
        RequestURLBuilder requestURLBuilder = new RequestURLBuilder();
        requestURLBuilder
                .withBaseURL(baseURL)
                .withAPIVersion(apiVersion)
                .withEndpoint(endPoint);
        logger.debug("Defaults initialised " + requestURLBuilder.build());
        return requestURLBuilder;
    }

    public RequestURLBuilder withBaseURL(String baseURL) {
        this.BASE_URL = baseURL;
        result.append(baseURL);
        return this;
    }

    public RequestURLBuilder withAPIVersion(String apiVersion) {
        this.apiVersion = apiVersion;
        result.append(apiVersion);
        return this;
    }

    public RequestURLBuilder withEndpoint(String endPoint) {
        this.endPoint = endPoint;
        result.append(endPoint);
        return this;
    }

    public RequestURLBuilder withAction(String action) {
        this.action = action;
        result.append(action);
        return this;
    }

    public RequestURLBuilder withParams(String params) {
        this.params = params;
        result.append(params);
        return this;
    }

    public String build() {
        return result.toString();
    }
}

package net.dean.jraw.http;

/**
 * Thrown when an HTTP response is not successful
 *
 * @see RestResponse#isSuccessful()
 */
public class NetworkException extends RuntimeException {
    private final RestResponse response;

    /**
     * Instantiates a NetworkException
     *
     * @param response The cause of this exception
     */
    public NetworkException(RestResponse response) {
        super(getExceptionMessage(response));
        this.response = response;
    }

    private static String getExceptionMessage(RestResponse response) {
        if (response == null)
            throw new NullPointerException("response cannot be null");

        return String.format("Request returned non-successful status code: %s %s",
                response.getStatusCode(),
                response.getStatusMessage());
    }

    public RestResponse getResponse() {
        return response;
    }
}

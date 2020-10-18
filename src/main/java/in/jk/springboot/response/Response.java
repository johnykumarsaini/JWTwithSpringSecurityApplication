package in.jk.springboot.response;

public class Response {

	private String responseCode;
	private String responseMessage;
	private Object responseData;
	private String errorMessage;

	public void setResponse(String responseCode, String responseMessage, Object responseData, String errorMessage) {

		this.setResponseCode(responseCode);
		this.setResponseMessage(responseMessage);
		this.setResponseData(responseData);
		this.setErrorMessage(errorMessage);

	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Object getResponseData() {
		return responseData;
	}

	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}

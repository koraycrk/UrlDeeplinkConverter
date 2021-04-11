package com.Url.Deeplink.Converter.Dto;

import com.Url.Deeplink.Converter.Enums.ErrorCodes;

public class ResponseDTO {
	
	public class ResponseHeader{
		private boolean Success;
		private String Message;
		private int code;
		
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public ResponseHeader() {
			super();
			// TODO Auto-generated constructor stub
		}
		public ResponseHeader(boolean success, String message) {
			super();
			Success = success;
			Message = message;
			code = ErrorCodes.Success.getValue();
		}
		public boolean isSuccess() {
			return Success;
		}
		public void setSuccess(boolean success) {
			Success = success;
		}
		public String getMessage() {
			return Message;
		}
		public void setMessage(String message) {
			Message = message;
		}
		
		
	}
	
	private String result;
	private ResponseHeader header;
	
	
	
	public ResponseDTO() {
		super();
		this.header = new ResponseHeader(true, "");
		// TODO Auto-generated constructor stub
	}

	public ResponseHeader getHeader() {
		return header;
	}

	public void setHeader(ResponseHeader header) {
		this.header = header;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	

}

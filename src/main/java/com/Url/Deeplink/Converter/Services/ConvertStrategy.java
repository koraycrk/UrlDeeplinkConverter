package com.Url.Deeplink.Converter.Services;

import com.Url.Deeplink.Converter.Dto.ResponseDTO;

public interface  ConvertStrategy {
	
	public ResponseDTO Convert(String url);

}

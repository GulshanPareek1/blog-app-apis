package com.gulshan.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gulshan.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// jese ham controller ke ander method banate the vaise hi yaha pe bhi method banayege 
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse>  resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
		// yaha hame ResourceNotFoundException ka object mil jayega jis se ham message nikal lenge jo ham vaha se throw kr rahe h 
		
		String messege = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(messege , false);
		
		return new ResponseEntity<ApiResponse>(apiResponse , HttpStatus.NOT_FOUND);
	}
	
	// it's the same method like we made in controller 
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex)
	{
		// here we'll return field with error so making Map here so that can return in key value pair for both field and error messege
		// ho sakta ki 4ro field invalid ho to hame field and messege dono nikalne honge and map banake usme rakhna h
		Map<String, String> resp = new HashMap<>();
		
		// ab hame is exception me se errors ko nikal ke ek ek ko map me rakhna h 
		// .getAllErrors() se hame ek list mil jayegi jispe ham traverse karenege 
		// and jo error ka object milega 
		// and ham is error se lambda expression ki help se ek ek field nikal sakte h and msg 
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName =((FieldError)error).getField();
			String messege	= error.getDefaultMessage();
			resp.put(fieldName, messege);
		});
		
		return new ResponseEntity<Map<String,String>>(resp , HttpStatus.BAD_REQUEST);
	}
}

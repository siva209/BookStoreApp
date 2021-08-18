package com.bridgelabz.bookstore.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response {
	
	String message;
	int status;
	Object data;
	String statusMsg;
		public Response(String message,Object user,int status,String statusMsg) 
		{
		this.message=message;
		this.status=status;
		this.data=user;
		this.statusMsg=statusMsg;
		}
		public Response(String string, Long id) {
			// TODO Auto-generated constructor stub
		}
		public Response(int i, String string, Object object) {
			// TODO Auto-generated constructor stub
		}
}



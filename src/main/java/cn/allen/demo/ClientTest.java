package cn.allen.demo;

import cn.allen.demo.client.Client;

public class ClientTest {

	public static void main(String[] args) throws Exception {
		new ClientTest().send();
	}

	public void send()throws Exception{
		new Client("127.0.0.1", 8000).sendMessage("AAA", this);
		
	}
	
	public void callback(String msg){
		System.out.println("&&client&&"+msg);
	}
}

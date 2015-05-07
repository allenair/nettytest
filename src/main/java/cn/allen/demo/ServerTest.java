package cn.allen.demo;

import cn.allen.demo.server.Server;

public class ServerTest {

	public static void main(String[] args) throws Exception {
		new Server().run(8000);
	}

}

package cn.allen.demo.client;

import cn.allen.demo.ClientTest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
	private final static int TIMEOUT=5000;
	private String host = "127.0.0.1";
	private int port = 8000;
	
	public Client(String host, int port){
		this.host=host;
		this.port=port;
	}
	
	public void sendMessage(String msgStr, final ClientTest testbean) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap boot = new Bootstrap();
			boot.group(group).channel(NioSocketChannel.class)
					.option(ChannelOption.TCP_NODELAY, true)
					.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
					.handler(new ClientInitializer());

			Channel ch = boot.connect(host, port).sync().channel();
            ClientHandler handler = (ClientHandler) ch.pipeline().get("client_handler");
            handler.sendMsg(msgStr, testbean);
            
//            ch.close();
            ch.closeFuture().sync();
            
		} catch(Exception e){
			System.out.println("@@@"+e.getMessage());
			throw e;
		} finally {
			group.shutdownGracefully();
		}
		
	}
}
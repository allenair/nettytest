package cn.allen.demo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {
	public void run(int port) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			System.out.println("======server start========");
			ServerBootstrap sboot = new ServerBootstrap();
			sboot.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ServerInitializer());
			
			sboot.bind(port).sync().channel().closeFuture().sync();
		} catch(Exception e){
			System.out.println("Server for client is ERROR!!"+e.getMessage());
			
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}

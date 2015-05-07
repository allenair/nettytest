package cn.allen.demo.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import cn.allen.demo.ClientTest;



public class ClientHandler extends SimpleChannelInboundHandler<String> {
	private volatile Channel channel;
	final BlockingQueue<String> answer = new LinkedBlockingQueue<String>();

	public void sendMsg(String msg, final ClientTest testbean) {
		System.out.println("====client START====");
		ChannelFuture f = this.channel.writeAndFlush(msg);
		
		f.addListener(new ChannelFutureListener() {
			
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if(future.isSuccess()){
					testbean.callback("OK");
					
				}
			}
		});
		
//		boolean interrupted = false;
//		for (;;) {
//			try {
//				String resBean = answer.take();
//				if (interrupted) {
//					Thread.currentThread().interrupt();
//				}
//				return resBean;
//			} catch (InterruptedException e) {
//				interrupted = true;
//			}
//		}
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String bean)
			throws Exception {
		System.out.println("Client>>>"+bean);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {

		System.out.println("some error happened!!!");
		ctx.close();
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		channel = ctx.channel();
	}

}

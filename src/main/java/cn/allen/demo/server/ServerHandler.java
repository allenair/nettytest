package cn.allen.demo.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<String> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {

		System.out.println("@@@get@@@" + msg);
		String outputMsg = "===return===" + msg;
		
		Thread.sleep(20000);
		
		ctx.write(outputMsg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(
				ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {

		System.out.println("SERVER  some error happened!!!"
				+ cause.getMessage());
		ctx.close();
	}

}

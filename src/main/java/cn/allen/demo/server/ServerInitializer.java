package cn.allen.demo.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;


public class ServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		
		pipeline.addLast("frame_decode", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
		
		pipeline.addLast("string_decode", new StringDecoder(CharsetUtil.UTF_8));
		pipeline.addLast("string_encode", new StringEncoder(CharsetUtil.UTF_8));
		
		
		pipeline.addLast("server_handler", new ServerHandler());
	}

}

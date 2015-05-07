package cn.allen.demo.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class ClientInitializer extends ChannelInitializer<SocketChannel> {
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();

		pipeline.addLast("frame_encode", new LengthFieldPrepender(4, false));
		
		pipeline.addLast("string_decode", new StringDecoder(CharsetUtil.UTF_8));
		pipeline.addLast("string_encode", new StringEncoder(CharsetUtil.UTF_8));
		
		pipeline.addLast("client_handler", new ClientHandler());
	}

}

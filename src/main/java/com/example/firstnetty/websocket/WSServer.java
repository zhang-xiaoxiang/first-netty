package com.example.firstnetty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
/**
 * WSServer:web通信服务器(在main方法主线程启动)
 * @author zhangxiaoxiang
 * @date 2019/10/3
 */
public class WSServer {

	public static void main(String[] args) throws Exception {
		
		EventLoopGroup mainGroup = new NioEventLoopGroup();
		EventLoopGroup subGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap server = new ServerBootstrap();
			server.group(mainGroup, subGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new WSServerInitialzer());
			
			ChannelFuture future = server.bind(8080).sync();
			
			future.channel().closeFuture().sync();
		} finally {
			mainGroup.shutdownGracefully();
			subGroup.shutdownGracefully();
		}
	}
	
}

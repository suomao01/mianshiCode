package netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public static void main(String[] args) {
        //创建两个线程组bossGroup和workerGroup,含有的子线程NioEventLoop的个数默认为cup核数的两倍
        //bossGroup只是处理连接请求，真正的和客户端物业处理，会交给workerGroup完成
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup(8);

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workGroup)
                //NioServerSocketChannel作为服务器的通道实现
                .channel(NioServerSocketChannel.class)
                //初始化服务器连接队列大小，服务端处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端连接。
                //多个客户端同事来的时候，服务端将不能处理的客户端连接请求放在队列中等待处理
                .option(ChannelOption.SO_BACKLOG,1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {//创建通道初始化对象，设置初始化参数在
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //对workerGroup的socketChannel设置处理器
//                        ch.pipeline().addLast(new NettyServerHannel());
                    }
                });

    }
}

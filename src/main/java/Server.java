import com.github.face.HelloService;
import com.github.face.impl.HelloServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TSimpleJSONProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * thrift服务
 * created by ShuaishuaiXiao on 2018/6/25 16:50
 **/

@Slf4j
public class Server {
    public static void main(String[] args) throws TTransportException {
        int port= 9000 ;
        // *) 传输层(Transport), 设置监听端口为9000
        TServerSocket serverTransport = new TServerSocket(port);

        // *) 协议层
//        TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory(true, true);
        TJSONProtocol.Factory protocolFactory = new TJSONProtocol.Factory();
        // *) 处理层(Processor)
        HelloServiceImpl handler = new HelloServiceImpl();
        HelloService.Processor<HelloServiceImpl> processor = new HelloService.Processor<HelloServiceImpl>(handler);

        // *) 服务层(Server)
        TServer server = new TThreadPoolServer(
                new TThreadPoolServer.Args(serverTransport)
                        .protocolFactory(protocolFactory)
                        .processor(processor));

        // *) 启动监听服务
        server.serve();
        log.info("服务已经启动，端口为："+port);

    }
}

import com.github.face.HelloService;
import com.github.face.impl.HelloServiceImpl;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;

/**
 * created by ShuaishuaiXiao on 2018/6/26 10:46
 **/
public class NioServer {
    public static void main(String[] args) throws IOException, TTransportException {

        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(9000);

        TBinaryProtocol.Factory protocol = new TBinaryProtocol.Factory(true,true);

        HelloServiceImpl handler = new HelloServiceImpl();
        HelloService.Processor<HelloServiceImpl> processor = new HelloService.Processor<HelloServiceImpl>(handler);

//        TServer server = new TNonblockingServer(
//                new TNonblockingServer.Args(serverSocket).protocolFactory(protocol).processor(processor));
//        TServer server = new THsHaServer(
//                new THsHaServer.Args(serverSocket).protocolFactory(protocol).processor(processor));
        TServer server = new TThreadedSelectorServer(
                new TThreadedSelectorServer.Args(serverSocket).protocolFactory(protocol).processor(processor));
        server.serve();
    }
}

import com.github.face.HelloService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TSimpleJSONProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * created by ShuaishuaiXiao on 2018/6/25 17:01
 **/
public class Client {
    public static void main(String[] args) throws TException {
        // *) 传输层
        TTransport transport = new TSocket("localhost", 9000);
        transport.open();
        // *) 协议层, 与服务端对应
//        TProtocol protocol = new TBinaryProtocol(transport);
        TJSONProtocol protocol = new TJSONProtocol(transport);
        // *) 创建RPC客户端
        HelloService.Client client = new HelloService.Client(protocol);
        // *) 调用服务
        System.out.println(client.hello("你好,世界"));
        // *) 关闭句柄
        transport.close();
    }
}

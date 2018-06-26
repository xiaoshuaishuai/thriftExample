import com.github.face.HelloService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.*;

import java.io.IOException;

/**
 * created by ShuaishuaiXiao on 2018/6/26 11:15
 **/
public class NioClient {
    public static void main(String[] args) {
        // *) 传输层
        TTransport transport = new TSocket("localhost", 9000);
        TFramedTransport framedTransport = new TFramedTransport(transport);
        try {
            framedTransport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        // *) 协议层, 与服务端对应
//
        TProtocol protocol = new TBinaryProtocol(framedTransport);
        // *) 创建RPC客户端
        HelloService.Client client = new HelloService.Client(protocol);
        // *) 调用服务
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(client.hello("你好,世界:"+i));
            }
        } catch (TException e) {
            e.printStackTrace();
        }
        // *) 关闭句柄
        framedTransport.close();
    }
}

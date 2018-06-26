namespace java com.github.service
include "User.thrift"
service HelloService{
    string sayHello(1:string msg)
    void discard(1:i32 i)
    list<User.User> listUser()
}
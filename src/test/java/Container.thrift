include "User.thrift"

struct Container{
    1:map<string,User.User> userMap;
    2:set<i32> intSet;
    3:list<double> doubleList;
}
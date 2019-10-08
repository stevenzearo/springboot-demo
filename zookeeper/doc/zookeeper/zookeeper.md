### zookeeper notes
#### ACL Access Control List 访问控制列表
- 构成  
1. scheme      
    安全性 world < auto < digest ? ip
2. id  
3. permission  
- 用途：  
控制客户端对节点的访问
#### SessionTimeOut
是指当客户端和服务端断开连接时长为超过指定的时间，该会话仍然有效
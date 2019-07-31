
## Translate Code   基于FFMPEG的转码集群

### 一键安装

#### 框架
mvn clean install

#### FFMPEG
- ./configure
- make && make install

### 采用框架及功能
- common包含共同的bean、常量、异常处理类
- api-server网关
- spring cloud config
- eureka服务治理
- schedualer调度系统
- translate转码服务
- 转码能力层采用ffmpeg

### 特性

- 转码速度取决于cpu数量及配置
- 上传文件速率受限于带宽
- 转码分辨率可自定义
- 转码和上传分别进行流量控制，以防止系统瘫痪。
- 当流量受限或者网络超时导致转码故障，schedualer会定期检测未完成任务补偿任务。


### Q&A

### 注意事项:

### 作者和贡献者信息：

- spirit(57810140@qq.com)


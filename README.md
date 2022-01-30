# TwitterSpider
基于Twitter开发者模式使用Twitter v2开发接口进行爬取用户推文数据的项目.该项目仅用于学习使用。

## 1.环境配置

idea2021,maven3.6+

## 2.运行步骤

### 2.1通过Git命令克隆本项目到本地



```bash
git clone https://github.com/LJF2402901363/TwitterSpider.git
```

### 2.2使用idea导入下载好的项目，并等待pom.xml的依赖文件下载完毕

### 2.3创建一个kafka服务器

本项目需要将爬取到的推文以json字符串的格式保存到kafka中，因此再开始项目之前需要您自己准备好一个kafka服务器，可以在本地安装，也可以在你的远程服务器上安装kafka服务端，具体由您自己决定（**建议使用远程Linux服务器中使用Docker一键式快速安装一个kafka服务端，可以自行百度**）。安装好kafka服务器端后记录下它的ip地址和端口，然后写入到项目配置文件中去。

### 2.4修改项目的配置

①修改resource目录下的**twitter-client-config.properties**文件，把自己在Twitter开发者官网上申请的秘钥和token相应填写进去。

②修改resource目录下的**kafka-consumer.properties**文件，配置自己kafka消费者的服务器的地址

③修改resource目录下的**kafka-producer.properties**文件，配置自己kafka生产者的服务器的地址

④修改resource目录下的conditions.json文件，配置需要监听指定推特用户的名字（userName），推文数量（nbTweets），发布的推文起始时间（startTime）和结束时间（endTime），推文的起始ID(sinceId)和结束ID(utilId)

⑤mail.properties暂时未使用到

### 2.4启动项目

启动app包下的App.java的main方法即可启动项目。启动项目后，TwitterSpiderTask会将爬取到的用户推文发送到kafka中，这些推文会以json格式字符串保存在kafka中，然后用户可以使用kafka客户端连接kafk服务器后进行消费处理这些推文消息。比如使用Python也可以连接kafka服务器然后进行消费推文消息。本项目的TwitterConsumerTask是使用Java的kafka客户端连接kafka服务器进行消费推文数据。

### 2.5二次开发项目

在本项目中已经将爬取到的推文同步以json格式字符串保存到kafaka中，您可以使用不同语言比如c++，Python，go等这些客户端连接到你在项目中所指定的kafka服务器，然后进行消费数据得到推文，然后定制自己的业务逻辑。如果想获取用户推文发布时及时通知到，可以在消费者端中使用加入邮件模块来通知给需要被通知的人。**此功能暂未实现。**

## 3.注意事项

Twitter的开发者账号对请求数据有次数限制，具体请看其官网。


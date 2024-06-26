# 雪花算法分布式ID生成器

该项目的目的是提供一个轻量级、高并发、高可用的生成唯一ID的服务，生成的ID是一个64位的长整型，全局唯一，保持递增，相对有序。基于twitter的雪花算法来生成ID,用于取代UUID那种无序、128位的字符串形式的ID，提供一种更加高效、人性化的全局唯一ID的生成方式，目前单机CPU4核、内存8G压测的并发数可以达到**
250万/每秒**，即每秒最多可以生成250万个唯一的ID，当然如果部署集群的话，这个数据可以更高。
<br><br>
具体的教程可以参考：[基于twitter雪花算法的分布式ID —— Java篇](./SnowFlake-Java.md)

<br><br>

## 特点

* 基于twitter的雪花算法生成ID;
* SpringBoot服务对外提供HTTP接口；
* 轻量级、高并发、易伸缩；
* 部署简单，支持分布式部署；
  <br>

## 部署

该项目是一个SpringBoot应用，注册到了nacos里面。如果不使用nacos。将启动类上面的@EnableDiscoveryClient注解注释掉即可。按照SpringBoot的部署方式正常部署即可。

```
snow:
  flake:
    datacenterId: 1
    machineId: 1
```

如果不指定这两个参数，那么会使用默认的值1。如果只考虑部署单机服务器，那么可以不考虑这两个参数，

**如果需要分布式集群来生成ID时，需要指定数据中心标识ID和机器进程标识ID，并且每一个服务器的数据中心标识ID和机器进程标识ID作为联合键全局唯一，这样才能保证集群生成的ID都是唯一的。**

<br>

## 使用方式

**获取一个id :**

请求URL： http://172.31.128.22:8081/id

请求方式：GET

返回参数：

```
{
    "success": true,
    "time": "2021-03-26 12:55:40",
    "data": 13240507703296,
    "code": "200",
    "msg": "成功"
}
```

**批量获取多个id**

请求URL： http://172.31.128.22:8081/id/{count}

请求方式：GET

请求示例：http://172.31.128.22:8081/id/5

返回参数：

```
{
    "success": true,
    "time": "2021-03-26 12:58:12",
    "data": [
        13880961146880,
        13880961146881,
        13880961146882,
        13880961146883,
        13880961146884
    ],
    "code": "200",
	"msg": "成功"
}
```

## 总结：

分布式id服务作为一个基础服务。

建议通过nacos等注册中心进行服务的注册与发现。后续各个服务需要获取分布式id的话只需要基于分布式id服务的服务名字通过feign调用获取id即可。
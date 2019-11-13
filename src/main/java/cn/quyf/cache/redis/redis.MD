

#### 过期策略
文章参考 [面试官：Redis 内存数据满了，会宕机吗？](https://mp.weixin.qq.com/s?__biz=MzIwMzY1OTU1NQ==&mid=2247487680&idx=1&sn=59ad8e65212dd5e09f79c1cd4c0129f8&chksm=96cd508ca1bad99adfa7335e87f3eb76ebffec7aa86772acd503d13f4e383dcb77d1f85ca330&scene=0&xtrack=1&key=e7af850574ba21a87ebd209a4e52db58cc7c3696860e15332229da07f2d621df94cf9ed6f50472ff28c56211b13e68345de47559cbd7f9ba19a02d758cc1dccf0ed97b3642af78799bd9ef0f9b11b543&ascene=1&uin=NDAyNDg1OTc1&devicetype=Windows+7&version=62060833&lang=zh_CN&pass_ticket=QoHsatDhO5ubTK0IzeBhc1rX8ocUF1rqDT8oyzOFnFRDtVfR%2Bh72Y3Dfabc%2Fc5YY)

既然可以设置Redis最大占用内存大小，那么配置的内存就有用完的时候。那在内存用完的时候，还继续往Redis里面添加数据不就没内存可用了吗？

实际上Redis定义了几种策略用来处理这种情况：

* noeviction(默认策略)：对于写请求不再提供服务，直接返回错误（DEL请求和部分特殊请求除外）

* allkeys-lru：从所有key中使用LRU算法进行淘汰

* volatile-lru：从设置了过期时间的key中使用LRU算法进行淘汰

* allkeys-random：从所有key中随机淘汰数据

* volatile-random：从设置了过期时间的key中随机淘汰

* volatile-ttl：在设置了过期时间的key中，根据key的过期时间进行淘汰，越早过期的越优先被淘汰

如何查看当前淘汰策略
> config get maxmemory-policy

如何修改淘汰策略
可通过修改redis.conf配置文件
> maxmemory-policy allkeys-lru

也可通过命令修改
> config set maxmemory-policy allkeys-lru



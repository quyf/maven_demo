###  查看java对象布局和大小的工具 ，jol

导入依赖
   ```xml
            <dependency>
                <groupId>org.openjdk.jol</groupId>
                <artifactId>jol-core</artifactId>
                <version>0.9</version>
            </dependency>
```

官网：http://openjdk.java.net/projects/code-tools/jol/

### 查看对象内部信息
ClassLayout.parseInstance(obj).toPrintable()

### 查看对象外部信息
   包括引用的对象，
   GraphLayout.parseInstance(obj).toPrintable()
   
   
### 查看对象占用的总空间大小
GraphLayout.parseInstance(obj).totalSize()


          
          
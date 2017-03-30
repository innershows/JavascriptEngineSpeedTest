# JavaScript引擎Android端性能对比

#### 目标：
  > 选择合适的JS引擎，提升执行效率

#### JS引擎：
 - JavaScriptCore Android版
    - 优点：Apple开源，iOS默认JS引擎，safari引擎，快。已经在AppEngine iOS版验证过
    - 缺点：C语言实现，缺少Java接口。有开源维护项目[AndroidJSCore](https://github.com/ericwlange/AndroidJSCore),有性能问题未解决。如果需要使用，建议在C层做jni封装。
 - V8
    - 优点：Google开源维护引擎，Chrome使用。
    - C++ 实现。有Java实现的项目[J2V8](https://github.com/eclipsesource/J2V8)。为Android项目设计的。
 - SpiderMonkey
    - Netscape开发，由Mozilla维护。
 - Rhino
    - 优点：Mozilla开发的开源JS引擎，使用Java开发，对Java支持最好。
    - 缺点：在Dalvik少了一些特性。
 - Nashorn
    - 缺点：JDK对Mozilla的替代，不支持Dalvik。


*本Demo只对 JavaScriptCore、V8 和 Rhino 做性能测试。*


#### 性能比较参数
- APK体积增量
- 内存占用
- Java调用JavaScript代码耗时
- JavaScript调用Java代码耗时


##### apk体积增量

 | JS引擎 | 体积增量
 | ----   |:---:|
 | JSCore | 23
 | V8     |  23
 | Rhino | 232




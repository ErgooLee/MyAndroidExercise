###  [后台知识点](https://developer.android.com/guide/background?hl=en)
android app的主线程主要用来渲染ui和处理用户交互。如果在该线程上处理太多工作，
则会导致糟糕的用户体验。任何长时间的计算和操作如解码位图，访问磁盘或者执行网络请求，
都应单独在后台线程上完成。`通常，超过几毫秒的操作都应该委托给后台线程。`

后台任务消耗诸多资源：电池、网络、ram。

难点：android系统对后台任务的限制越来越重，如何在诸多限制下实现app的后台需求。

### android6.0 引入Doze mode and app standby。
Doze mode: 当设备长时间未使用时（未插电源+静止不动+屏幕关闭）延迟后台应用的cpu活动、网络请求、jobs、syncs、standard alarms。
设备会定期从打盹状态中退出、执行syncs，jobs，alarms，允许app访问网络。

Doze 限制：
- 暂停网络请求
- 系统忽略wake locks
- 推迟闹钟 set setExact setWindow。setAndAllowWhileIdle/setExactAndAllowWhileIdle不会被推迟。
- 系统不会执行wifi扫描
- 系统不会运行Sync Adapters
- 系统不会运行JobScheduler

App Standby：推迟最近未与之交互的应用程序的网络请求活动。

电池优化白名单
白名单中的app可以在doze mode和app standby中使用网络、持有部分唤醒锁。

ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS 启动系统设置页
REQUEST_IGNORE_BATTERY_OPTIMIZATIONS 启动弹窗

### android7.0 引入Doze-on-the-Go
未插电+屏幕关闭一段时间后进入Doze Mode。

### android8.0 对诸多后台服务进行进一步限制。
后台服务限制：
- 当应用从前台进入后台，只有几分钟窗口去运行服务。
- 不允许后台应用启动后台服务，

广播限制：
- AndroidManifest.xml中申明的隐式广播不在起作用
签名许可的广播不在此限制内。

### android9.0 引入App Standby Buckets
根据app的最近使用频率，动态地设定app访问资源的优先级，总共有5个优先级，对用5个Standby buckets。
- Active：app被经常使用或者最近使用。
- Working set：app被定期使用
- Frequent：经常使用但并非每天使用
- Rare：不经常使用
- Never：被安装后从未使用过

[限制链接](https://developer.android.com/topic/performance/power/power-details.html?hl=en)


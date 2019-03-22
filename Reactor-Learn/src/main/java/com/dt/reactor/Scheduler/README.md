## 调度器与线程模型
在 Reactor 中, 对于多线程并发调度的处理变得异常简单.  
在以往的多线程开发场景中, 我们通常使用 `Executors` 工具类来创建线程池, 通常由如下四种类型: 
- `newCachedThreadPool` 创建一个弹性大小缓存线程池, 如果线程池长度超过处理需要, 
可灵活回收空闲线程, 若无可回收, 则新建线程;
- `newFixedThreadPool` 创建一个大小固定的线程池, 可控制线程最大并发数, 超出的线程会在队列中等待;
- `newScheduledThreadPool` 创建一个大小固定的线程池, 支持定时及周期性的任务执行;
- `newSingleThreadExecutor` 创建一个单线程化的线程池, 它只会用唯一的工作线程来执行任务, 保证所有任务
按照指定顺序(FIFO,LIFO,优先级)执行.  

此外, `newWorkStealingPool` 还可以创建支持 work-stealing 的线程池.

使用 Reactor 的调度器 (Scheduler) 创建:
- 当前线程 `Schedulers.immediate()`
- 可重用的单线程 `Schedulers.single()`. 注意, 这个方法对所有调用者都提供同一个线程来使用, 直到
该调度器被废弃. 如果你想使用独占的县城, `Schedulers.newSingle()`
- 弹性线程池 `Schedulers.elastic()`. 它根据需要创建一个线程池, 重用空闲线程. 线程池如果空闲时间过长
(默认为 60s) 就会废弃. 对于一个 I/O 阻塞的场景比较使用. `Schedulers.elastic()` 能够方便地给一个阻塞
的任务分配它自己的线程, 从而不会妨碍其他任务和资源.
- 固定大小线程池 `Schedulers.parallel()` . 所创建线程池的大小与 CPU 个数等同. 
- 自定义线程池 `Schedulers.fromExecutorService(ExecutorService)`, 基于自定义的 ExecutorService 
创建 Scheduler  


Schedulers类已经预先创建了几种常用的线程池：使用`single()`、`elastic()`和`parallel()`方法可以分别使用内置的单线程、弹性线程池和固定大小线程池。
如果想创建新的线程池，可以使用`newSingle()`、`newElastic()`和`newParallel()`方法。
package Thread;

import java.util.concurrent.*;

public class ThreadPoolUtil {
//    private static final Logger log = LogManager.getLogger(ThreadPoolUtil.class);

    private static int MIN_SIZE;
    private static int MAX_SIZE;
    private static int THREAD_EXPIRED;
    private static int QUEUE_SIZE_ADD_THREAD;
    static private ThreadPoolUtil inst = null;
    static private ThreadPoolExecutor executor = null;
//    static private ThreadPoolExecutor executor2 = null;

    private ThreadPoolUtil(int nThreadMinSize, int nThreadMaxSize, int nQueueSize) {
        executor = new TestThreadPoolExecutor(nThreadMinSize, nThreadMaxSize, THREAD_EXPIRED, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(nQueueSize));
//        executor2 = new TestThreadPoolExecutor(nThreadMinSize, nThreadMaxSize, THREAD_EXPIRED, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<>(nQueueSize));
    }


    public ThreadPoolExecutor getExecutor() {
        return executor;
    }

//    public ThreadPoolExecutor getExecutor2() {
//        return executor2;
//    }

    /**
     * if(cpu core 8-16) then set minThread 4,MaxThreads 32!
     * Too many threads  cause CPU switching threads to consume more time
     */
    static public ThreadPoolUtil instance() {
        if (null == inst) {

//            MIN_SIZE = ConfUtil.getInstance().getInt("THREAD_POOL_SIZE_MIN", 4);
//            MAX_SIZE = ConfUtil.getInstance().getInt("THREAD_POOL_SIZE_MAX", 32);
//            THREAD_EXPIRED = ConfUtil.getInstance().getInt("THREAD_POOL_THREAD_EXPIRED", 30);
//            QUEUE_SIZE_ADD_THREAD = ConfUtil.getInstance().getInt("QUEUE_SIZE_ADD_THREAD", 5);//当队列中堆积较多任务未处理时，说明已有线程池中任务长时间未退出，可开启新的线程容纳快进快出的任务
//            int QUEUE_SIZE = ConfUtil.getInstance().getInt("THREAD_POOL_QUEUE_SIZE", 4068);

            inst = new ThreadPoolUtil(32, 32, 10000);

            //test
            TestShowThreadSize();

        }
        return inst;
    }

    private static boolean showActiveCount = true;

    //test
    private static void TestShowThreadSize() {
//        log.debug("show thread Infos.....");

        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1);

        service.execute(() -> {
            while (true) {
                try {

                    Thread.sleep(500);

                    inst.changePoolSize(false);
                    if (showActiveCount) {
                        System.out.println("Size:" + executor.getActiveCount() + "  queueSize:" + executor.getQueue().size());
                    }

                    showActiveCount = (executor.getActiveCount() != 0);


//                    log.debug("ThreadSize:{},QueueSize:{},PoolSize:{},CorePoolSize:{}", executor.getActiveCount(), executor.getQueue().size(), executor.getPoolSize(), executor.getCorePoolSize());
                } catch (Exception e) {
                    System.out.println(e);
//                    log.error(e.getMessage(), e);
                }
            }
        });
    }

    @Deprecated
    static public ThreadPoolUtil instance(int nThreadSize, int nQueueSize) {
        if (null == inst)
            inst = new ThreadPoolUtil(nThreadSize, nThreadSize, nQueueSize);
        return inst;
    }

    public void close() {
        if (executor != null)
            executor.shutdown();
    }

    public boolean close2() {
        if (executor != null && executor.getActiveCount() == 0) {
//            System.out.println("getActiveCount:" + executor.getActiveCount());
//            System.out.println("getTaskCount:" + executor.getTaskCount());
//                System.out.println("executor.shutdown()");
//            executor.shutdown();
//            executor2.shutdown();
            return true;
        }
        return false;
    }

    public boolean addTask(Runnable task) {
        try {

            changePoolSize(false);
            executor.execute(task);
//            System.out.println("add.task");
//            log.debug("add task end getThreadSize:{},getQueueSize:{},", getThreadSize(), getQueueSize());
            return true;
        } catch (Exception e) {
//            log.error("add task exception", e);
            for (Runnable r : executor.getQueue()) {
//                log.debug("Task[{},{}]", r.hashCode(), r.toString());
            }
        }
        return false;
    }
//
//    public boolean addTask2(Runnable task) {
//        try {
//
//            changePoolSize(false);
//            executor2.execute(task);
////            System.out.println("add.task");
////            log.debug("add task end getThreadSize:{},getQueueSize:{},", getThreadSize(), getQueueSize());
//            return true;
//        } catch (Exception e) {
////            log.error("add task exception", e);
//            for (Runnable r : executor.getQueue()) {
////                log.debug("Task[{},{}]", r.hashCode(), r.toString());
//            }
//        }
//        return false;
//    }

    public boolean addTask(Runnable task, boolean flag) {
        try {

            changePoolSize(flag);
            executor.execute(task);
//            log.debug("add task end getThreadSize:{},getQueueSize:{},", getThreadSize(), getQueueSize());
            return true;
        } catch (Exception e) {
//            log.error("add task exception", e);
            for (Runnable r : executor.getQueue()) {
//                log.debug("Task[{},{}]", r.hashCode(), r.toString());
            }
        }
        return false;
    }

    public <T> Future<T> addFutureTask(Callable<T> task) {
        try {
            changePoolSize(false);
            return executor.submit(task);
        } catch (Exception e) {
//            log.error("add future task exception", e);
        }
        return null;
    }

    public <T> Future<T> addFutureTask(Callable<T> task, boolean flag) {
        try {
            changePoolSize(flag);
            return executor.submit(task);
        } catch (Exception e) {
//            log.error("add future task exception", e);
        }
        return null;
    }

    public <T> T waitForFutureTaskFinish(Future<T> future) {
        try {
            return future.get();
        } catch (Exception e) {
//            log.error("wait for future task exception", e);
        }
        return null;
    }

    /**
     * 获取当前线程数
     *
     * @return
     */
    public int getThreadSize() {
        return executor.getActiveCount();
    }

    /**
     * 队列中等待执行的任务数目
     */
    public int getQueueSize() {
        return executor.getQueue().size();
    }

    /**
     * 动态调整线程池corePoolSize
     *
     * @param flag
     */
    private void changePoolSize(boolean flag) {

        if (flag) {
            addCorePoolSize();
            return;
        }

//        if (executor.getQueue().size() < QUEUE_SIZE_ADD_THREAD) {
//
//            if (executor.getCorePoolSize() > MIN_SIZE) {
//                //Do setCorePoolSize() as little as possible
//                executor.setCorePoolSize(MIN_SIZE);
//            }
//
//        } else {
//
//            addCorePoolSize();
//        }
    }

    private void addCorePoolSize() {
        if (MAX_SIZE == executor.getCorePoolSize()) {
            return;
        }

        int i = executor.getCorePoolSize();
        i++;

        if (i <= MAX_SIZE) {
            executor.setCorePoolSize(i);
        }
    }

}
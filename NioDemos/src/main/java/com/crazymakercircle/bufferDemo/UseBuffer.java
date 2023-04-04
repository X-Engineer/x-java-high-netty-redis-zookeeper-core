package com.crazymakercircle.bufferDemo;

import com.crazymakercircle.util.Logger;

import java.nio.IntBuffer;

/**
 * 详细介绍 Buffer 类的几个常用方法，包含 Buffer 实例的创建、写入、读取、重复读、标记和重置
 * allocate()
 * put()
 * flip()
 * get()
 * rewind()
 * clear()
 */
public class UseBuffer {
    static IntBuffer intBuffer = null;

    public static void allocatTest() {
        // 分配容量为 20 的空间
        intBuffer = IntBuffer.allocate(20);
        Logger.debug("------------after allocate------------------");
        Logger.debug("position=" + intBuffer.position());
        Logger.debug("limit=" + intBuffer.limit());
        Logger.debug("capacity=" + intBuffer.capacity());
    }

    public static void putTest() {
        // 写入 0 ~ 4 ，这 5 个数值
        for (int i = 0; i < 5; i++) {
            intBuffer.put(i);
        }
        Logger.debug("------------after putTest------------------");
        Logger.debug("position=" + intBuffer.position());
        Logger.debug("limit=" + intBuffer.limit());
        Logger.debug("capacity=" + intBuffer.capacity());

    }

    public static void flipTest() {
        // 读写模式切换
        intBuffer.flip();
        Logger.debug("------------after flipTest ------------------");

        Logger.debug("position=" + intBuffer.position());
        Logger.debug("limit=" + intBuffer.limit());
        Logger.debug("capacity=" + intBuffer.capacity());
    }

    public static void getTest() {
        // 读取 2 个元素
        for (int i = 0; i < 2; i++) {
            int j = intBuffer.get();
            Logger.debug("j = " + j);
        }

        Logger.debug("------------after get 2 int ------------------");
        Logger.debug("position=" + intBuffer.position());
        Logger.debug("limit=" + intBuffer.limit());
        Logger.debug("capacity=" + intBuffer.capacity());
        // 再次读取 3 个元素
        for (int i = 0; i < 3; i++) {
            int j = intBuffer.get();
            Logger.debug("j = " + j);
        }

        Logger.debug("------------after get 3 int ------------------");
        Logger.debug("position=" + intBuffer.position());
        Logger.debug("limit=" + intBuffer.limit());
        Logger.debug("capacity=" + intBuffer.capacity());
    }

    public static void rewindTest() {
        // 倒带: position 设置为 0， mark 被丢弃
        intBuffer.rewind();
        Logger.debug("------------after rewind ------------------");
        Logger.debug("position=" + intBuffer.position());
        Logger.debug("limit=" + intBuffer.limit());
        Logger.debug("capacity=" + intBuffer.capacity());
    }


    /**
     * rewind之后，重复读
     * 并且演示 mark 标记方法
     */
    public static void reRead() {
        // 设置 mark
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                intBuffer.mark();
            }
            int j = intBuffer.get();
            Logger.debug("j = " + j);

        }
        Logger.debug("------------after reRead------------------");
        Logger.debug("position=" + intBuffer.position());
        Logger.debug("limit=" + intBuffer.limit());
        Logger.debug("capacity=" + intBuffer.capacity());

    }

    public static void afterReset() {
        Logger.debug("------------after reset------------------");
        intBuffer.reset();
        Logger.debug("position=" + intBuffer.position());
        Logger.debug("limit=" + intBuffer.limit());
        Logger.debug("capacity=" + intBuffer.capacity());
        for (int i =2; i < 5; i++) {
            int j = intBuffer.get();
            Logger.debug("j = " + j);

        }

    }

    public static void clearDemo() {
        // 清空 buffer，此方法不会实际清除 buffer 中的内容
        // 只是 position 设置为零，limit 设置为 capacity，mark 被丢弃。
        Logger.debug("------------after clear------------------");
        /**
         * 在缓冲区处于读模式时，调用clear()，缓冲区会被切换成写模式。调用clear()之后，
         * 我们可以看到清空了position（写入的起始位置）的值，其值被设置为0，并且limit值（写入的上限）为最大容量。
         */
        intBuffer.clear();
        Logger.debug("position=" + intBuffer.position());
        Logger.debug("limit=" + intBuffer.limit());
        Logger.debug("capacity=" + intBuffer.capacity());
    }

    public static void main(String[] args) {
        Logger.debug("分配内存");

        allocatTest();

        Logger.debug("写入");
        putTest();

        Logger.debug("翻转");

        flipTest();

        Logger.debug("读取");
        getTest();

        Logger.debug("重复读");
        rewindTest();
        reRead();

        Logger.debug("make&reset写读");
        afterReset();

        Logger.debug("清空");

        clearDemo();

        Logger.debug("清空之后");
        for (int i = 0; i < 20; i++) {
            Logger.debug(intBuffer.get());
        }
        /**
         * 总体来说，使用Java NIO Buffer类的基本步骤如下：
         * （1）使用创建子类实例对象的allocate()方法创建一个Buffer类的实例对象。
         * （2）调用put()方法将数据写入缓冲区中。
         * （3）写入完成后，在开始读取数据前调用Buffer.flip()方法，将缓冲区转换为读模式。
         * （4）调用get()方法，可以从缓冲区中读取数据。
         * （5）读取完成后，调用Buffer.clear()方法或Buffer.compact()方法，将缓冲区转换为写模式，可以继续写入。
         */
    }
}



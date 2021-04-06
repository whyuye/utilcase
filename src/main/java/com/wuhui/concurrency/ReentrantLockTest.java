package com.wuhui.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程循环顺序打印ABC100次
 */
public class ReentrantLockTest {

    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();
        final Condition conditionA = reentrantLock.newCondition();
        final Condition conditionB = reentrantLock.newCondition();
        final Condition conditionC = reentrantLock.newCondition();

        IntegerHolder integerHolder = new IntegerHolder();
        integerHolder.setValue(1); // 让A满足条件先运行

        Thread threadA = new Thread(new MyThread("A", reentrantLock, conditionA, conditionB, integerHolder));
        Thread threadB = new Thread(new MyThread("B", reentrantLock, conditionB, conditionC, integerHolder));
        Thread threadC = new Thread(new MyThread("C", reentrantLock, conditionC, conditionA, integerHolder));
        threadA.start();
        threadB.start();
        threadC.start();
    }

    static class IntegerHolder {

        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    static class MyThread implements Runnable {

        private final String word;

        private final Lock lock;

        private final Condition curCondition;

        private final Condition nextCondition;

        private final IntegerHolder integerHolder;

        public MyThread(String word, Lock lock, Condition curCondition, Condition nextCondition, IntegerHolder integerHolder) {
            this.word = word;
            this.lock = lock;
            this.curCondition = curCondition;
            this.nextCondition = nextCondition;
            this.integerHolder = integerHolder;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                lock.lock(); // 由于是在加锁里面进行的处理，已经保证了integerHolder持有变量在不同线程间的可见性了
                try {
                    if (("A".equals(word) && integerHolder.getValue() != 1) || ("B".equals(word) && integerHolder.getValue() != 2) || ("C".equals(word) && integerHolder.getValue() != 3) ) {
                        // 不满足条件则挂起，会释放锁，同时当前线程进入到waitSet中
                        curCondition.await();
                    }
                    System.out.print(word);
                    if ("C".equals(word)) {
                        System.out.println("——————" + (i+1));
                    }
                    switch (word) {
                        case "A":
                            integerHolder.setValue(2);
                            break;
                        case "B":
                            integerHolder.setValue(3);
                            break;
                        case "C":
                            integerHolder.setValue(1);
                            break;
                        default:
                    }
                    nextCondition.signal();
                } catch (Exception e) {
                    // ignore
                }
                finally {
                    lock.unlock();
                }
            }
        }
    }
}

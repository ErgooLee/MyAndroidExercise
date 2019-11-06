package com.example.test.kotlin

import kotlinx.coroutines.*

class Coroutine {

    suspend fun requestToken(): String {
        delay(1000)
        println("requestToken at ${Thread.currentThread().name}")
        return "token"
    }// 挂起函数

    suspend fun createPost(token: String, item: String): String {
        delay(2000)
        println("createPost $token $item at ${Thread.currentThread().name}")
        return "post"
    }  // 挂起函数

    fun processPost(post: String) {
        println("process post $post at ${Thread.currentThread().name}")
    }

    fun postItem() {
        GlobalScope.launch(Dispatchers.Unconfined) {
            val token = async(Dispatchers.IO) { requestToken() }.await()
            val post = async(Dispatchers.IO) { createPost(token, "item") }.await()
            processPost(post)
        }
    }

    fun testRunBlocking() {
        runBlocking { // start main coroutine
            launch { // launch new coroutine in background and continue
                delay(30000L)
                println("World at ${Thread.currentThread().name}")
            }
            println("Hello,${Thread.currentThread().name}") // main coroutine continues here immediately
            delay(20000L)      // delaying for 2 seconds to keep JVM alive
        }
    }

    fun testRunBlocking2() {
        runBlocking<Unit> {
            launch { // 默认继承 parent coroutine 的 CoroutineDispatcher，指定运行在 main 线程
                println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
                delay(100)
                println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
            }
            launch(Dispatchers.Unconfined) {
                println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
                delay(100)
                println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
            }
        }
    }


}
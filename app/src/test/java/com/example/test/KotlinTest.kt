package com.example.test

import kotlinx.coroutines.*
import org.junit.Test
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@ExperimentalContracts
public class KotlinTest {
    fun f() {
        val isEmptyStringList: (List<*>) -> Boolean = List<*>::isEmpty
        val dog = "cat"

        dog.let2 {
            print(it)
        }

        println(listOf("aaa", "bb", "c").sortedWith(compareBy {
            it.length
        }))
        println(listOf("aaa", "bb", "c").sortedBy {
            it.length
        })
    }

    public inline fun <T, R> T.let2(block: (T) -> R): R {
        return block(this)
    }

//    @ExperimentalContracts
//    fun createOnce(runFunction: () -> Unit) {
//        contract {
//            callsInPlace(runFunction, kotlin.contracts.InvocationKind.EXACTLY_ONCE)
//        }
//        runFunction()
//    }
//
//    @ExperimentalContracts
//    fun getKotlinVersion(): Float {
//        val kotlinVersion: Float
//        createOnce {
//            kotlinVersion = 1.3f
//        }
//
//        // The line below generates the error (and does not compile):
//        // Captured values initialization is forbidden due to possible reassignment
//        return kotlinVersion
//    }

    @Test
    fun test() {
        println("${Thread.currentThread().name}  协程初始化开始，时间: " + System.currentTimeMillis())

        GlobalScope.launch(Dispatchers.IO) {
            println("${Thread.currentThread().name} 协程初始化完成，时间: " + System.currentTimeMillis())
            for (i in 1..3) {
                println("${Thread.currentThread().name}  协程任务1打印第$i 次，时间: " + System.currentTimeMillis())
            }
            delay(500)
            for (i in 1..3) {
                println("${Thread.currentThread().name}  协程任务2打印第$i 次，时间: " + System.currentTimeMillis())
            }
        }

        println("${Thread.currentThread().name}  主线程 ${Thread.currentThread().name} sleep ，时间: " + System.currentTimeMillis())
        Thread.sleep(10000)
        println("${Thread.currentThread().name}  主线程运行，时间: " + System.currentTimeMillis())

        for (i in 1..3) {
            println("${Thread.currentThread().name}  主线程打印第$i 次，时间: " + System.currentTimeMillis())
        }
    }

    @Test
    fun postItem() {
        GlobalScope.launch {
            val token = requestToken()
            val post = createPost(token, "item")
            processPost(post)
        }
    }


}


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






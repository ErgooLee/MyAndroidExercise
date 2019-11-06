package com.example.test.proxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy


var printer: Printer? = null

fun testProxy() {
    printer = Proxy.newProxyInstance(Printer::class.java.classLoader,
        arrayOf(Printer::class.java),
        object : InvocationHandler {
            @Throws(Throwable::class)
            override fun invoke(
                proxy: Any,
                method: Method,
                args: Array<Any>?
            ): Any? {
                println("proxy")
                return method.invoke(this, args)
            }
        }) as Printer
    printer?.print()

}
package com.example.test.rxjava

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit

class RxJava {
    fun testMerge() {

        val observable1 = Observable.create(ObservableOnSubscribe<Int> {
            println("thread1 ${Thread.currentThread().name}")
//            it.onNext(1)
            it.onComplete()
        }).subscribeOn(Schedulers.io())

        val observable2 = Observable.create(ObservableOnSubscribe<Int> {
            TimeUnit.SECONDS.sleep(3)
            println("thread2 ${Thread.currentThread().name}")
            it.onNext(2)
            it.onComplete()
        }).subscribeOn(Schedulers.io())

        Observable.merge(observable1, observable2)
            //not work
            .subscribeOn(Schedulers.computation())
            .observeOn(Schedulers.computation())
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                    println("onComplete at ${Thread.currentThread().name}")

                }

                override fun onSubscribe(d: Disposable?) {

                }

                override fun onNext(t: Int?) {
                    println("subscribe $t at ${Thread.currentThread().name}")
                }

                override fun onError(e: Throwable?) {
                    println(e?.message)
                }
            })
    }


    fun testMerge2() {

        val observable1 = Observable.create(ObservableOnSubscribe<Int> {
            it.onError(RuntimeException("test"))
            println("thread1 ${Thread.currentThread().name}")
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())

        val observable2 = Observable.create(ObservableOnSubscribe<Int> {
            println("thread2 ${Thread.currentThread().name}")
            it.onNext(2)
            it.onComplete()
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())


        Observable.mergeDelayError(observable1, observable2)
            //not work
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                    println("onComplete at ${Thread.currentThread().name}")

                }

                override fun onSubscribe(d: Disposable?) {
                    println("onSubscribe at ${Thread.currentThread().name}")

                }

                override fun onNext(t: Int?) {
                    println("onNext $t at ${Thread.currentThread().name}")
                }

                override fun onError(e: Throwable?) {
                    println("onError ${e?.message} at ${Thread.currentThread().name}")

                }
            })
    }


    fun testZip() {


        val observable1 = Observable.create(ObservableOnSubscribe<Int> {
            Thread.sleep(3000)
            println("thread1 ${Thread.currentThread().name}")
            it.onNext(1)
            it.onComplete()
        }).subscribeOn(Schedulers.io())

        val observable2 = Observable.create(ObservableOnSubscribe<Int> {
            println("thread2 ${Thread.currentThread().name}")
            it.onNext(2)
            it.onComplete()
        }).subscribeOn(Schedulers.io())


        Observable.zip(observable1, observable2,
            BiFunction<Int, Int, Int> { t1, t2 ->
                println("t1:$t1, t2:$t2")
                t1 + t2
            })
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                    println("onComplete at ${Thread.currentThread().name}")

                }

                override fun onSubscribe(d: Disposable?) {
                    println("onSubscribe at ${Thread.currentThread().name}")

                }

                override fun onNext(t: Int?) {
                    println("onNext $t at ${Thread.currentThread().name}")
                }

                override fun onError(e: Throwable?) {
                    println("onError ${e?.message}")
                }
            })
    }


    fun testZip2() {

        val observable1 = Observable.create(ObservableOnSubscribe<Int> {
            println("thread1 ${Thread.currentThread().name}")
            it.onError(RuntimeException("test"))
        }).subscribeOn(Schedulers.io())

        val observable2 = Observable.create(ObservableOnSubscribe<Int> {
            println("thread2 ${Thread.currentThread().name}")
            it.onNext(2)
            it.onComplete()
        }).subscribeOn(Schedulers.io())


        Observable.zip(observable1, observable2,
            BiFunction<Int, Int, Int> { t1, t2 ->
                println("t1:$t1, t2:$t2")
                t1 + t2
            })
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                    println("onComplete at ${Thread.currentThread().name}")

                }

                override fun onSubscribe(d: Disposable?) {
                    println("onSubscribe at ${Thread.currentThread().name}")

                }

                override fun onNext(t: Int?) {
                    println("onNext $t at ${Thread.currentThread().name}")
                }

                override fun onError(e: Throwable?) {
                    println("onError ${e?.message}")
                }
            })

    }

    fun testSingleMap() {
        val single = Single.just(1);
        val stringSingle = single.map {
            it.toString()
        }
        stringSingle.subscribe(object : SingleObserver<String> {
            override fun onSuccess(t: String) {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
            }
        })
    }


    fun testInterval() {
        val observable = Observable.interval(0, 1, TimeUnit.SECONDS)
        observable.subscribe(object : Observer<Long> {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(t: Long?) {
                println(t)
            }

            override fun onError(e: Throwable?) {
            }
        })
    }


    fun testSingleDelay() {
        val single = Single.just(1)
            .delay(1, TimeUnit.SECONDS)

        single.subscribe(object : SingleObserver<Int> {
            override fun onSuccess(t: Int) {
                println(t)
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
            }
        })
    }

    fun testObserverMap() {
        val single = Observable.just(1,2,3)
        single.map {
            it.toString()
        }.subscribe(object :Observer<String>{
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(t: String?) {

            }

            override fun onError(e: Throwable?) {
            }
        })
    }

    fun testSubscribeOn() {
        Single.just(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer<Int> {
                println(it)
            })

    }



}
package com.example.test.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test.R
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestRetrofit : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_retrofit)
        Thread {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()

            val service = retrofit.create(GitHubService::class.java)

            val repos: Call<List<Repo>> = service.listRepos("octocat")
            val ret = repos.execute().body()
            println(ret?.get(0)?.name)


            val retrofit2 = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build()
            val service2 = retrofit2.create(GitHubService::class.java)

            val repos2: Call<ResponseBody> = service2.listRepos2("octocat")
            val ret2 = repos2.execute().body()!!.string()
            println(ret2)

            val retrofit3 = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build()
            val service3 = retrofit3.create(GitHubService::class.java)

            val ret3: Call<Void> = service3.listRepos3("octocat")
            ret3.enqueue(object : retrofit2.Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {

                }

                override fun onResponse(call: Call<Void>, response: retrofit2.Response<Void>) {

                }
            })

            val gitHubService = retrofit.create(GitHubService::class.java)
            gitHubService.listRepos4("octocat")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: SingleObserver<List<Repo>>{
                    override fun onSuccess(t: List<Repo>?) {
                        println(t?.size)
                    }

                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onError(e: Throwable?) {
                    }
                })

        }.start()

    }
}

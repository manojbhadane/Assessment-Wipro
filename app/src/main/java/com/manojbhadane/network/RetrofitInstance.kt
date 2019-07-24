import com.manojbhadane.app.App
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Singleton retrofit class
 */
object RetrofitInstance {

    private var BASE_URL: String? = null
    private var retrofit: Retrofit? = null

    private val cacheSize = 10 * 1024 * 1024 // 10 MB

    /**
     * init retrofit instance
     */
    fun init(baseURL: String) {
        BASE_URL = baseURL
        getInstance()
    }

    /**
     * get retrofit instance
     */
    fun getInstance(): Retrofit {
        if (retrofit == null) {

            val cache = Cache(App.instance.getCacheDir(), cacheSize.toLong())

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .addInterceptor(offlineInterceptor)
                    .addNetworkInterceptor(onlineInterceptor)
                    .cache(cache)
                    .build()

            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL!!)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build()
        }
        return retrofit!!
    }

    /**
     * init api services
     */
    fun <T> getService(service: Class<T>): T {
        return getInstance().create(service)
    }

    /**
     * online interceptor
     */
    internal var onlineInterceptor: Interceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge = 60 * 60 // read from cache for 300(60 min.) seconds even if there is internet connection
        response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .removeHeader("Pragma")
                .build()
    }

    /**
     * offline interceptor
     */
    internal var offlineInterceptor: Interceptor = Interceptor { chain ->
        var request = chain.request()
        if (!NetworkManager.isConnectingToInternet(App.instance)) {
            val maxStale = 60 * 60 * 24 * 1 // Offline cache available for 1 days
            request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .removeHeader("Pragma")
                    .build()
        }
        chain.proceed(request)
    }
}
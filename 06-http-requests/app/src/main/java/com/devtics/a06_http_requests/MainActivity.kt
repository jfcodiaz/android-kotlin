package com.devtics.a06_http_requests

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import okhttp3.Call
import okhttp3.OkHttpClient
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{fileName}")
    fun getText(@Path("fileName") fileName: String): retrofit2.Call<String>
}
class MainActivity : AppCompatActivity() {

    private lateinit var requestQueue: RequestQueue
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        requestQueue = Volley.newRequestQueue(this)
        val BASE_URL = "https://raw.githubusercontent.com/jfcodiaz/android-kotlin/master/06-http-requests/endpoint/"
        val URL = "${BASE_URL}helloword.txt"
        Log.d(URL, URL)
        val btnNativeHttp = findViewById<Button>(R.id.btnNativeHttp);
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnNativeHttp.setOnClickListener(View.OnClickListener {
            tvResult.text = "Get Request by Native"
            Log.d("Debug", "Click on btn NAtive http")
            Thread {
                val result = makeGetRequest(URL)
                runOnUiThread {
                    tvResult.text = result
                }
            }.start()
        })

        //Http Request with Volley
        val btnVolley = findViewById<Button>(R.id.btnVolley)
        btnVolley.setOnClickListener(View.OnClickListener {
            tvResult.text = "Get Request by Volley"
            makeGetRequestWithVolley(URL,
                onSuccess =  { response -> tvResult.text = response},
                onError = { error ->
                    Log.e("VolleryError", "Error: ${error.message}")
                    tvResult.text = "Error: ${error.message}"
                })
        })
        //OkHttp Request
        val btnOkHttp = findViewById<Button>(R.id.btnOkHttp)
        btnOkHttp.setOnClickListener(View.OnClickListener {
            tvResult.text = "Get Request by OkHTTP"
            Thread {
                val result = makeGetRequestWithOkHttp(URL)
                runOnUiThread {
                    tvResult.text = result
                }
            }.start()
        })

        //Retrofit Simple
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        val service = retrofit.create(ApiService::class.java)

        val btnRetrofit = findViewById<Button>(R.id.btnRetrofit)
        btnRetrofit.setOnClickListener(View.OnClickListener {
            tvResult.text = "Get Request by Retrfit"
            val call = service.getText("helloword.txt")
            call.enqueue(object : Callback<String> {
                override fun onResponse(call: retrofit2.Call<String>, response: retrofit2.Response<String>) {
                    runOnUiThread {
                        if(response.isSuccessful) {
                            tvResult.text = response.body()
                        } else {
                            tvResult.text = "Error: ${response.code()}"
                        }
                    }
                }

                override fun onFailure(call: retrofit2.Call<String>, t: Throwable) {
                    runOnUiThread{
                        tvResult.text = "Error: ${t.message}"
                    }
                }
            })
        })
    }
    private fun makeGetRequestWithOkHttp(url: String): String {
        val request = okhttp3.Request.Builder()
            .url(url)
            .build()

        return try {
            val response: okhttp3.Response = client.newCall(request).execute()
            if(response.isSuccessful) {
                response.body()?.string() ?: "Empty String"
            } else {
                "Error ${response.code()}"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Expection: ${e.message}"
        }
    }
    private fun makeGetRequestWithVolley(
        url: String,
        onSuccess: (String) -> Unit,
        onError: (VolleyError) -> Unit
    ) {
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                response -> onSuccess(response)
            },
            {
                error -> onError(error)
            }
        )

        requestQueue.add(stringRequest)
    }
    fun makeGetRequest(url:String): String? {
        var result: String? = null
        val urlObj = URL(url)
        val connection = urlObj.openConnection() as HttpURLConnection

        try {
            connection.requestMethod = "GET"
            connection.connectTimeout= 5000
            connection.readTimeout = 5000

            val responseCode = connection.responseCode
            if(responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = StringBuilder()
                var line: String?

                while(reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()
                result = response.toString()
            } else {
                Log.d("Error HttpNative","Error $responseCode")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection.disconnect()
        }
        return result
    }

}
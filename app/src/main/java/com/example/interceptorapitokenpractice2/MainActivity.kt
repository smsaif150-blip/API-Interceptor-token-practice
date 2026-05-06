package com.example.interceptorapitokenpractice2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.interceptorapitokenpractice2.databinding.ActivityMainBinding
import com.example.interceptorapitokenpractice2.models.LoginRequest
import com.example.interceptorapitokenpractice2.models.LoginResponse
import com.example.interceptorapitokenpractice2.models.UserResponse
import com.example.interceptorapitokenpractice2.networks.ApiService
import com.example.interceptorapitokenpractice2.networks.retrofitClients
import com.example.interceptorapitokenpractice2.utils.PrefsManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefs: PrefsManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = PrefsManager(applicationContext)


        val savedToken = prefs.getToken()

        if (savedToken==null)
        {
            loginUser()
        }
        else
        {
            getUserData(savedToken)
        }

    }

    private fun loginUser()
    {
        val retrofit = retrofitClients.getClient(null)
        val api = retrofit.create(ApiService::class.java)
        val request = LoginRequest("michaelw","michaelwpass")
        api.login(request).enqueue(object : Callback<LoginResponse>
        {
            override fun onResponse(
                call: Call<LoginResponse?>,
                response: Response<LoginResponse?>
            ) {
                if (response.isSuccessful)
                {
                    val token = response.body()?.accessToken
                    if (token!=null)
                    {
                        prefs.saveToken(token)
                        getUserData(token)
                        Log.d("API","token: $token")
                    }
                }
            }

            override fun onFailure(
                call: Call<LoginResponse?>,
                t: Throwable
            ) {
                Log.e("API","Error: ${t.message}")
            }

        })
    }

    fun getUserData(token: String)
    {
        val retrofit = retrofitClients.getClient(token)
        val api = retrofit.create(ApiService::class.java)

        api.getData().enqueue(object : Callback<UserResponse>{
            override fun onResponse(
                call: Call<UserResponse?>,
                response: Response<UserResponse?>
            ) {
                if (response.isSuccessful)
                {
                    val user = response.body()

                    binding.username.text = user?.username
                    binding.id.text = user?.id.toString()
                    binding.email.text = user?.email
                }
            }

            override fun onFailure(
                call: Call<UserResponse?>,
                t: Throwable
            ) {
                Log.e("API","Error: ${t.message}")
            }
        })
    }
}

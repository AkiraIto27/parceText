package com.example.test

import android.content.res.AssetManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.test.testgson.GsonCompany
import com.example.test.ui.theme.TestTheme
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.example.test.testMoshi.MoshiCompany
import com.example.test.testjackson.JacksonCompany
import com.example.test.testktxserialization.KtxSerializationCompany
import java.io.IOException
import com.squareup.moshi.Moshi.Builder
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val assetManager = resources.assets
        testMethod(assetManager)

        enableEdgeToEdge()
        setContent {
            TestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Throws(IOException::class)
fun testMethod(assetManager: AssetManager) {
    val inputStream = assetManager.open("test.json")
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
    val str: String = bufferedReader.readText()


    // Gson
    val gson = Gson()
    var startTime = System.currentTimeMillis()
    val gsonCompany = gson.fromJson(str, GsonCompany::class.java)
    println("tetetest gson:$gsonCompany")
    var endTime = System.currentTimeMillis()
    println("tetetest gson1回の処理時間：" + (endTime - startTime) + " ms")

    startTime = System.currentTimeMillis()
    for (i in 0..1000) {
        gson.fromJson(str, GsonCompany::class.java)
    }
    endTime = System.currentTimeMillis()
    println("tetetest gson1000回の処理時間：" + (endTime - startTime) + " ms")

    // Jackson
    val mapper = ObjectMapper()
    startTime = System.currentTimeMillis()
    val jacksonCompany: JacksonCompany = mapper.readValue(str, JacksonCompany::class.java)
    println("tetetest jackson:$jacksonCompany")
    endTime = System.currentTimeMillis()
    println("tetetest jackson1回の処理時間：" + (endTime - startTime) + " ms")

    startTime = System.currentTimeMillis()
    for (i in 0..1000) {
        mapper.readValue(str, JacksonCompany::class.java)
    }
    endTime = System.currentTimeMillis()
    println("tetetest jackson1000回の処理時間：" + (endTime - startTime) + " ms")

    // Moshi
    val moshi: Moshi = Builder().add(KotlinJsonAdapterFactory()).build()
    val jsonAdapter = moshi.adapter(MoshiCompany::class.java)
    startTime = System.currentTimeMillis()
    val moshiCompany: MoshiCompany? = jsonAdapter.fromJson(str)
    println("tetetest moshi:$moshiCompany")
    endTime = System.currentTimeMillis()
    println("tetetest moshi1回の処理時間：" + (endTime - startTime) + " ms")
    startTime = System.currentTimeMillis()
    for (i in 0..1000) {
        jsonAdapter.fromJson(str)
    }
    endTime = System.currentTimeMillis()
    println("tetetest moshi1000回の処理時間：" + (endTime - startTime) + " ms")

    // kotlinx serialization
    startTime = System.currentTimeMillis()
    val serializationCompany: KtxSerializationCompany = Json.decodeFromString(str)
    println("tetetest kotlinx.serialization: $serializationCompany")
    endTime = System.currentTimeMillis()
    println("tetetest kotlinx.serialization1回の処理時間：" + (endTime - startTime) + " ms")
    startTime = System.currentTimeMillis()
    for (i in 0..1000) {
        Json.decodeFromString<KtxSerializationCompany>(str)
    }
    endTime = System.currentTimeMillis()
    println("tetetest kotlinx.serialization1000回の処理時間：" + (endTime - startTime) + " ms")
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestTheme {
        Greeting("Android")
    }
}
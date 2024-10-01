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
import kotlinx.serialization.encodeToString
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
    try {
        val gson = Gson()
        var startTime = System.currentTimeMillis()
        val gsonCompany = gson.fromJson(str, GsonCompany::class.java)
        println("tetetest gson:$gsonCompany")
        var endTime = System.currentTimeMillis()
        println("tetetest gson fromJson 1回の処理時間：" + (endTime - startTime) + " ms")

        startTime = System.currentTimeMillis()
        for (i in 0..1000) {
            gson.fromJson(str, GsonCompany::class.java)
        }
        endTime = System.currentTimeMillis()
        println("tetetest gson fromJson 1000回の処理時間：" + (endTime - startTime) + " ms")

        // Gson serialization
        startTime = System.currentTimeMillis()
        val gsonJson = gson.toJson(gsonCompany)
        println("tetetest gson toJson: $gsonJson")
        endTime = System.currentTimeMillis()
        println("tetetest gson toJson 1回の処理時間：" + (endTime - startTime) + " ms")

        startTime = System.currentTimeMillis()
        for (i in 0..1000) {
            gson.toJson(gsonCompany)
        }
        endTime = System.currentTimeMillis()
        println("tetetest gson toJson 1000回の処理時間：" + (endTime - startTime) + " ms")

    } catch (e: Exception) {
        println("tetetest gson exception = ${e.message}")
    }

    // Jackson
    try {
        val mapper = ObjectMapper()
        var startTime = System.currentTimeMillis()
        val jacksonCompany: JacksonCompany = mapper.readValue(str, JacksonCompany::class.java)
        println("tetetest jackson:$jacksonCompany")
        var endTime = System.currentTimeMillis()
        println("tetetest jackson readValue 1回の処理時間：" + (endTime - startTime) + " ms")

        startTime = System.currentTimeMillis()
        for (i in 0..1000) {
            mapper.readValue(str, JacksonCompany::class.java)
        }
        endTime = System.currentTimeMillis()
        println("tetetest jackson readValue 1000回の処理時間：" + (endTime - startTime) + " ms")

        // Jackson serialization
        startTime = System.currentTimeMillis()
        val jacksonJson = mapper.writeValueAsString(jacksonCompany)
        println("tetetest jackson writeValueAsString: $jacksonJson")
        endTime = System.currentTimeMillis()
        println("tetetest jackson writeValueAsString 1回の処理時間：" + (endTime - startTime) + " ms")

        startTime = System.currentTimeMillis()
        for (i in 0..1000) {
            mapper.writeValueAsString(jacksonCompany)
        }
        endTime = System.currentTimeMillis()
        println("tetetest jackson writeValueAsString 1000回の処理時間：" + (endTime - startTime) + " ms")

    } catch (e: Exception) {
        println("tetetest jackson exception = ${e.message}")
    }

    // Moshi
    try {
        val moshi: Moshi = Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(MoshiCompany::class.java)
        var startTime = System.currentTimeMillis()
        val moshiCompany: MoshiCompany? = jsonAdapter.fromJson(str)
        println("tetetest moshi:$moshiCompany")
        var endTime = System.currentTimeMillis()
        println("tetetest moshi fromJson 1回の処理時間：" + (endTime - startTime) + " ms")
        startTime = System.currentTimeMillis()
        for (i in 0..1000) {
            jsonAdapter.fromJson(str)
        }
        endTime = System.currentTimeMillis()
        println("tetetest moshi fromJson 1000回の処理時間：" + (endTime - startTime) + " ms")

        // Moshi serialization
        startTime = System.currentTimeMillis()
        val moshiJson = jsonAdapter.toJson(moshiCompany)
        println("tetetest moshi toJson: $moshiJson")
        endTime = System.currentTimeMillis()
        println("tetetest moshi toJson 1回の処理時間：" + (endTime - startTime) + " ms")

        startTime = System.currentTimeMillis()
        for (i in 0..1000) {
            jsonAdapter.toJson(moshiCompany)
        }
        endTime = System.currentTimeMillis()
        println("tetetest moshi toJson 1000回の処理時間：" + (endTime - startTime) + " ms")

    } catch (e: Exception) {
        println("tetetest moshi exception = ${e.message}")
    }

    // kotlinx serialization
    try {
        var startTime = System.currentTimeMillis()
        val serializationCompany: KtxSerializationCompany = Json.decodeFromString(str)
        println("tetetest kotlinx.serialization: $serializationCompany")
        var endTime = System.currentTimeMillis()
        println("tetetest kotlinx.serialization decodeFromString 1回の処理時間：" + (endTime - startTime) + " ms")
        startTime = System.currentTimeMillis()
        for (i in 0..1000) {
            Json.decodeFromString<KtxSerializationCompany>(str)
        }
        endTime = System.currentTimeMillis()
        println("tetetest kotlinx.serialization decodeFromString 1000回の処理時間：" + (endTime - startTime) + " ms")

        // kotlinx serialization serialization
        startTime = System.currentTimeMillis()
        val kotlinxSerializationJson =
            Json.encodeToString<KtxSerializationCompany>(serializationCompany)
        println("tetetest kotlinx.serialization encodeToString: $kotlinxSerializationJson")
        endTime = System.currentTimeMillis()
        println("tetetest kotlinx.serialization encodeToString 1回の処理時間：" + (endTime - startTime) + " ms")

        startTime = System.currentTimeMillis()
        for (i in 0..1000) {
            Json.encodeToString(serializationCompany)
        }
        endTime = System.currentTimeMillis()
        println("tetetest kotlinx.serialization encodeToString 1000回の処理時間：" + (endTime - startTime) + " ms")

    } catch (e: Exception) {
        println("tetetest kotlinx.serialization exception = ${e.message}")
    }
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
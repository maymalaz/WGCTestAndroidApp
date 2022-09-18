package com.example.wgctestandroidapp.data.common.utils

import android.content.Context
import androidx.annotation.NonNull
import com.example.wgctestandroidapp.BuildConfig

import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

class RequestInterceptor @Inject constructor(
    @ApplicationContext val context: Context,
) : Interceptor {
    @Throws(Exception::class)
    override fun intercept(@NonNull chain: Interceptor.Chain): Response {
        var request = chain.request() //本来的post数据
        //cookies 设置
        val builder = request.newBuilder()

        builder.addHeader("platform", "ANDROID")
        builder.addHeader("Content-Type", "text/plain")
        builder.addHeader("Accept-Encoding", "identity")
        val postBodyString = bodyToString(request.body)

        request = builder.build()
        var response = handleResponse(chain.proceed(request))

        response = processResponse(response)
        val bodyString = response.body!!.string()
        try {
            if (1008 == JSONObject(bodyString).optInt("code")) {
            }
        } catch (e: java.lang.Exception) {
            logE(e.toString())
            e.printStackTrace()
            if (BuildConfig.DEBUG) {
                logH(
                    (String.format(
                        " >>>><<<<<收到返回 \n%d %s", response.code,
                        ("request url " + response.request.url.toString() + "\n"
                                + "request body " + postBodyString + "\n"
                                + "response body " + bodyString)
                    ))
                )
            }
            val result =
                "{\"success\":false,\"code\":0,\"message\":\"Exception: ${e.localizedMessage}\",\"version\":\"app_01\",\"data\":null,\"is_native\":false}"
            return Response.Builder().request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .message("Exception")
                .code(200)
                .body(result.toResponseBody("application/json".toMediaTypeOrNull()))
                .build()
        }
        if (BuildConfig.DEBUG) {
            logH(
                (String.format(
                    " >>>><<<<<收到返回 \n%d %s", response.code,
                    ("request url " + response.request.url.toString() + "\n"
                            + "request body " + postBodyString + "\n"
                            + "response body " + bodyString)
                ))
            )
        }
        return processResponse(makeResponse(response, bodyString))
    }

    private fun processResponse(response: Response): Response {
        logD("======== Video Response $response")
        return response
    }

    private fun bodyToString(request: RequestBody?): String {
        try {
            val buffer = Buffer()
            if (request != null) request.writeTo(buffer) else return ""
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "did not work"
        }
    }


    private fun handleResponse(response: Response): Response {
        var usedResponse = response

            val responseString = response.body?.toString()!!
            logE("========== responseString  $responseString")

        return usedResponse
    }

    private fun makeResponse(response: Response, responseString: String): Response {
        return response.newBuilder()
            .body(responseString.toResponseBody(response.body!!.contentType()))
            .build()
    }
}
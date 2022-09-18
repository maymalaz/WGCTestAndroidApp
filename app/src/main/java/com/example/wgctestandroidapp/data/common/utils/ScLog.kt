package com.example.wgctestandroidapp.data.common.utils

import android.text.TextUtils
import android.util.Log
import com.example.wgctestandroidapp.BuildConfig



private val debug: Boolean = BuildConfig.DEBUG
private const val MAX_LENGTH = 2 * 1024

fun logD(o: Any) {
    logger("d", o)
}

fun logE(o: Any) {
    logger("e", o)
}

fun logI(o: Any) {
    logger("i", o)
}

fun logH(o: Any) {
    logger("h", o)
}

fun logW(o: Any) {
    logger("w", o)
}

fun logDt(msg: String, t: Throwable) {
    logger("d", msg, t)
}

fun logEt(msg: String, t: Throwable) {
    logger("e", msg, t)
}

fun logIt(msg: String, t: Throwable) {
    logger("i", msg, t)
}

fun logWt(msg: String, t: Throwable) {
    logger("w", msg, t)
}

// Log An Object
private fun logger(type: String, o: Any) {
    if (!debug) {
        return
    }
    val msg = o.toString()
    val tag = getTag(getCallerStackTraceElement())
    when (type) {
        "i" -> Log.i(tag, msg)
        "h" -> i(tag, msg)
        "d" -> Log.d(tag, msg)
        "e" -> Log.e(tag, msg)
        "w" -> Log.w(tag, msg)
    }
}

// Log An Error
private fun logger(type: String, msg: String, e: Throwable) {
    if (!debug) {
        return
    }
    val tag = getTag(getCallerStackTraceElement())
    when (type) {
        "i" -> Log.i(tag, msg, e)
        "d" -> Log.d(tag, msg, e)
        "e" -> Log.e(tag, msg, e)
        "w" -> Log.w(tag, msg, e)
    }
}

private fun i(TAG: String, msg: String) {
    val strLength = msg.length
    var start = 0
    var end = MAX_LENGTH
    for (i in 0..999) {
        if (strLength > end) {
            Log.e("$TAG>>>><<<<<$i", msg.substring(start, end))
            start = end
            end += MAX_LENGTH
        } else {
            Log.e("$TAG>>>><<<<<$i", msg.substring(start, strLength))
            break
        }
    }
}

private fun getTag(element: StackTraceElement): String {
    var tag = "%s.%s(Line:%d)"
    var callerClazzName = element.className
    callerClazzName = callerClazzName.substring(
        callerClazzName
            .lastIndexOf(".") + 1
    )
    tag = String.format(
        tag, callerClazzName, element.methodName,
        element.lineNumber
    )
    return tag
}

private fun getCallerStackTraceElement(): StackTraceElement {
    return Thread.currentThread().stackTrace[5]
}
package com.firstlink.duo.util.Extensions

import android.util.Log

/**
 * @author wzq
 */
fun Any.v(tag: String, msg: String) {
    Log.v(tag, msg)
}

fun Any.d(tag: String, msg: String) {
    Log.d(tag, msg)
}

fun Any.i(tag: String, msg: String) {
    Log.i(tag, msg)
}

fun Any.w(tag: String, msg: String) {
    Log.w(tag, msg)
}

fun Any.e(tag: String, msg: String) {
    Log.e(tag, msg)
}

fun Any.wtf(tag: String, msg: String) {
    Log.wtf(tag, msg)
}

fun Any.v(msg: String) {
    Log.v(tag, msg)
}

fun Any.d(msg: String) {
    Log.d(tag, msg)
}

fun Any.i(msg: String) {
    Log.i(tag, msg)
}

fun Any.w(msg: String) {
    Log.w(tag, msg)
}

fun Any.e(msg: String) {
    Log.e(tag, msg)
}

fun Any.wtf(msg: String) {
    Log.wtf(tag, msg)
}

private val Any.tag: String?
    get() = javaClass.simpleName
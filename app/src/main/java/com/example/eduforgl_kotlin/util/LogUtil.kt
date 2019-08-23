package com.example.eduforgl_kotlin.util

import android.util.Log

class LogUtil {
    companion object {
        val VERBOSE = 1
        val DEBUG = 2
        val INFO = 3
        val WARN = 4
        val ERROR = 5
        val NOTHING = 6
        var level = VERBOSE

        fun v(tag: String, msg: String) {
            if (level <= VERBOSE) {
                Log.v(tag, msg)
            }
        }

        fun d(tag: String, msg: String) {
            if (level <= DEBUG) {
                Log.d(tag, msg)
            }
        }

        fun i(tag: String, msg: String) {
            if (level <= INFO) {
                Log.i(tag, msg)
            }
        }

        fun w(tag: String, msg: String) {
            if (level <= WARN) {
                Log.w(tag, msg)
            }
        }

        fun e(tag: String, msg: String) {
            if (level <= ERROR) {
                Log.e(tag, msg)
            }
        }
    }
}
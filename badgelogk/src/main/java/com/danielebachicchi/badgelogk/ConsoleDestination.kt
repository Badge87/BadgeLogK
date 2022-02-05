package com.danielebachicchi.badgelogk

import android.util.Log

/**
 * [LogDestination] that will print logs on the console.
 *
 * @author Daniele Bachicchi
 */
class ConsoleDestination: LogDestination() {

    init {
        format = "'['m':'l']' M"
    }

    override fun send(
        level: Logger.LogLevel,
        message: String,
        error: Throwable?,
        tag: String,
        file: String,
        method: String,
        line: Int
    ): String {
        val result = super.send(level, message, error, tag, file, method, line)
        if (result.isEmpty())
            return ""

        when(level) {
            Logger.LogLevel.VERBOSE -> Log.v(tag, result, error)
            Logger.LogLevel.DEBUG -> Log.d(tag, result, error)
            Logger.LogLevel.INFO -> Log.i(tag, result, error)
            Logger.LogLevel.WARNING -> Log.w(tag, result, error)
            Logger.LogLevel.ERROR -> Log.e(tag, result, error)
        }
        return result
    }
}
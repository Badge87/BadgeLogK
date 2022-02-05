package com.danielebachicchi.badgelogk

import java.io.File
import java.lang.AssertionError

/**
 * Handle all logs logic
 *
 * This class cannot be instantiated, all methods are static
 * Before using it, remember to call [Logger.setup] to initialize it. Just call once,
 * preferably on application startup.
 *
 * @author Daniele Bachicchi
 */
class Logger private constructor(){
    enum class LogLevel{ VERBOSE, DEBUG, INFO, WARNING, ERROR }


    /**
     * Cannot initialize this class. Will throw an exception if you try to call init
     */
    init { throw AssertionError("Logger class cannot be instantiated!") }

    companion object{
        private var destinations: MutableList<LogDestination> = mutableListOf()

        /**
         * Initialize Logger functionality. Add all your [LogDestination] here.
         *
         * @param destination your [LogDestination] where Logger dispatch the logs.
         */
        fun setup(vararg destination: LogDestination = arrayOf(ConsoleDestination())){
            destinations = mutableListOf(*destination)

        }

        /**
         * Dispatch a verbose log to all [LogDestination], previously added in [Logger.setup].
         *
         * @param message message of your log.
         * @param error Throwable error of your log, if present, default null.
         * @param tag tag of your log, default is the class name of the caller.
         * @param file the class caller of the log request, default is the class name of the caller.
         * @param method the method name where this method is called, default is the method name of the caller.
         * @param line the line number of the class file where this method is called, default is the line of the method caller.
         *
         */
        fun verbose(message:String, error: Throwable? = null, tag: String? = null, file: String = getClassStack(), method: String = getMethodStack(), line: Int = getLineNumber()){
            prepareLog(LogLevel.VERBOSE, message, error, tag ?: file, file, method, line)
        }
        /**
         * Dispatch a debug log to all [LogDestination], previously added in [Logger.setup].
         *
         * @param message message of your log.
         * @param error Throwable error of your log, if present, default null.
         * @param tag tag of your log, default is the class name of the caller.
         * @param file the class caller of the log request, default is the class name of the caller.
         * @param method the method name where this method is called, default is the method name of the caller.
         * @param line the line number of the class file where this method is called, default is the line of the method caller.
         *
         */
        fun debug(message:String, error: Throwable? = null, tag: String? = null, file: String = getClassStack(), method: String = getMethodStack(), line: Int = getLineNumber()){
            prepareLog(LogLevel.DEBUG, message, error, tag ?: file, file, method, line)
        }
        /**
         * Dispatch a info log to all [LogDestination], previously added in [Logger.setup].
         *
         * @param message message of your log.
         * @param error Throwable error of your log, if present, default null.
         * @param tag tag of your log, default is the class name of the caller.
         * @param file the class caller of the log request, default is the class name of the caller.
         * @param method the method name where this method is called, default is the method name of the caller.
         * @param line the line number of the class file where this method is called, default is the line of the method caller.
         *
         */
        fun info(message:String, error: Throwable? = null, tag: String? = null, file: String = getClassStack(), method: String = getMethodStack(), line: Int = getLineNumber()){
            prepareLog(LogLevel.INFO, message, error, tag ?: file, file, method, line)
        }
        /**
         * Dispatch a warning log to all [LogDestination], previously added in [Logger.setup].
         *
         * @param message message of your log.
         * @param error Throwable error of your log, if present, default null.
         * @param tag tag of your log, default is the class name of the caller.
         * @param file the class caller of the log request, default is the class name of the caller.
         * @param method the method name where this method is called, default is the method name of the caller.
         * @param line the line number of the class file where this method is called, default is the line of the method caller.
         *
         */
        fun warning(message:String, error: Throwable? = null, tag: String? = null, file: String = getClassStack(), method: String = getMethodStack(), line: Int = getLineNumber()){
            prepareLog(LogLevel.WARNING, message, error, tag ?: file, file, method, line)
        }
        /**
         * Dispatch an error log to all [LogDestination], previously added in [Logger.setup].
         *
         * @param message message of your log.
         * @param error Throwable error of your log, if present, default null.
         * @param tag tag of your log, default is the class name of the caller.
         * @param file the class caller of the log request, default is the class name of the caller.
         * @param method the method name where this method is called, default is the method name of the caller.
         * @param line the line number of the class file where this method is called, default is the line of the method caller.
         *
         */
        fun error(message:String, error: Throwable? = null, tag: String? = null, file: String = getClassStack(), method: String = getMethodStack(), line: Int = getLineNumber()){
            prepareLog(LogLevel.ERROR, message, error, tag ?: file, file, method, line)
        }

        /**
         * Retrieve all existing log file generated by all [FileDestination] added during [Logger.setup]
         *
         * @return all log files generated.
         */
        fun getLogFiles(): Array<File> {
            val result: MutableList<File> = mutableListOf()
            for (destination in destinations) {
                if (destination is FileDestination) {
                    result.addAll(destination.getLogFiles())
                }
            }
            return result.toTypedArray()
        }

        private fun prepareLog(level: LogLevel,
                               message: String,
                               error: Throwable?,
                               tag: String,
                               file: String,
                               method: String,
                               line: Int) {
            for (destination in destinations) {
                destination.send(level,message,error,tag,file,method,line)
            }
        }


        private fun getLineNumber() = Thread.currentThread().stackTrace[4].lineNumber
        private fun getClassStack() = Thread.currentThread().stackTrace[4].className
        private fun getMethodStack() = Thread.currentThread().stackTrace[4].methodName

    }


}
<p align="center">
<img src="https://github.com/Badge87/BadgeLog/blob/master/Example/BadgeLog/Images.xcassets/AppIcon.appiconset/1024.png?raw=true" width="250" height="250">
</p>

# BadgeLog (Kotlin version)
For the iOS swift version, see [this page](https://github.com/Badge87/BadgeLog)

BadgeLog is an Android Kotlin library that helps you manage logs within your application:

- Centralize all log management logic
- Print console logs in a nice way and customize it.
- Ability to save logs to file and export them

## Configure
Setting up the library is very quick and easy. You need to invoke the following method:

```kotlin
Logger.setup(ConsoleDestination())
```
This will setup Logger with the funcionality to print logs into Logcat.
If you want to save log in file also, just add any destination you want in setup():
```kotlin
Logger.setup(ConsoleDestination(), FileDestination())
```
I recommend to call setup() within the initialization of the App or in any case as soon as possible.


The library is ready to log! to log in, simply call up:

```kotlin
Logger.verbose("I'm a verbose log!")
Logger.debug("I'm a debug log!")
Logger.info("I'm a info log!")
Logger.warning("I'm a warning log!")
Logger.error("I'm an error log!")
Logger.error("I am an error with exception log!", Throwable("Custom Fake Exception"))
```

## LogDestination
LogDestination is a source that will print the logs in different destination. At the moment there are this destination templates:
- ConsoleDestination: will print logs inside Logcat. You can specify the single row format and the min log level.
- FileDestination: will print logs inside files. You can specify the path, the min log level and the single row format.

You can create a custom LogDestination. Just subclass LogClass Destination and handle the log row string inside send() method. This example show how ConsoleDestination handle its logic inside send() method:
```kotlin
override fun send(
        level: Logger.LogLevel,
        message: String,
        error: Throwable?,
        tag: String,
        file: String,
        method: String,
        line: Int
    ): String {
        //call super to retrive the formatted log row string
        val result = super.send(level, message, error, tag, file, method, line)
        //if return empty string, it means that the minLevel is greater that log row level
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
```

## Requirements
- Min SDK 21

## Installation

TODO


## Author

Badge87, bacdaniele@gmail.com

## License

BadgeLog is available under the MIT license. See the LICENSE file for more info.

<p align="center">
<img src="https://github.com/Badge87/BadgeLog/blob/master/Example/BadgeLog/Images.xcassets/AppIcon.appiconset/1024.png?raw=true" width="250" height="250">
</p>

# BadgeLog (Kotlin version)
For the iOS swift version, see [this page](https://github.com/Badge87/BadgeLog)

BadgeLog is an Android Kotlin library that helps you manage logs within your application:

- Centralize all log management logic
- Print console logs in a nice way and customize it.
- Ability to save logs to file and export them

## Installation

```gradle
repositories {
  mavenCentral()
}

dependencies {
   implementation 'com.danielebachicchi.badgelogk:badgelogk:0.1.0'
}
```

## Configure
Setting up the library is very quick and easy. You need to invoke the following method:

```kotlin
Logger.setup(ConsoleDestination())
```
This will setup ```Logger``` with the funcionality to print logs into Logcat.
If you want to save log in file also, just add any destination you want in setup():
```kotlin
Logger.setup(ConsoleDestination(), FileDestination())
```
I recommend to call ```setup()``` within the initialization of the App or in any case as soon as possible.


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
```LogDestination``` is a source that will print the logs in different destination. 
Every Log request will be dispatched into every Destination added during setup()

At the moment there are this destination templates:
- ```ConsoleDestination```: will print logs inside Logcat. You can specify the single row format and the min log level.
- ```FileDestination```: will print logs inside files. You can specify the path, the min log level and the single row format.

You can create a custom ```LogDestination```. Just subclass LogDestination and handle the log row string inside ```send()``` method. This example show how ConsoleDestination handle its logic inside send() method:
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
## Log Format

For each `LogDestination`, you can set the format in which they will display the logs. The format is a string that can contain custom characters and preset values.
Insert your own custom character inside apex.
The default format is

```
"T '-' L '-' '['c':'l']' M e"

2022-02-08T13:00:00Z - VERBOSE - [ContentView:18] I'm a log!
```
The default format for ConsoleDestination is:
```swift
"'['m':'l']' M"

VERBOSE [ContentView:18] I'm a log!
```

Preset Char (case sensitive) are:
- `M` -> the message of the log.
- `m` -> the function name where the log has been requested
- `L` -> the `LogLevel` type (VERBOSE, DEBUG, INFO....)
- `l` -> the line number of the file where the log has been requested
- `f` -> the file name, fullpath, where the log has been requested
- `c` -> the file name, without path, where the log has been requested
- `e` -> the NSError of the log, if present.
- `T` -> the date of the log, formatted with var `dateFormat` of the `LogDestination`.
- `t` -> the tag of the log.
- " " -> empty space.


## Requirements
- Min SDK 21

## Author

Daniele Bachicchi (Badge87)

## License

BadgeLog is available under the MIT license. See the LICENSE file for more info.

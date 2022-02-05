<p align="center">
<img src="https://github.com/Badge87/BadgeLog/blob/master/Example/BadgeLog/Images.xcassets/AppIcon.appiconset/1024.png?raw=true" width="250" height="250">
</p>

# BadgeLog (Kotlin version)


BadgeLog is an Android Kotlin library that helps you manage logs within your application:

- Centralize all log management logic
- Print console logs in a nice way and customize it.
- Ability to save logs to file and export them

## Configure
Setting up the library is very quick and easy. You need to invoke the following method:

```kotlin
Logger.setup()
```
I recommend to put it within the initialization of the App or in any case as soon as possible


The library is ready to log! to log in, simply call up:

```kotlin
Logger.verbose("I'm a verbose log!")
Logger.debug("I'm a debug log!")
Logger.info("I'm a info log!")
Logger.warning("I'm a warning log!")
Logger.error("I'm an error log!")
Logger.error("I am an error with exception log!", Throwable("Custom Fake Exception"))
```

The detailed documentation is still in progress.


## Requirements
- Min SDK 21

## Installation

TODO


## Author

Badge87, bacdaniele@gmail.com

## License

BadgeLog is available under the MIT license. See the LICENSE file for more info.

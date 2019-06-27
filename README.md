
# LogDNA-Android-Client
Android client for LogDNA

[![](https://jitpack.io/v/mazenrashed/LogDNA-Android-Client.svg)](https://jitpack.io/#mazenrashed/LogDNA-Android-Client)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-Mazen%20Rashed-green.svg?style=flat )]( https://android-arsenal.com/details/1/7545 )

###  Add the JitPack repository to your build file
```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

### Add dependency
```groovy
dependencies {
	implementation 'com.github.mazenrashed:LogDNA-Android-Client:${LAST_VERSION}'
}
```
### Add permissions to manifest
```groovy
<uses-permission android:name="android.permission.INTERNET" />
```
### Initialize LogDNA
Should be initialized once in `Application.onCreate()`:
```kotlin
LogDna.init(LOG_DNA_API_KEY, APP_NAME)
```
### Start Logging
```kotlin
LogDna.log(  
        Line.Builder().setLine("Some Test")  
                .addCustomField(Line.CustomField("fName", "mazen"))  
                .addCustomField(Line.CustomField("lName", "rashed"))  
		.addCustomField(Line.CustomField("age", 25))
                .setLevel(Line.LEVEL_DEBUG)  
                .setTime(System.currentTimeMillis())  
                .build()  
)
```

## Listen to your logs

### Using callback
```kotlin
LogDna.logResultsListener = { logResult ->  
  Log.d("LogDna", "${logResult.isSuccessful}, ${logResult.message}, ${logResult.logRequest.uid}")  
}
```
### Using RxJava / RxKotlin

 Add  **[RxRelay](https://github.com/JakeWharton/RxRelay)** dependency
```groovy
dependencies {
	implementation 'com.jakewharton.rxrelay2:rxrelay:2.1.0'
}
```
Then subscribe to logResults
```kotlin
LogDna.logResults.subscribe {  logResult ->
  Log.d("LogDna", "${logResult.isSuccessful}, ${logResult.message}, ${logResult.logRequest.uid}")  
}
```
## Contributing

We welcome contributions to LogDNA Android Client !
* ⇄ Pull requests and ★ Stars are always welcome.

## Already used by :

![Cat® App: Fleet Management](https://lh3.googleusercontent.com/yhpI5MrMIQI3A6tTlzTUG-lKbT6PVE3WyU1XJV3IFta51GGMFGG-52Zo2FxiMuLg9g=s180)
### [Cat® App: Fleet Management](https://play.google.com/store/apps/details?id=com.cat.corelink&hl=en_AU)

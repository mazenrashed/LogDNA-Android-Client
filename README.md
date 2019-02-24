
# LogDNA-Android-Client
Android client for LogDNA

[![](https://jitpack.io/v/mazenrashed/LogDNA-Android-Client.svg)](https://jitpack.io/#mazenrashed/LogDNA-Android-Client)

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
                .setLevel(Line.LEVEL_DEBUG)  
                .setTime(System.currentTimeMillis())  
                .build()  
)
```

### Listen to your logs
```kotlin
LogDna.logResults.subscribe {  logResult ->
  Log.d("LogDna", "${logResult.isSuccessful}, ${logResult.message}, ${logResult.logRequest.uid}")  
}
```

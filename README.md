# TPL Maps Android SDK Samples

A Native Android SDK to integrate Vector 3D Maps in your Android Application Project.

The project is tested on Android Studio versions 3.3, 3.3.1 & 3.4.2 - if you have issues building it, please upgrade to one of these versions.


## Configurations
### Dependencies/Artifacts
```markdown
implementation 'com.tpl.maps.sdk:maps:1.3.1' //MAPS
implementation 'com.tpl.maps.sdk:places:1.1.1' //SEARCH/PLACES
implementation 'com.tpl.maps.sdk:routing:1.1.1' //ROUTING
```
### Root Level build.gradle
```
allprojects {
    repositories {
        jcenter()
        mavenCentral()
        google()
        maven { url "http://api.tplmaps.com:8081/artifactory/example-repo-local/" }
    }
}
```
### AndroidManifest.xml
```
<meta-data
    android:name="@string/metadata_name_api_key"
    android:value="YOUR_API_KEY" />
```
### Screenshot
![Maps](/Screenshots/Maps.png?raw=true "Preview Maps")

### Sample APK
[APK](/APK/samples-debug.apk)

### Contributor
Muhammad Hassan Jamil  (Team Lead Android Development - TPL Maps - hassan.jamil@tplmaps.com)

### User guide
We are writing developers guide till then consult

Developers guide from: https://api.tplmaps.com/android-doc/

API Documentation from: https://api.tplmaps.com/apiportal/#/portal/sdk-doc 

## Note
If you got a crash for your application targeting API level 28 and higher like
```
java.lang.NoClassDefFoundError: Failed resolution of: Lorg/apache/http/ProtocolVersion;
at com.android.volley.toolbox.HurlStack.performRequest(HurlStack.java:108)
at com.android.volley.toolbox.BasicNetwork.performRequest(BasicNetwork.java:93)
at com.android.volley.NetworkDispatcher.run(NetworkDispatcher.java:105)
Caused by: java.lang.ClassNotFoundException: Didn't find class "org.apache.http.ProtocolVersion" on path: DexPathList[[zip file "/data/app/com.tplmaps.android.sdksamples-oS6o2rN2pC0liRlFuVormg==/base.apk", zip file "/data/app/com.tplmaps.android.sdksamples-...
```
### Solution
Update: This is no longer a bug or a workaround, it is required if your app targets API Level 28 (Android 9.0) or above and uses the Google Maps SDK for Android 16.0.0 or below (or if your app uses the Apache HTTP Legacy library). It is now included in the [official docs](https://developers.google.com/maps/documentation/android-sdk/config#specify_requirement_for_apache_http_legacy_library). The public issue has been [closed](https://issuetracker.google.com/issues/79478779#comment11) as intended behavior.

This is a [bug](https://issuetracker.google.com/issues/79478779#comment3) on the Google Play Services side, until it's fixed, you should be able to workaround by adding this to your `AndroidManifest.xml` inside the `<application>` tag:
```
<uses-library android:name="org.apache.http.legacy" android:required="false" />
```
[Answer Reference](https://stackoverflow.com/questions/50461881/java-lang-noclassdeffounderrorfailed-resolution-of-lorg-apache-http-protocolve)

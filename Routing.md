# Setup Routing API
To setup TPL Maps in your application follow these steps
1. Add the following configuration in project level `build.gradle`
``` groovy
allprojects {
repositories {
   jcenter()
   maven { url "http://api.tplmaps.com:8081/artifactory/example-repo-local/"
   }
}
```
2. Add the following gradle dependency in android application module’s `build.gradle`
``` groovy
dependencies {
  implementation 'com.tpl.maps.sdk:maps:1.3.1'
}
```
3. Add Internet permission in your `AndroidManifest.xml`
``` xml
<uses-permission android:name="android.permission.INTERNET" />
```
4. Configure API Key
   - Create an account on [TPLMaps LBS Portal](https://api.tplmaps.com/apiportal).
   - Generate Android API Key through [Generate Key](https://api.tplmaps.com/apiportal/#/app/key-generation) option.
   - Copy the key put it into `<meta-data>` tag mentioned below and copy the tag in your project’s `AndroidManifest.xml` under `<application>` tag
``` xml
      <meta-data
        android:name="com.tplmaps.android.sdk.API_KEY"
        android:value="YOUR_API_KEY_HERE" />
```
> Updating the file...
# Setup Places/Search API
## Configure API Key


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
4. Configure/Get API Key for your project. See section [Configure API Key]()
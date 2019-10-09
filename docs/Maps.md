# Setup Maps API
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
  implementation 'com.tpl.maps.sdk:maps:1.4.1'
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
5. Add `MapView` in your `layout.xml`
``` xml
<com.tplmaps3d.MapView
  android:id="@+id/map"
  android:layout_width="match_parent"
  android:layout_height="match_parent" />
```
6.	Get `MapView` resource in your Activity’s `onCreate()` method, also call `mapView.onCreate()` life cycle method by adding the following lines of code
``` java
MapView mapView= (MapView) findViewById(R.id.map);
mapView.onCreate(savedInstanceState);
```
7.	Call MapView’s other life cycle methods by putting this code snippet into your Activity
``` java
@Override
protected void onResume() {
  super.onResume();
  mapView.onResume();
}

@Override
protected void onPause() {
  super.onPause();

  if(mapView != null)
    mapView.onPause();
  
}

@Override
protected void onStart() {
  super.onStart();

  if(mapView!= null)
    mapView.onStart();
}

@Override
protected void onStop() {
  super.onStop();

  if(mapView!= null)
    mapView.onStop();
}

@Override
protected void onDestroy() {
  super.onDestroy();

  if(mapView!= null)
    mapView.onDestroy();
}
```
8. Implement `MapView.OnMapReadyCallback` interface with your `Activity` and use the `onMapReady(MapController)` callback method to get a handle to the `MapController` object. The callback is triggered when the map is ready to be used. It provides a non-null instance of `MapController`. You can use the `MapController` object to set the view options for the map e.g. add a marker etc.bThe method will look like this:
``` java
@Override
public void onMapReady(final MapController mapController) {
  // TODO: Map loaded and ready, write your map tasks here by using the mapController instance
}
```
9. Load Map by adding the below lines of code. The method loads map asynchronously and you will get callback in `onMapReady(MapController)` when map ready. The method will take a reference of `MapView.OnMapReadyCallback` instance as parameter.
``` java
   mapView.loadMapAsync(this);
```
> Note: `MapView.loadMapAsync()` must be called from the main thread, and the callback will be executed in the main thread


## Final Code
### AndroidManifest.xml
``` xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
  package="com.tplmaps.android">

<uses-permission android:name="android.permission.INTERNET" />

<application
  android:allowBackup="true"
  android:icon="@mipmap/ic_launcher"
  android:label="@string/app_name"
  android:supportsRtl="true">

  <activity
    android:name=".ActivityTPLMaps "
    android:configChanges="screenSize|orientation"
    android:label="@string/app_name">
    <intent-filter>
      <action android:name="android.intent.action.MAIN" />
      <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
  </activity>


  <meta-data
    android:name="com.tplmaps.android.sdk.API_KEY"
    android:value="YOUR_API_KEY" />

</application>

</manifest>
```
## ActivityTPLMaps.java
``` java
public class ActivityTPLMaps extends AppCompatActivity implements MapView.OnMapReadyCallback {

private static final String TAG = ActivityTPLMaps.class.getSimpleName();
private MapView mapView;

@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.layout_tpl_maps);

  // Initializing and getting MapView resource
  mapView = (MapView) findViewById(R.id.map);

  // Map View's life cycle
  mapView.onCreate(savedInstanceState);

  // Registering listener, loading map asynchronously
  mapView.loadMapAsync(this);
}

@Override
protected void onResume() {
  super.onResume();
  mapView.onResume();
}

@Override
protected void onPause() {
  super.onPause();

  if(mapView != null)
    mapView.onPause();
}

@Override
protected void onStart() {
  super.onStart();

  if(mapView != null)
    mapView.onStart();
}

@Override
protected void onStop() {
  super.onStop();

  if(mapView != null)
    mapView.onStop();
}

@Override
protected void onDestroy() {
  super.onDestroy();

  if(mapView != null)
    mapView.onDestroy();
}

@Override
public void onMapReady(final MapController mapController) {
  Toast.makeText(this, "Map Ready", Toast.LENGTH_SHORT).show();
  // TODO: Map loaded and ready, write your map tasks here
}
}
```
## layout_tpl_maps.xml
``` xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:tools="http://schemas.android.com/tools"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  tools:context=".sdk.samples.activities.ActivityMaps">

  <com.tplmaps3d.MapView
    android:id="@+id/map"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />

</RelativeLayout>
```

You will get TPL Maps loaded successfully in your application.
> Furthermore, you can view or download our open source [samples project](https://github.com/TPLMaps/TPLMapsAndroidSdkSamples) to get help for more features of SDK.

## Proguard
Add the following rules to your app module's `proguard-rules.pro` file, to generate **minified release build**
``` groovy
-keep class com.tplmaps3d.** { *; }

-dontwarn org.xmlpull.v1.**
-keep class org.xmlpull.v1.** { *; }
```

## Screenshot
<p float="left">
 <img src="/images/screenshots/Maps.png" width="150" />
</p>

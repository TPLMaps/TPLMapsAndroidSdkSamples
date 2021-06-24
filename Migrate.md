# Table of content
- [Migrate From Google Maps to TPL Maps](#migrate-from-google-maps-to-tpl-maps)
    + [Creating a Map](#creating-a-map)
    + [Adding a Marker](#adding-a-marker)

# Migrate From Google Maps to TPL Maps

This part describes how to create a map using Google Map SDK and TPL Maps SDK.

### Creating a Map

#### Google Maps SDK

1. In the [Cloud Console](https://cloud.google.com/console/apis/library/maps-android-backend.googleapis.com), enable the Maps SDK for Android on your project. For details, see  [enable APIs](https://developers.google.com/maps/gmp-get-started#enable-api-sdk).

2. Follow the [Get an API Key](https://developers.google.com/maps/documentation/android-sdk/get-api-key)  Follow the guide to get, add, and restrict an API key.

3. Copy the key and put it into `meta-tag` mentioned below and place the tag into your project's **AndroidManifest.xml** under `<application>` tag:

   ```xml
   <meta-data android:name="com.google.android.geo.API_KEY"
   	android:value="YOUR_GOOGLE_MAPS_API_KEY_HERE"/>
   ```


4. Add build dependencies to the **build.gradle** file of your app.

   ```groovy
   implementation 'com.google.android.gms:play-services-maps:17.0.0'  
   ```

5. Add a fragment to the activity layout file.

   ```xml
   <fragment   
       android:id="@+id/mapFragment"   
       android:name="com.google.android.gms.maps.MapFragment"
       android:layout_width="match_parent"   
       android:layout_height="match_parent"/>  
   ```

6. Add the following java code to in you activity class's `onCreate()` method

   ```java
   ((SupportMapFragment) getSupportFragmentManager()
                   .findFragmentById(R.id.map)).getMapAsync(this);
   ```

7. Follow [guide](https://developers.google.com/maps/documentation/android-sdk/start), to see more.

#### TPL Maps SDK

1. Create an account on [TPLMaps LBS Portal](https://api.tplmaps.com/apiportal).

2. Get the Key, from your **Dashboard**, find **API Keys** Tab and [Generate New Key](https://api.tplmaps.com/apiportal/#/app/billing/api-key-management) from the option.

3. Get the Key (without **ORIGIN**), from your **Dashboard**, find **API Keys** Tab and [Generate New Key](https://api.tplmaps.com/apiportal/#/app/billing/api-key-management) from the option. 

   Copy the key put it into `meta-tag` mentioned below and copy the tag in your project’s **AndroidManifest.xml** under `<application>` tag

    ```xml
    <meta-data android:name="com.tplmaps.android.sdk.API_KEY"
               android:value="YOUR_API_KEY_HERE" />
    ```
    If you generate **API Key** with the **ORIGIN** mentioned, you must need to add the ORIGIN in **AndroidManifest.xml** in the tag mentioned below with API Key.
    > The tag below is **optional** for non-origin based API Key.

    ```xml
    <meta-data android:name="com.tplmaps.android.sdk.ORIGIN"
               android:value="YOUR_ORIGIN_HERE" />
    ```

4. Add dependency url in your root project's **build.gradle** .

   ```groovy
   allprojects {   
       repositories {    
           jcenter()
           google()
           maven { url "http://api.tplmaps.com:8081/artifactory/example-repo-local/" }   
       }
   }
   ```

   Add build dependencies to the **build.gradle** file of your app module.

   ```groovy
   implementation 'com.tpl.maps.sdk:maps:1.6.3'  
   ```

5. Add `MapView` to the activity layout file.

   ```xml
   <com.tplmaps3d.MapView android:id="@+id/map"
                      android:layout_width="match_parent"
                      android:layout_height="match_parent"  />  
   ```

6. Add the following java code to in you activity class's `onCreate()` method and add imports.

   ```java
   ((MapView) findViewById(R.id.map)).loadMapAsync(this);
   ```

   Add imports and change `OnMapReadyCallback` to `MapView.OnMapReadyCallback`

   ```java
   import com.tplmaps3d.MapController;
   import com.tplmaps3d.MapView;
   ```

   And implement the callback method in your activity as:

   ``` java
     @Override
     public void onMapReady(MapController mapController) {
    		// TODO Add your maps related functions here
     }
   ```

7. Follow sections [Setup Maps](#setup-maps) or [Table of Content](#table-of-content), to get more info.

   > Better to keep a single dependencies/configurations (of Google Maps or TPL Maps) to avoid duplication and same class names confusion.

### Adding a Marker

This part describes how to add a marker using Google Map SDK and TPL Maps SDK.

#### Google Maps SDK

1. Define a global **`GoogleMap`** object.

   ``` java
   private GoogleMap mGoogleMap;
   ```

2. Implement the **`OnMapReadyCallback`** API and override the **`onMapReady`** method.

   ``` java
   @Override  
   public void onMapReady(GoogleMap googleMap) {  
      mGoogleMap = googleMap;  
      LatLng amsterdam = new LatLng(52.37, 4.90);  
      mGoogleMap.addMarker(new MarkerOptions().position(amsterdam).title("Amsterdam"));
      mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(amsterdam, 8));
   } 
   ```

3. Load **`SupportMapFragment`** in the **`onCreate`** method of **`Activity`** and call **`getMapAsync()`**.

   ``` java
   ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                   .getMapAsync(this);
   ```

#### TPL Maps SDK

1. Define a global **`MapController`** object.

   ``` java
   private MapController mMapController;
   ```

2. Implement the **`MapView.OnMapReadyCallback`** API and override the **`onMapReady`** method.

   ``` java
   @Override  
   public void onMapReady(MapController mapController) {  
      mMapController = mapController;  
      LngLat islamabad = new LngLat(73.093104, 33.730494);
      mapController.addMarker(new MarkerOptions().position(islamabad)
                   .title("Islamabad"));
      mMapView.getMapController().animateCamera(
                   CameraPosition.fromLngLatZoom(mapController, islamabad, 8), 0);
   } 
   ```

3. Load **`MapView`** in the **`onCreate`** method of **`Activity`** and call **`loadMapAsync()`**.

   ``` java
   ((MapView) findViewById(R.id.map)).loadMapAsync(this);
   ```
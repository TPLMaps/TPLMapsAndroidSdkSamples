# TPL Maps Android SDK Samples

An open source Android (Java) Native Library to incorporate/start Android Qibla Direction Activity in your Native Android Applcation.


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
### Contributor
Muhammad Hassan Jamil - Team Lead Android Development - TPL Maps - hassan.jamil@tplmaps.com
### User guide
We are writing developers guide till then consult

Developers guide from: https://api.tplmaps.com/android-doc/

API Documentation from: https://api.tplmaps.com/apiportal/#/portal/sdk-doc 

# TPL Maps Android SDK Samples

An open source Android (Java) Native Library to incorporate/start Android Qibla Direction Activity in your Native Android Applcation.


## Configurations
### Dependency
```markdown
MAPS: implementation 'com.tpl.maps.sdk:maps:1.3.1'
SEARCH/PLACES: implementation 'com.tpl.maps.sdk:places:1.1.1'
ROUTING: implementation 'com.tpl.maps.sdk:routing:1.1.1'
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
![Alt text](/Screenshot.jpeg?raw=true "Preview")
### Contributor
Muhammad Hassan Jamil - Team Lead Android Development - TPL Maps - hassan.jamil@tplmaps.com

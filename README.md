# map

Step 1. Add the JitPack repository to your build file
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  Step 2. Add the dependency
  dependencies {
	        implementation 'com.github.birdwang:map:1.0.0'
	}
  
  just use GoogleMapClient.java

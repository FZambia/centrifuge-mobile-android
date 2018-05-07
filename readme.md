Simple example of using [centrifuge-mobile](https://github.com/centrifugal/centrifuge-mobile) library for Android development.

You need to download library build for Android from [releases page](https://github.com/centrifugal/centrifuge-mobile/releases).

In archive you will find `centrifuge.aar` file that is library built for all Android architectures. To use it with Android Studio project:

File → New → New Module → Import .JAR/.AAR package → write path to `centrifuge.aar` and click `Finish` button

Then:

File → Project Structure → app → Dependencies → Add Module Dependency → add `centrifuge` to dependency list

Look at example in this repo to get basic understanding of how to use library. Note that should add special permission for app in `AndroidManifest.xml` file:

```
<uses-permission android:name="android.permission.INTERNET" />
```

Don't forget to run Centrifuge-based server (or Centrifugo). And also change connection address to yours accordingly.

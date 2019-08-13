## Android [Compose](https://developer.android.com/jetpack/compose)

Compose is a very interesting *new* UI framework by the Android team. It's still pre-alpha and unpublished. The best way to use it is to follow the instructions [here](https://android.googlesource.com/platform/frameworks/support/+/refs/heads/androidx-master-dev/ui/README.md#getting-started).

Extra steps to get this patch running:

1. `git checkout 86cadecb18` This patch was built on commit id `86cadecb18`. It may work in other commits.
2. Inside `androidx-master-dev/frameworks/support/ui` run: `git apply  < $ANDROID_FRAMEWORK_BENCHMARK_PATH/frameworks/compose/compose.patch`
3. Open Android Studio with `./studiow`
4. Find the `ui-material/integration-tests/material-studies` project in Android Studio and run it.


elevant files are in `ui-material/integration-tests/material-studies/src/main/java/androidx/ui/material/studies/table`

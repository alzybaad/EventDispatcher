# EventDispatcher

Android has many events. And some of them require boilerplate code, conditional statement by id, type or code...
EventDispatcher generate such boilerplate code instead of you.

**100% reflection-free.**

## Supported Events

* `OnActivityResult`
* `OnOptionsItemSelected`

## Usage

### `OnActivityResult`

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        MainActivityEventDispatcher.onActivityResult(this, requestCode, resultCode, data);
    }

    @OnActivityResult(requestCode = 1, resultCode = RESULT_OK)
    void onActivity1ResultOk(Intent data) {
        // called on requestCode = 1 and resultCode = RESULT_OK
    }

    @OnActivityResult(requestCode = 2, resultCode = RESULT_OK)
    void onActivity2ResultOk(Intent data) {
        // called on requestCode = 2 and resultCode = RESULT_OK
    }

    @OnActivityResult(requestCode = { 1, 2 }, resultCode = RESULT_CANCELED)
    void onActivity12ResultCanceled() {
        // called on requestCode = 1 or 2 and resultCode = RESULT_CANCELED
    }

    @OnActivityResult(requestCode = 3)
    void onActivityResult(int resultCode) {
        // called on requestCode = 3 (resultCode does not matter)
    }
}
```

You write handler methods annotated with `@OnActivityResult`.
`@OnActivityResult` has 2 arguments, requestCode and resultCode.
Your handler can have 3 arguments, `(int requestCode, int resultCode, Intent data)` or its sublist.
If your handler has 1 `int` argument , it's a `resultCode`, not a `requestCode`.
Finally, you must call the `XXXEventDispatcher#onActivityResult()` (generated method)  in your `OnActivityResult`.
`XXX` is your class name.

### `OnOptionsItemSelected`

```java
public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return MainActivityEventDispatcher.onOptionsItemSelected(this, item) || super.onOptionsItemSelected(item);
    }

    @OnOptionsItemSelected(android.R.id.home)
    boolean onHomeSelected() {
        finish();
        return true;
    }
}
```

## Download

Your **project** `build.gradle`.
```groovy
buildscript {
  dependencies {
    classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
  }
}
```

Your **app module** `build.gradle`.
```groovy
repositories {
    maven { url 'http://team-birdhead.github.io/maven' }
}

dependencies {
    compile 'team.birdhead.eventdispatcher:eventdispatcher:1.0.0'
    apt 'team.birdhead.eventdispatcher:eventdispatcher-processor:1.0.0'
}
```

## License

    Copyright 2017 alzybaad

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

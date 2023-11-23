# react-native-kiosk-view

A simple kiosk mode for Android in react native.

Does not work with Expo because of its native dependencies.

## Installation

```sh
npm install react-native-kiosk-view
```

## Android Changes

In `MainActivity.java` add the following code:

```java
//...
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.kioskview.Immersive;

public class MainActivity extends ReactActivity {
  //...
  @Override
  public void onBackPressed() {
		if( !Immersive.getActive() ) super.onBackPressed();
		else Immersive.fullscreen(getWindow(), MainActivity.this);
  }

  @Override
  protected void onPause() {
		super.onPause();
		if(Immersive.getActive()) Immersive.moveToFront(getTaskId(),(ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
  }

  @Override
  protected void onResume() {
		super.onResume();
		if(Immersive.getActive()) Immersive.fullscreen(getWindow(), MainActivity.this);
  }

  @Override
  protected void onUserLeaveHint() {
		super.onUserLeaveHint();
		if(Immersive.getActive()) {
			Immersive.moveToFront(getTaskId(), (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
			Immersive.fullscreen(getWindow(), MainActivity.this);
		}
  }
}
```

Add below permissions in `AndroidManifest.xml:`

```xml
<uses-permission android:name="android.permission.REORDER_TASKS" />
<uses-permission android:name="android.permission.BROADCAST_CLOSE_SYSTEM_DIALOGS" />
```

The `<intent-filter>` should be atleast:

```xml
<intent-filter>
  <action android:name="android.intent.action.MAIN" />
  <category android:name="android.intent.category.HOME" />
  <category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
```

## Usage

```ts
import * as React from 'react';
import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';
import Kiosk from 'react-native-kiosk-view';

export default function App() {
  const handleFullScreen = () => Kiosk.fullscreen();

  const handleExitFullScreen = () => Kiosk.exitFullscreen();

  return (
    <View style={styles.container}>
      <TouchableOpacity onPress={handleFullScreen}>
        <Text>Enter Kiosk Mode{'\n'}</Text>
      </TouchableOpacity>

      <TouchableOpacity onPress={handleExitFullScreen}>
        <Text>Exit Kiosk Mode</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)

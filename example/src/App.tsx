import * as React from 'react';

import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';
import Kiosk from 'react-native-kiosk-view';

export default function App() {
  return (
    <View style={styles.container}>
      <TouchableOpacity onPress={() => Kiosk.fullscreen()}>
        <Text>Enter Kiosk Mode{'\n'}</Text>
      </TouchableOpacity>

      <TouchableOpacity onPress={() => Kiosk.exitFullscreen()}>
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
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});

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

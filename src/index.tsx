import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-kiosk-view' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const KioskView = NativeModules.KioskView
  ? NativeModules.KioskView
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

const fullscreen = () => KioskView.fullscreen();

const exitFullscreen = () => KioskView.exitFullscreen();

const moveToFront = () => KioskView.moveToFront();

const Kiosk = { fullscreen, exitFullscreen, moveToFront };

export default Kiosk;

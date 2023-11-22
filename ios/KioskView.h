
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNKioskViewSpec.h"

@interface KioskView : NSObject <NativeKioskViewSpec>
#else
#import <React/RCTBridgeModule.h>

@interface KioskView : NSObject <RCTBridgeModule>
#endif

@end

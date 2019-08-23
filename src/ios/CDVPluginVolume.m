#import "CDVPluginVolume.h"

#import <Cordova/CDVAvailability.h>

@implementation CDVPluginVolume

- (void)pluginInitialize {
}

- (void)test:(CDVInvokedUrlCommand *)command {
    CDVPluginResult *result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:"Test function"];
    [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
}

@end

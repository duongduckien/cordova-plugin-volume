
import CoreLocation

@objc(volume) class volume : CDVPlugin {


    override func pluginInitialize() {

    }


    // Test function
    @objc(test:)
    func test(command: CDVInvokedUrlCommand) {

        var pluginResult = CDVPluginResult(
            status: CDVCommandStatus_OK,
            messageAs: "Test function"
        )

        self.commandDelegate!.send(
            pluginResult,
            callbackId: command.callbackId
        )

    }

}

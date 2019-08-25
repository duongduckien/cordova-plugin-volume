
import CoreLocation
import AVFoundation
import MediaPlayer

@objc(CDVPluginVolume) class CDVPluginVolume : CDVPlugin {

    override func pluginInitialize() {

    }

    @objc(getVolume:)
    func getVolume(command: CDVInvokedUrlCommand) {
        
        let vol = AVAudioSession.sharedInstance().outputVolume
        let pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: Double(vol))

        self.commandDelegate!.send(
            pluginResult,
            callbackId: command.callbackId
        )

    }
    
    @objc(setVolume:)
    func setVolume(command: CDVInvokedUrlCommand) {
        
        let inputVol = (command.arguments[0] as? NSNumber)?.floatValue ?? 0

        let volumeView = MPVolumeView(frame: .zero)
        let slider = volumeView.subviews.first(where: { $0 is UISlider }) as? UISlider

        DispatchQueue.main.asyncAfter(deadline: DispatchTime.now() + 0.01) {
            slider?.value = inputVol
        }
        
        let pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: true)
        
        self.commandDelegate!.send(
            pluginResult,
            callbackId: command.callbackId
        )
        
    }

}

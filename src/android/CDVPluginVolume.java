package CDVPluginVolume;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.media.*;

public class CDVPluginVolume extends CordovaPlugin {

	public static final String SET_VOLUME = "setVolume";
	public static final String GET_VOLUME = "getVolume";

	private Context context;
	private AudioManager manager;

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

		context = cordova.getActivity().getApplicationContext();
		manager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

		if (SET_VOLUME.equals(action)) {

			try {

				int volumeToSet = (int) Math.round(args.getDouble(0) * 100.0f);
				int volume = getVolumeToSet(volumeToSet);
				boolean playSound;

				if (args.length() > 1 && !args.isNull(1)) {
					playSound = args.getBoolean(1);
				} else {
					playSound = true;
				}

				manager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, (playSound ? AudioManager.FLAG_PLAY_SOUND : 0));
				callbackContext.success();

                return true;

			} catch (Exception e) {
				return false;
			}

		} else if(GET_VOLUME.equals(action)) {

			try {

				int vol = getCurrentVolume();
				float currVol = vol / 100.0f;
				String strVol= String.valueOf(currVol);
				callbackContext.success(strVol);

                return true;

			} catch (Exception e) {
				return false;
			}

		}

        return false;

	}

	private int getVolumeToSet(int percent) {

		try {

			int vol;
			int maxVolume = manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
			vol = Math.round((percent * maxVolume) / 100);

			return vol;

		} catch (Exception e){
			return 1;
		}

	}

	private int getCurrentVolume() {

		try {

			int vol;
			int maxVolume = manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
			int currVolume = manager.getStreamVolume(AudioManager.STREAM_MUSIC);
			vol = Math.round((currVolume * 100) / maxVolume);

			return vol;

		} catch (Exception e) {
			return 1;
		}

	}

}

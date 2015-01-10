package io.firstwave.gdxkit.android;

import android.content.Context;
import io.firstwave.gdxkit.platform.Platform;
import io.firstwave.gdxkit.platform.PlatformDelegate;

/**
 * Created by obartley on 1/9/15.
 */
public class AndroidDelegate implements PlatformDelegate {

	private final Context mContext;

	public AndroidDelegate(Context context) {
		mContext = context;
	}

	@Override
	public final Platform getPlatformType() {
		return Platform.ANDROID;
	}
}

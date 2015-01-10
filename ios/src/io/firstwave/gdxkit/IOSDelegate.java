package io.firstwave.gdxkit;

import io.firstwave.gdxkit.platform.Platform;
import io.firstwave.gdxkit.platform.PlatformDelegate;

/**
 * Created by obartley on 1/9/15.
 */
public class IOSDelegate implements PlatformDelegate {
	@Override
	public Platform getPlatformType() {
		return Platform.IOS;
	}
}

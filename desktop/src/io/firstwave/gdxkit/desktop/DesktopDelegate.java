package io.firstwave.gdxkit.desktop;

import io.firstwave.gdxkit.platform.Platform;
import io.firstwave.gdxkit.platform.PlatformDelegate;

/**
 * Created by obartley on 1/9/15.
 */
public class DesktopDelegate implements PlatformDelegate {
	@Override
	public Platform getPlatformType() {
		return Platform.DESKTOP;
	}
}

package com.glowiak.librlimg;

import com.raylib.Raylib;

public class FontLoader
{
	// should be enough for the font not to be blurry
	// it's a public field, so adjust if you need
	public static final int FONTLOAD_BASESIZE = 72;
	
	// the charset to load. 0 means every characters in the font
	// adjust if you need
	public static final int LOAD_CHARSET = 0;
	
	public static final Raylib.Font getFontFromJar(String path)
	{
		byte[] buffer = ImageLoader.rl_LoadFileDataFromIS(
			MusicLoader.class.getResourceAsStream(path),
			true
		);
		
		return Raylib.LoadFontFromMemory(
			ImageLoader.getFileType(path),
			buffer,
			buffer.length,
			FONTLOAD_BASESIZE,
			null,
			LOAD_CHARSET
		);
	}
}

package com.glowiak.librlimg;

import com.raylib.Raylib;

public class MusicLoader
{
	public static final Raylib.Sound getSoundFromJar(String path)
	{
		byte[] buffer = ImageLoader.rl_LoadFileDataFromIS(
			MusicLoader.class.getResourceAsStream(path),
			true
		);
		
		Raylib.Wave wav = Raylib.LoadWaveFromMemory(
			ImageLoader.getFileType(path),
			buffer,
			buffer.length
		);
		
		Raylib.Sound s = Raylib.LoadSoundFromWave(wav);
		
		Raylib.UnloadWave(wav);
		
		return s;
	}
}

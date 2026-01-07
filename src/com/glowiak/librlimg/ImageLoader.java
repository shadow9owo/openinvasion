package com.glowiak.librlimg;

import com.raylib.Raylib;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class ImageLoader
{
	private static final boolean DEBUG = false;
	
	public static final Raylib.Texture getTextureFromJar(String path)
	{
		return Raylib.LoadTextureFromImage(rl_LoadImage(path));
	}
	public static final Raylib.Image getImageFromJar(String path)
	{
		return rl_LoadImage(path);
	}
	public static final Raylib.Image getImageFromInputStream(InputStream is, String extension)
	{
		byte[] fileData = rl_LoadFileDataFromIS(is, false);
		
		return Raylib.LoadImageFromMemory(extension, fileData, fileData.length);
	}
	public static final Raylib.Texture getTextureFromInputStream(InputStream is, String extension)
	{
		return Raylib.LoadTextureFromImage(getImageFromInputStream(is, extension));
	}
	public static final Raylib.Image rl_LoadImage(String path)
	{
		byte[] fileData = rl_LoadFileData(path);
		
		return Raylib.LoadImageFromMemory(getFileType(path), fileData, fileData.length);
	}
	public static final byte[] rl_LoadFileData(String path)
	{
		return rl_LoadFileDataFromIS(ImageLoader.class.getResourceAsStream(path), true);
	}
	protected static final byte[] rl_LoadFileDataFromIS(InputStream is, boolean close_is)
	{
		byte[] data = null;
		
		try {
		
			int size = is.available();
			if (DEBUG)
				System.out.println("initial size: " + size);
		
			if (size > 0)
			{
				data = new byte[size];
				
				BufferedInputStream bis = new BufferedInputStream(is);
				
				if (bis.read(data, 0, size) == 0)
				{
					if (DEBUG)
						System.out.println("buffer read error");
					bis.close();
					return null;
				}
				
				bis.close();
				if (close_is)
					is.close();
				
				return data;
			}
			
			if (close_is)
				is.close();
		
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		if (DEBUG)
			System.out.println("final null");
		return null;
	}
	protected static final String getFileType(String f)
	{
		return f.substring(f.lastIndexOf("."), f.length());
	}
}

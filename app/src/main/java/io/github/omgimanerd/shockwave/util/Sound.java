package io.github.omgimanerd.shockwave.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

/**
 * Created by omgimanerd on 1/26/15.
 */
public class Sound {

  private static SoundPool soundPool_;
  private static HashMap sounds_;

  @SuppressWarnings("deprecated")
  public static void loadSounds(Context context) {
    soundPool_ = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
    sounds_ = new HashMap();
  }

  public static void play(String soundName) {
    if (sounds_.containsKey(soundName )) {
      soundPool_.play((Integer) sounds_.get(soundName), 0, 1, 1, 0, 1);
    } else {
      throw new Error("Something bad happened, sound does not exist :/");
    }
  }

  public static void release() {
    soundPool_.release();
    soundPool_ = null;
  }
}
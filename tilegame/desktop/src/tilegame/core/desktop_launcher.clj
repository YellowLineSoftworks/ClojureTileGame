(ns tilegame.core.desktop-launcher
  (:require [tilegame.core :refer :all])
  (:import [com.badlogic.gdx.backends.lwjgl LwjglApplication]
           [org.lwjgl.input Keyboard])
  (:gen-class))

(defn -main
  []
  (LwjglApplication. tilegame-game "tilegame" screensz screensz)
  (Keyboard/enableRepeatEvents true))

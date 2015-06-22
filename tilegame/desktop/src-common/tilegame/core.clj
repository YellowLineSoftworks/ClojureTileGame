(ns tilegame.core
  (:require [play-clj.core :refer :all]
            [play-clj.g2d :refer :all]))

(defscreen main-screen
  :on-show
  (fn [screen entities]
    (update! screen :renderer (stage))
    (let [tile (assoc (texture "tile.png") :x 500 :y 24 :width 50 :height 50)]
    [tile]))
  
  :on-render
  (fn [screen entities]
    (clear!)
    (render! screen entities)))

(defgame tilegame-game
  :on-create
  (fn [this]
    (set-screen! this main-screen)))

(defn- reset [] (on-gl (set-screen! tilegame-game main-screen)))

(ns tilegame.core
  (:require [play-clj.core :refer :all]
            [play-clj.g2d :refer :all]))

(declare generate-tile-x generate-tile-y)

(def screensz 600)
(def board-size 3)
(def tile-size (/ screensz board-size))

(defn- generate-blank-tile 
  "Generates a blank tile for the upper right hand corner"
  [] 
  (assoc (texture "blank_tile.png") 
         :x (* (- board-size 1) tile-size), :y (* (- board-size 1) tile-size),
         :width tile-size, :height tile-size,
         :location (- (* board-size board-size) 1), :blanktile? true))

(defn- generate-tile 
  "Generates a tile of arbitrary index"
  [index] 
  (assoc (texture "tile.png") 
         :x (generate-tile-x index), :y (generate-tile-y index), 
         :width tile-size, :height tile-size,
         :location (index), :blanktile? false))

(defn- generate-tile-x 
  "Generates the x cord of a tile of arbitrary index"
  [index]
  (+ 0 (* (mod index board-size) tile-size)))

(defn- generate-tile-y
  "Generates the y cord of a tile of arbitrary index"
  [index]
  (+ 0 (* (Math/floor (/ index board-size)) tile-size)))


(defn- build-tile-board
  "Creates a vector of tiles maps to use in game"
  []
  (loop [tile-vector [] index 0]
    (if (= index (- (* board-size board-size) 1))
      (conj tile-vector (generate-blank-tile))
      (recur (conj tile-vector (generate-tile index)) (inc index)))))
 

(defscreen main-screen
  :on-show
  (fn [screen entities]
    (update! screen :renderer (stage))
    (let [board (build-tile-board)]
      board))
  
  :on-render
  (fn [screen entities]
    (clear!)
    (render! screen entities)))

(defgame tilegame-game
  :on-create
  (fn [this]
    (set-screen! this main-screen)))

(defn- reset [] (on-gl (set-screen! tilegame-game main-screen)))

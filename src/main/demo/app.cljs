(ns demo.app
  (:require
    [reagent.core :as r]
    ;; don't need the :refer or :rename
    ;; could just use :as router and then [:> router/Link ...]
    ;; just wanted to match the JS example
    ["react-router-dom" :refer (Route Link) :rename {BrowserRouter Router}]))

(defn index []
  [:h2 "Home"])

(defn users []
  [:h2 "Users"])

(defn about []
  [:h2 "About"])

;; react-router wants react component classes
(def Index (r/reactify-component index))
(def Users (r/reactify-component users))
(def About (r/reactify-component about))

(defn root []
  [:> Router
   [:div
    [:nav
     [:ul
      [:li
       [:> Link {:to "/"} "Home"]]
      [:li
       [:> Link {:to "/about/"} "About"]]
      [:li
       [:> Link {:to "/users/"} "Users"]]]]
    [:> Route {:path "/" :exact true :component Index}]
    [:> Route {:path "/about/" :component About}]
    [:> Route {:path "/users/" :component Users}]
    ]])

(defn ^:dev/after-load start []
  (r/render [root] (js/document.getElementById "app")))

(defn init []
  (start))
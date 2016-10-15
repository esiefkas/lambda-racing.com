(ns lambda-racing.core
  (:require [goog.events :as events]
            [goog.history.EventType :as EventType]
            [reagent.core :refer [atom] :as reagent]
            [cljs.reader :as cljsr]
            [secretary.core :as secretary :refer-macros [defroute]]
            [reagent.session :as session]
            [lambda-racing.pages.splash :refer [splash-page]])
  (:import goog.History))

(enable-console-print!)

(secretary/set-config! :prefix "#")

(defroute "/" []
  (session/put! :current-page [splash-page]))

(defn current-page
  []
  (fn []
    [:div.page-container
     (session/get :current-page)]))

;; History
;; must be called after routes have been defined
(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen EventType/NAVIGATE
                   (fn [event] (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(hook-browser-navigation!)

(reagent/render [current-page] (.getElementById js/document "app"))

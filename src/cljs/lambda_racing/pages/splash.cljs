(ns lambda-racing.pages.splash
  (:require [reagent.core :as r]))

(defn nav-dropdown []
  (fn []
    [:li.dropdown
     [:a {:href "#"
          :class "dropdown-toggle"
          :data-toggle "dropdown"
          :role "button"
          :aria-haspopup "true"
          :aria-expanded "false"} [:span.caret] "Dropdown"]
     [:ul.dropdown-menu
      [:li [:a {:href "#"} "Thing1"]]
      [:li [:a {:href "#"} "Thing2"]]
      [:li [:a {:href "#"} "Thing3"]]
      [:li.divider {:role "separator"}]
      [:li.dropdown-header "Nav Header"]
      [:li [:a {:href "#"} "SeperateThing1"]]
      [:li [:a {:href "#"} "SeperateThing2"]]]]))

(defn navbar []
  (fn []
    [:div.navbar-wrapper
     [:div.container
      [:nav.navbar.navbar-inverse.navbar-static-top
       [:div.container
        [:div.navbar-header
         [:button {:type "button"
                   :class "navbar-toggle collapsed"
                   :data-toggle "collapse"
                   :data-target "#navbar"
                   :aria-expanded "false"
                   :aria-controls "navbar"}
          [:span {:class "sr-only"} "Toggle Navigation"]
          [:span {:class "icon-bar"}]
          [:span {:class "icon-bar"}]
          [:span {:class "icon-bar"}]]
         [:a.navbar-brand {:href "#"} "Lambda Racing"]]
        [:div {:id "navbar"
               :class "navbar-collapse collapse"}
         [:ul.nav.navbar-nav
          [:li.active
           [:a {:href "#"} "Home"]]
          [:li
           [:a {:href "#"} "About"]]
          [:li
           [:a {:href "#"} "Contact"]]]]]]]]))

(defn splash-page []
  (fn []
    [navbar]))

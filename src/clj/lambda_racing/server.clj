(ns lambda-racing.server
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.core :as hiccup]
            [hiccup.page :refer [include-js]]
            [ring.adapter.jetty :as jetty]
            [config.core :refer [env]])
  (:gen-class))

;;;; Base HTML pages

(def main-page
  [:html
   [:head
    [:title "Lambda Racing"]]
   [:body
    [:div#app]
    (include-js "js/main.js")
    (include-js "https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js")
    [:link {:rel "stylesheet"
            :href "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
            :integrity "sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
            :crossorigin "anonymous"}]

    [:link {:rel "stylesheet"
            :href"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
            :integrity "sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
            :crossorigin "anonymous"}]

    [:script {:src "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
              :integrity "sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
              :crossorigin "anonymous"}]]])

(def not-found-page
  [:html
   [:head
    [:title "404 Not Found"]]
   [:body
    [:h1 "Page not found"]]])

;;;; Handlers and middleware

(defroutes app
  (GET "/" [] (hiccup/html main-page))
  (route/resources "/")
  (route/not-found (hiccup/html not-found-page)))

(def site (handler/site app))

;;;; Managing the server in the REPL or from 'lein run'

(defonce ^:dynamic server nil)

(defn stop
  []
  (when server
    (.stop server)))

(defn start
  [& [port]]
  (stop)
  (alter-var-root
    #'server
    (constantly
      (jetty/run-jetty #'site
                       {:port (long (or port 3000))
                        :join? false}))))

(defn -main
  [& [port]]
  (start (Integer/parseInt (or (env :port) "3000"))))

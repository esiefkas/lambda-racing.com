(defproject lambda-racing "0.1.0-SNAPSHOT"
  :description "Minimal webapp using ClojureScript, Compojure, and Reagent"
  :url "https://github.com/esiefkas/lambda-racing.com"
  :license {:name "MIT License"
            :url "http://www.opensource.org/licenses/mit-license.php"}

  :dependencies [;; Language
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.93"]

                 ;; Server
                 [compojure "1.5.1"]
                 [hiccup "1.0.5"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [yogthos/config "0.8"]

                 ;; Client
                 [reagent "0.5.1"]
                 [reagent-utils "0.2.0"]
                 [secretary "1.2.3"]

                 ;; Emacs integration
                 [com.cemerick/piggieback "0.2.1"]
                 [figwheel-sidecar "0.5.4-7"]]

  :plugins [[lein-cljsbuild "1.1.3"]
            [lein-figwheel "0.5.4-7"]]

  :source-paths ["src/clj"]

  :cljsbuild {:builds [{:id "main"
                        :source-paths ["src/cljs"]
                        :figwheel true
                        :compiler {:main "lambda-racing.core"
                                   :output-to "resources/public/js/main.js"
                                   :output-dir "resources/public/js/out"
                                   :asset-path "js/out"}}]}
  :figwheel {:ring-handler lambda-racing.server/site}

  :clean-targets ^{:protect false} ["resources/public" "target"]

  :uberjar-name "lambda-racing.jar"
  :profiles {:uberjar {:aot :all
                       :main lambda-racing.server
                       :hooks [leiningen.cljsbuild]
                       :prep-tasks ["compile" ["cljsbuild" "once" "main"]]}}
  :main lambda-racing.server)

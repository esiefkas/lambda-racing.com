(ns figwheel.connect (:require [figwheel.client] [lambda-racing.core] [figwheel.client.utils]))
(figwheel.client/start {:build-id "main", :websocket-url "ws://localhost:3449/figwheel-ws"})


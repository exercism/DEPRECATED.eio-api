(ns exercism-api.core
  (:require [org.httpkit.server :as http-kit]
            [exercism-api.handler :refer [app]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.json :refer [wrap-json-response]])
  (:gen-class))

(defn dev? [args] (some #{"-dev"} args))

(defn port [args]
  (if-let [port (first (remove #{"-dev"} args))]
    (Integer/parseInt port)
    9000))

(defn -main [& args]
  (http-kit/run-server
   (-> app
       wrap-json-response
       wrap-reload)
   {:port (port args)})
  (println "server started"))

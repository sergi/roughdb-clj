(ns roughdb-clj.core
  (:gen-class)
  (:require [org.httpkit.server :as server]))

(require 'roughdb-clj.lib)
(refer 'roughdb-clj.lib)

(defn baby-small-app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (key2path "sergi")})

(defn -main
  "This is our app's entry point"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8080"))] ;(5)
    (server/run-server #'baby-small-app {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))

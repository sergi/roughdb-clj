(ns roughdb-clj.lib
  (:import (java.security MessageDigest)
           (java.util Base64)
           (java.math BigInteger)))

(defn md5 [^String s]
  (let [algorithm (MessageDigest/getInstance "MD5")
        raw (.digest algorithm (.getBytes s))]
    (format "%032x" (BigInteger. 1 raw))))

(defn key2path [key]
  (let [encode #(.encodeToString (Base64/getEncoder) (.getBytes %))
        hash (md5 key)
        b64data (encode key)]
    (format "/%s/%s/%s" (subs hash 0 2) (subs hash 2 4) b64data)))

(defn key2volume [key volumes]
  (second
   (reduce (fn [acc volume]
             (let [score (md5 (str key volume))
                   prevScore (first acc)]
               (if  (< (compare prevScore score) 0) [score volume] acc)))
           ["" (first volumes)] volumes)))

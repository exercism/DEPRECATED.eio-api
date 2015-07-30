(ns exercism-api.db.models
  (:require [korma.db :refer :all]
            [korma.core :refer :all]
            [environ.core :refer [env]]))

;; Obviously, change all this to environ before prod
(defdb exercism-dev
  (postgres {:db "exercism"
             :user "exercism"
             :password "exercism"
             :host "localhost"
             :port "5432"}))

(declare users submissions comments)

(defentity users
  (has-many comments {:fk :user_id})
  (has-many submissions {:fk :user_id})
  ;; Field :username returns a PGObject, so we need to coerce to String.
  (transform (fn [user]
               (let [username (str (:username user))]
                 (assoc user :username username)))))

(defentity submissions
  (has-many comments {:fk :submission_id})
  (belongs-to users {:fk :user_id}))

(defentity comments
  (belongs-to users {:fk :user_id})
  (belongs-to submissions {:fk :submission_id}))

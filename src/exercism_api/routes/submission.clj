(ns exercism-api.routes.submission
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [cheshire.core :as c]
            [schema.core :as s]
            [exercism-api.schema :refer :all]))

(s/defschema Dummy {:message s/Str})

(defn dummy []
  (ok (c/generate-string {:message "ok"})))

(defroutes* submission-routes
  ;; Exercise routes
  ;; Origin: api/routes/exercises.rb
  (GET* "/exercises" []
        :return [Exercise]
        :summary "Grab the next exercises"
        :query-params [key :- String]
        (dummy))
  (GET* "/exercises/:track-id" [track-id]
        :return [Exercise]
        :summary "Fetch exercises for a track"
        :query-params [key :- String]
        (dummy))
  (GET* "exercises/:track-id/:slug" [track-id slug]
        :return Exercise
        :summary "Fetch a specific exercise"
        :query-params [key :- String]
        (dummy))

  ;; Problem routes
  ;; Origin api/routes/problems.rb
  (GET* "/problems" []
        :return [Problem]
        :summary "Problem listing"
        :query-params [key :- String]
        (dummy))

  ;; Submission routes
  ;; Origin api/routes/submissions.rb
  (GET* "/submissions/:language/:slug" [language slug]
        :return [Submission]
        :summary "Individual problem submissions"
        :query-params [key :- String]
        (dummy))

  (GET* "/submissions/:key" [key]
        :return Submission
        :summary "Individual submission, by key"
        (dummy)))

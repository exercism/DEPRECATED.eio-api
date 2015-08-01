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
  (GET* "/exercises/:track-id" []
        :return [Exercise]
        :summary "Fetch exercises for a track"
        :path-params  [track-id :- String]
        :query-params [key :- String]
        (dummy))
  (GET* "/exercises/:track-id/:slug" []
        :return Exercise
        :summary "Fetch a specific exercise"
        :path-params [track-id :- String, :slug :- String]
        :query-params [key :- String]
        (dummy))

  ;; Iterations
  (GET* "/iterations/:key/restore" []
        :return [Submission]
        :path-params [key :- String]
        :summary "Restore user submissions from server"
        (dummy))

  (POST* "/iterations/:language/:slug/skip" [language, slug]
         :return Long
         :summary "Skip problem"
         :query-params [key :- String]
         (no-content))

  (POST* "/user/assignments" []
         :return Long
         :summary "Post assignment"
         :body-params [key :- String,
                       solution :- String,
                       language :- String
                       problem :- String]
         (no-content))

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

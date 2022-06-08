(ns lein-git-revisions.plugin
  (:require [clojure.java.shell :as sh]
            [git-revisions.core :as r]))

(defn middleware
  ; TODO: some minimal default config
  [{:keys           [git-revisions root]
    :as             project}]
  (sh/with-sh-dir root
    (let [{:keys [format adjust revision-file]} git-revisions]
  (-> project
      (assoc :version (r/revision-generator format adjust (when (some? revision-file)
                                                            {:output-path  revision-file
                                                             :project-root (:root project)})))))))

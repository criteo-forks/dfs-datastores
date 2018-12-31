(def ROOT-DIR (subs *file* 0 (- (count *file*) (count "project.clj"))))
(def VERSION (-> ROOT-DIR (str "/../VERSION") slurp))

(defproject com.backtype/dfs-datastores VERSION
  :description "Dead-simple vertical partitioning, compression, appends, and consolidation of data on a distributed filesystem."
  :url "https://github.com/nathanmarz/dfs-datastores"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.slf4j/slf4j-api "1.6.6"]
                 [jvyaml/jvyaml "1.0.0"]
                 [com.google.guava/guava "13.0"]
                 [junit/junit "4.12" :scope "test"]
                 [com.backtype/dfs-datastores-util ~VERSION]
                 ]
  :deploy-repositories {"releases" {:url "https://nexus.criteo.prod/content/repositories/criteo.releases/"
                                    :sign-releases false}
                        "snapshots" {:url "https://oss.sonatype.org/content/repositories/snapshots"}}
  :scm {:connection "scm:git:git://github.com/nathanmarz/dfs-datastores.git"
        :developerConnection "scm:git:ssh://git@github.com/nathanmarz/dfs-datastores.git"
        :url "https://github.com/nathanmarz/dfs-datastores"
        :dir ".."}
  :pom-addition [:developers
                 [:developer
                  [:name "Nathan Marz"]
                  [:url "http://twitter.com/nathanmarz"]]
                 [:developer
                  [:name "Soren Macbeth"]
                  [:url "http://twitter.com/sorenmacbeth"]]
                 [:developer
                  [:name "Sam Ritchie"]
                  [:url "http://twitter.com/sritchie"]]]
  :source-paths ["src/main/java"]
  :test-paths ["src/test/java"]
  :javac-options ["-source" "1.8" "-target" "1.8"]
  :java-source-paths ["src/main/java" "src/test/java"]
  :junit ["src/test/java"]
  :profiles {:dev
             {:dependencies [
                             [org.slf4j/slf4j-log4j12 "1.6.6"]
                             [junit/junit "4.12"]
                             ]
              :plugins [[lein-junit "1.1.8"]]}
             :test
             {:dependencies [
                             [org.slf4j/slf4j-log4j12 "1.6.6"]
                             ]
              :plugins [[lein-junit "1.1.8"]]}
             :provided
             {:dependencies [
                             [org.apache.hadoop/hadoop-mapreduce-client-core "2.7.0"]
                             [org.apache.hadoop/hadoop-mapreduce-client-common "2.7.0"]
                             [org.apache.hadoop/hadoop-hdfs "2.7.0"]
                             [org.apache.hadoop/hadoop-common "2.7.0"]
                             ]}}
  :classifiers {:javadoc {:java-source-paths ^:replace []
                          :source-paths ^:replace []
                          :resource-paths ^:replace []}
                :sources {:java-source-paths ^:replace []
                          :resource-paths ^:replace []}
                :test {:java-source-paths ^:replace ["test"]
                       :resource-paths ^:replace ["test"]}
                })

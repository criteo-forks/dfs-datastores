(def ROOT-DIR (subs *file* 0 (- (count *file*) (count "project.clj"))))
(def VERSION (-> ROOT-DIR (str "/../VERSION") slurp))

(defproject com.backtype/dfs-datastores-cascading VERSION
  :description "Dead-simple vertical partitioning, compression, appends, and consolidation of data on a distributed filesystem."
  :url "https://github.com/nathanmarz/dfs-datastores"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[com.backtype/dfs-datastores ~VERSION]
                 [org.slf4j/slf4j-log4j12 "1.6.6"]
                 [cascading/cascading-hadoop "2.5.3"
                  :exclusions [org.apache.hadoop/hadoop-core]]
                 [junit/junit "4.12" :scope "test"]
                 [com.backtype/dfs-datastores-util ~VERSION :scope "test"]
                 [com.backtype/dfs-datastores ~VERSION :scope "test" :classifier "test"]
                 ]
  :repositories {"conjars" "http://conjars.org/repo"}
  :deploy-repositories {"releases" {:url "https://oss.sonatype.org/service/local/staging/deploy/maven2"
                                    :creds :gpg}
                        "snapshots" {:url "https://oss.sonatype.org/content/repositories/snapshots"
                                     :creds :gpg}}
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
  :javac-options ["-source" "1.7" "-target" "1.7"]
  :java-source-paths ["src/main/java" "src/test/java"]
  :junit ["src/test/java"]
  :profiles {:dev
             {:plugins [[lein-junit "1.1.8"]]}
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
                          :resource-paths ^:replace []}})

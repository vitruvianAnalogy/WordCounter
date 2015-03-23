#echo ${CLASSPATH}
#export CLASSPATH=$CLASSPATH:WordCounter/
javac insight/Folder.java insight/util/WordCounter.java
java insight.util.WordCounter ../wc_input ../wc_output

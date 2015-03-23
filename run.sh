#javac WordCounter/src/insight/Folder.java
echo ${CLASSPATH}
export CLASSPATH=src/insight

javac src/insight/Folder.java src/insight/util/WordCounter.java
java src/insight/util/WordCounter wc_input wc_output

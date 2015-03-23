git clone git@github.com:vitruvianAnalogy/WordCounter.git ./home/<Username>/
cd ./home/<Username>/WordCounter

javac ./home/<username>/WordCounter/src/insight/Folder.java
echo ${CLASSPATH}
export CLASSPATH=/home/<username>/Insight/src/insight

javac ./home/<username>/WordCounter/src/insight/util/WordCounter.java
java ./home/<username>/WordCounter/src/insight/util/WordCounter 

.home/<username>/WordCounter/wc_input .home/<username>/WordCounter/wc_output
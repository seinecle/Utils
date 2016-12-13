#Utils classes (mainly for text mining and network analysis)
These are a couple of helper java classes I often use in my projects. They are written with a preference for speed of execution. Here are some that you might find most useful:  


###Clock.java
A simple way to measure the execution time of part of your code

###Lemmatizer.java
A quick way to lemmatize the most common forms of English terms (plurals -> singular). I started adding French rules as well, keep an eye on it.

###NGramFinder.java
Creates ngrams. The binary counting parameter just means: if true, then just one instance of a given ngram is returned.

###NGramDuplicatesCleaner.java
When your ngrams returned "United States of" and "United States of America", you need "United States of" to be removed. But when you find "hot commodity" and "hot", you probably want "hot" to remain in your set, if it is frequent enough in the corpus. This class deals with these issues.

###StopWords.java
A class which loads plain text files of stopwords, in different languages. Also stopwords for academic types of discourse, in English and French. The text files themselves are situated in this repo: https://github.com/seinecle/Stopwords

###StopwordsRemover.java
Removes stopwords in useful ways. For example; removes "of", "house of" because it contains "of", but does not remove "of" when contained in the ngram "United States of America". Fine tuned to work with domain specific lists of stopwords (named "scientific stopwords", but you can plug any other domain-specific list).

###FindAllPairs
USeful to count all co-occurrences in a set of elements. Not multithreaded but sill very optimized.

###License
Copyright 2016 Clement Levallois

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
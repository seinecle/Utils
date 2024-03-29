# Utils classes 
No dependencies. These are a couple of helper java classes I often use in my projects. They are written with a preference for speed of execution. Here are some that you might find most useful:  

## Installation

```xml
<dependency>
	<groupId>net.clementlevallois.utils</groupId>
	<artifcactId>utils-core</artifactId>
	<version>0.13</version>
</dependency>
```

Or [check on Maven](https://central.sonatype.com/artifact/net.clementlevallois.utils/utils-core) to see the latest version.


## Release
* 2023, April 13: version 0.13. Made the multiset concurrent with the internal map shifted to a ConcurrentHashMap.

* 2022, March 28: version 0.12. Added a method that removes repeated chars in a string but keeps accents and diacritics
* 2022, March 24: release as a Maven artifact is attempted with a new versioning starting at 0.11

### Clock.java
A simple way to measure the execution time of part of your code, not for benchmark but to read in your logs. Outputs to System.out

### Multiset
Convenience class replacing the Multiset by Guava (when I realized I imported Guava just for this class).

### FindAllPairs
USeful to count all co-occurrences in a set of elements. Not multithreaded but still very optimized.

### License
Copyright 2016-2023 Clement Levallois

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
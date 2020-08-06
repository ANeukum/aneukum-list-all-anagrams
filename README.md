# Build and run

This project was created using jdk 1.8 and maven 3.6.0.
If you have them on path you can run the program using the following commands:

    git clone https://github.com/ANeukum/aneukum-list-all-anagrams.git

    cd aneukum-list-all-anagrams

    mvn test

    cd target/classes

    java aneukum.bewerbung.lidl.Main
    
This should result in the required output.
If problems occur the project can be built and run like like any vanilla java project.
The only dependency is Junit 5.

# Design
While scanning the input file the chosen implementation puts each of its lines in a key-value map.
The keys are chosen such that two lines have the same key if an only if they are anagrams.
The values are lists containing all strings that are anagrams to each other. 
The result can then be obtained by printing all map values that have more than one element.
A reasonable design consist therefore of a class for the keys and a class containing the insert and print logic for the map.

The keys are obtained by counting the characters in each line. 
For example, the key of "ABCA" would contain the following information: 2xA, 1xB, 1xC.
Alternatively the key could be the sorted string.
While counting takes linear time and is therefore asymptotically faster than sorting the lines seem to be rather short. 
Therefore other effects will dominate the execution time.

As Java Strings are UTF-16 encoded it is necessary to choose methods that are aware of surrogate pairs.
Otherwise it is possible to construct false positives as can be seen in one of the test cases.

## Maintainability
AnagramKey and AnagramMap have single responsibilities and hide their implementation details. 
They can only be used in the way that is documented by tests.
Their names already tell that and how they are supposed to be used together.
Also, they are immutable which further narrows down their usage and thereby reduces code complexity.

The amount of tests and comments is rather low. 
This is by choice since they can inhibit maintainability if they become outdated.
If errors have severe consequences there have to be more tests.
On the other hand, if the code is likely to change fewer tests are appropriate.

The use of equals and hash code can be consfusing since the fact that HashMap uses them is not visible in the code.
At the same time the intent of these methods is clear if the maintainer has this knowledge.

## Performance
Since reads every word from the input file only once execution time is O(n) where n is the number of words.
Asymptotically this is optimal since every input word must be read.
The maximum word has limited impact if it is small compared to the number of words - which seems reasonable to assume.

The main data structure in AnagramMap is a Map<Map, List>.
This causes a certain overhead.
Using low level arrays such as String[] would most likely increase speed at the cost of maintainability.

Another thing to consider is that there can be hash collisions that impact the performance of HashMap.
The test case in AnagramKeyTest for utf-16 surrogate pairs contains a collision.

# Scaling

The space complexity is also O(n) where n is the number of words.
Since streams are used for reading and writing only a single copy of the input words is stored.

## 10 million words: 
It is reasonable to expect that the RAM of a modern computer is sufficient for input files with 10 million words.
A short test revealed that the current implementation takes a couple of minutes to process such a file.
The processor was the bottleneck therefore paralellization is an option.

The task at hand does not lend itself to full parallelization.
Whether a word is an anagram can only be decided if all other words are processed.
Therefore, if the input file is split in multiple parts that are processed inividually as if they were the entire input some a posteriori merging becomes necessary.
Sorting the words according to word length reduces the amount of merging since words of different lengths can not be anagrams.
This sorting can be achieved in linear time using bucket sort.
This also allows for preallocation of arrays.

Alternatively, the key-value map could be synchronized resulting in a certain amount of contention limiting the degree of parallelization.
The amount of contention could be determined by measuring the fraction of time a single thread spends accessing the map.

## 100 billion words:
At an input size of 100 billion words the RAM becomes a limiting factor.
Once again there is the possibility of splitting the input into parts, merging the individual outputs later on.
In the case of 10 million words the merging could be done in memory.
Here, the results are stored on the disk which results in much higher execution times.

Instead of using files the words could be inserted into a database.
Then the output lines could simply be queried from this databse.
This would probably require a lot of effort considering maximum table sizes.

Depending on the use case a potential solution is to remove duplicates.
For natural languages this results in a manageable input size not larger than a common dictionary.
For other use cases, for example cryptography, this is not that beneficial.
